#include <arpa/inet.h>
#include <errno.h>
#include <fcntl.h>
#include <netdb.h>
#include <netinet/in.h>
#include <openssl/bio.h>
#include <openssl/err.h>
#include <openssl/evp.h>
#include <openssl/md5.h>
#include <openssl/pem.h>
#include <openssl/rsa.h>
#include <openssl/ssl.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>
#include <sys/socket.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/file.h>
#include <unistd.h>
#include "support.h"
#include "Client.h"
#include <iostream>
#include <cmath>
using namespace std;

void help(char *progname)
{
	printf("Usage: %s [OPTIONS]\n", progname);
	printf("Perform a PUT or a GET from a network file server\n");
	printf("  -P    PUT file indicated by parameter\n");
	printf("  -G    GET file indicated by parameter\n");
	printf("  -s    server info (IP or hostname)\n");
	printf("  -p    port on which to contact server\n");
	printf("  -S    for GETs, name to use when saving file locally\n");
}

void die(const char *msg1, const char *msg2)
{
	fprintf(stderr, "%s, %s\n", msg1, msg2);
	exit(0);
}

/*
 * connect_to_server() - open a connection to the server specified by the
 *                       parameters
 */
int connect_to_server(char *server, int port)
{
	int clientfd;
	struct hostent *hp;
	struct sockaddr_in serveraddr;
	char errbuf[256];                                   /* for errors */

	/* create a socket */
	if((clientfd = socket(AF_INET, SOCK_STREAM, 0)) < 0)
	{
		die("Error creating socket: ", strerror(errno));
	}

	/* Fill in the server's IP address and port */
	if((hp = gethostbyname(server)) == NULL)
	{
		sprintf(errbuf, "%d", h_errno);
		die("DNS error: DNS error ", errbuf);
	}
	bzero((char *) &serveraddr, sizeof(serveraddr));
	serveraddr.sin_family = AF_INET;
	bcopy((char *)hp->h_addr_list[0], (char *)&serveraddr.sin_addr.s_addr, hp->h_length);
	serveraddr.sin_port = htons(port);

	/* connect */
	if(connect(clientfd, (struct sockaddr *) &serveraddr, sizeof(serveraddr)) < 0)
	{
		die("Error connecting: ", strerror(errno));
	}
	return clientfd;
}

/*
 * echo_client() - this is dummy code to show how to read and write on a
 *                 socket when there can be short counts.  The code
 *                 implements an "echo" client.
 */
void echo_client(int fd)
{
	// main loop
	while(1)
	{
		/* set up a buffer, clear it, and read keyboard input */
		const int MAXLINE = 8192;
		char buf[MAXLINE];
		bzero(buf, MAXLINE);
		if(fgets(buf, MAXLINE, stdin) == NULL)
		{
			if(ferror(stdin))
			{
				die("fgets error", strerror(errno));
			}
			break;
		}

		/* send keystrokes to the server, handling short counts */
		size_t n = strlen(buf);
		size_t nremain = n;
		ssize_t nsofar;
		char *bufp = buf;
		while(nremain > 0)
		{
			if((nsofar = write(fd, bufp, nremain)) <= 0)
			{
				if(errno != EINTR)
				{
					fprintf(stderr, "Write error: %s\n", strerror(errno));
					exit(0);
				}
				nsofar = 0;
			}
			nremain -= nsofar;
			bufp += nsofar;
		}

		/* read input back from socket (again, handle short counts)*/
		bzero(buf, MAXLINE);
		bufp = buf;
		nremain = MAXLINE;
		while(1)
		{
			if((nsofar = read(fd, bufp, nremain)) < 0)
			{
				if(errno != EINTR)
				{
					die("read error: ", strerror(errno));
				}
				continue;
			}
			/* in echo, server should never EOF */
			if(nsofar == 0)
			{
				die("Server error: ", "received EOF");
			}
			bufp += nsofar;
			nremain -= nsofar;
			if(*(bufp-1) == '\n')
			{
				*bufp = 0;
				break;
			}
		}

		/* output the result */
		printf("%s", buf);
	}
}

/*
 * put_file() - send a file to the server accessible via the given socket fd
 */
void put_file(int fd, char *put_name, int encrypt)
{
	const int MAXLINE = 8192;
	//open file
	FILE* fp = fopen (put_name, "rb+");
	if (fp != NULL) {
		flock(fileno(fp), LOCK_EX);
		fseek (fp, 0, SEEK_END);
		long size = ftell (fp);   // get size
  		fseek (fp, 0, SEEK_SET);
		char content[size+1];
		char *conptr = content;
		fread(conptr, 1, size, fp);
		content[size] = 0;

		int maxSize = MAXLINE - (45+strlen(to_string(size).c_str())+strlen(put_name));
		int block = ceil((float)size/maxSize);
		int i, bSize;
		for (i=0; i<block; i++) {
			if (strlen(conptr)>maxSize) {
				bSize = maxSize;
			}
			else {
				bSize = strlen(conptr);
			}
			char* enConptr = encryption(encrypt, conptr, bSize);  // encrption
			if (enConptr==NULL) {
				printf("Error: Encryption Failed.\n");
				break;
			}
			char* mdString = checksum(enConptr, bSize);  // checksum
			/*			char action[4];
			if (i==0) {
				strncpy(action, "wb+", 3);
			}
			else {
				strncpy(action, "ab+", 3);
			}
			action[3] = 0; */
			char* msg = buildMsg("PUTC ", put_name, size, mdString, enConptr, bSize);
			writeToServer(fd, msg); //
			conptr = conptr + bSize;
			free(enConptr);
			free(mdString);
			free(msg);
			char* readBuf = readFromServer(fd);
			if (strncmp(readBuf, "OKC", 3)==0) {
				printf("PUT succeed.\n");
			}
			else {
				printf("Error: %s\n", readBuf);
			}
			free(readBuf);
		}
		flock(fileno(fp), LOCK_UN);
		fclose(fp);
	}
	else {
		printf("Error: File Not Exist.\n");	
	}
	return;
}

/*
 * get_file() - get a file from the server accessible via the given socket
 *              fd, and save it according to the save_name
 */
void get_file(int fd, char *get_name, char *save_name, int encrypt)
{
	const int MAXLINE = 8192;
	char msg[MAXLINE];
	strcpy(msg, "GETC ");
	strcat(msg, get_name);
	strcat(msg, "\n");
	writeToServer(fd, msg);
	char* bufp = readFromServer(fd);
	if (strncmp(bufp, "OKC", 3)==0) {
		char* filename = strtok(bufp+4, "\n");
		char* bytes = strtok(NULL, "\n");
		char* cmpString = strtok(NULL, "\n");
		//char* action = strtok(NULL, "\n");
		//action[3] = 0;
		char* content = strtok(NULL, "\0");
		content[strlen(content)-1] = 0;
		int size = strlen(content);
		char* mdString = checksum(content, size);
		if (strncmp(cmpString, mdString, 32)==0) {
			if (save_name != NULL) {
				bzero(filename, strlen(filename));
				strcpy(filename, save_name);
			}
			char* deConptr = decryption(encrypt, content, size);
			if(deConptr!=NULL) {
				FILE* fp = fopen(filename, "wb+");
				flock(fileno(fp), LOCK_EX);
				int wr = fwrite (deConptr, 1, size, fp);
				if (wr < 0) {
					printf("Error: Client Write Error.\n");
				}
				else {
					printf("GET succeed.\n");
				}
				flock(fileno(fp), LOCK_UN);
				fclose(fp);
			}
			else {
				printf("Error: Decryption Failed.\n");
			}
			free(deConptr);
		}
		else {
			printf("Error: Communication Error.\n");
		}
		free(mdString);
	}
	else {
		printf("Error: %s\n", bufp);
	}
	free(bufp);
	return;
}

char* readFromServer(int fd) {
	const int MAXLINE = 8192;
	char buf[MAXLINE];
	char* bufp = buf;
	size_t nremain = MAXLINE;
	ssize_t nsofar;
	while(1)
	{
		if((nsofar = read(fd, bufp, nremain)) < 0)
		{
			if(errno != EINTR)
			{
				die("read error: ", strerror(errno));
			}
			continue;
		}
		/* in echo, server should never EOF */
		if(nsofar == 0)
		{
			die("Server error: ", "received EOF");
		}
		bufp += nsofar;
		nremain -= nsofar;
		if(*(bufp-1) == '\n')
		{
			*bufp = 0;
			break;
		}
	}
	char* con = (char*)malloc(MAXLINE*sizeof(char));
	strcpy(con, buf);
	return con;
}

void writeToServer(int fd, char* bufp) {
	size_t nremain = strlen(bufp);
	ssize_t nsofar;
	//char *bufp = buf;
	while(nremain > 0)
	{
		if((nsofar = write(fd, bufp, nremain)) <= 0)
		{
			if(errno != EINTR)
			{
				fprintf(stderr, "Write error: %s\n", strerror(errno));
				exit(0);
			}
			nsofar = 0;
		}
		nremain -= nsofar;
		bufp += nsofar;
	}
	return;
}

char* checksum (char* conptr, int size) {
	unsigned char checksum[MD5_DIGEST_LENGTH];
	char* mdString = (char*)malloc(33 * sizeof(char));
	MD5_CTX ctx;
    MD5_Init(&ctx);
    MD5_Update(&ctx, conptr, size);
    MD5_Final(checksum, &ctx);
    for (int i = 0; i < 16; i++) {
    	sprintf(&mdString[i*2], "%02x", (unsigned int)checksum[i]);
    }
    return mdString;
}

char* buildMsg(char* title, char* filename, int size, char* mdString, char* content, int bSize) {
	const int MAXLINE = 8192;
	char* msg = (char*)malloc(MAXLINE * sizeof(char));
	strcpy(msg, title);
	strcat(msg, filename);
	strcat(msg, "\n");
	strcat(msg, to_string(size).c_str());
	strcat(msg, "\n");
	strcat(msg, mdString);
	strcat(msg, "\n");
	//strcat(msg, action);
	//strcat(msg, "\n");
	strncat(msg, content, bSize);
	strcat(msg, "\n");
	return msg;
}


RSA* createRSAWithFilename(char * filename,int pub) {
  FILE * fp = fopen(filename,"rb");
  if(fp == NULL) {
    printf("Unable to open file %s \n",filename);
    return NULL;
  }
  RSA *rsa= RSA_new() ;
  if(pub) {
    rsa = PEM_read_RSA_PUBKEY(fp, &rsa, NULL, NULL);
  }
  else {
    rsa = PEM_read_RSAPrivateKey(fp, &rsa, NULL, NULL);
  }
  return rsa;
}

int to_encrypt(unsigned char* data, int len, unsigned char *en) {
  RSA* rsa = createRSAWithFilename("public.pem",1);
  int ret = RSA_public_encrypt(len,data,en,rsa,RSA_PKCS1_PADDING);
  return ret;
}

int to_decrypt(unsigned char* data, int len, unsigned char *de) {
  RSA* rsa = createRSAWithFilename("private.pem",0);
  int ret = RSA_private_decrypt(len,data,de,rsa,RSA_PKCS1_PADDING);
  return ret;
}

char* encryption(int encrypt, char* conptr, int size) {
	const int MAXLINE = 8192;
	char *str = (char *) malloc(sizeof(char) * MAXLINE);
	char buf[MAXLINE];
  	bzero(buf, MAXLINE);
	unsigned char en[MAXLINE]={};
	int len = 0;
	if(encrypt) {
  		strncat(buf, conptr, size);
	}
	else {
  		strncat(str, conptr, size);
	}
  	if(encrypt) {
    	len = to_encrypt(reinterpret_cast<unsigned char *>(buf), strlen(buf), en);
    	if(len == -1) {
    		return NULL;
    	}
    	strcat(str,reinterpret_cast<const char *>(en));
  	}
  	return str;
}

char* decryption(int encrypt, char* conptr, int size) {
	const int MAXLINE = 8192;
	char *str = (char *) malloc(sizeof(char) * MAXLINE);
	char buf[MAXLINE];
	bzero(buf, MAXLINE);
	unsigned char de[MAXLINE]={};
  	int len = 0; 
    if(encrypt) {
	    strncat(buf, conptr, size);
    }
    else {
    	strncat(str, conptr, size);
    }
    if(encrypt) {
      	len = to_decrypt(reinterpret_cast<unsigned char *>(buf), strlen(buf), de);
      	if(len == -1) {
      		return NULL;
      	}
      	strcat(str,reinterpret_cast<const char *>(de));
    }
    return str;
}


/*
 * main() - parse command line, open a socket, transfer a file
 */
int main(int argc, char **argv)
{
	/* for getopt */
	long  opt;
	char *server = NULL;
	char *put_name = NULL;
	char *get_name = NULL;
	int   port;
	char *save_name = NULL;
	int encrypt = 0;

	check_team(argv[0]);

	/* parse the command-line options. */
	while((opt = getopt(argc, argv, "hs:P:G:S:p:e")) != -1)
	{
		switch(opt)
		{
		case 'h': help(argv[0]); break;
		case 's': server = optarg; break;
		case 'P': put_name = optarg; break;
		case 'G': get_name = optarg; break;
		case 'S': save_name = optarg; break;
		case 'p': port = atoi(optarg); break;
		case 'e': encrypt = 1; break;
		}
	}

	/* open a connection to the server */
	int fd = connect_to_server(server, port);

	/* put or get, as appropriate */
	if(put_name)
	{
		put_file(fd, put_name, encrypt);
	}
	else
	{
		get_file(fd, get_name, save_name, encrypt);
	}

	/* close the socket */
	int rc;
	if((rc = close(fd)) < 0)
	{
		die("Close error: ", strerror(errno));
	}
	exit(0);
}
