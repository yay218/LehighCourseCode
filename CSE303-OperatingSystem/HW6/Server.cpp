#include <arpa/inet.h>
#include <errno.h>
#include <fcntl.h>
#include <netdb.h>
#include <netinet/in.h>
#include <openssl/md5.h>
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
#include "Server.h"
#include "LRUCache.h"
#include <thread>
#include <iostream>
#include <cmath>
using namespace std;


void help(char *progname)
{
	printf("Usage: %s [OPTIONS]\n", progname);
	printf("Initiate a network file server\n");
	printf("  -m    enable multithreading mode\n");
	printf("  -l    number of entries in the LRU cache\n");
	printf("  -p    port on which to listen for connections\n");
}

void die(const char *msg1, char *msg2)
{
	fprintf(stderr, "%s, %s\n", msg1, msg2);
	exit(0);
}

/*
 * open_server_socket() - Open a listening socket and return its file
 *                        descriptor, or terminate the program
 */
int open_server_socket(int port)
{
	int                listenfd;    /* the server's listening file descriptor */
	struct sockaddr_in addrs;       /* describes which clients we'll accept */
	int                optval = 1;  /* for configuring the socket */

	/* Create a socket descriptor */
	if((listenfd = socket(AF_INET, SOCK_STREAM, 0)) < 0)
	{
		die("Error creating socket: ", strerror(errno));
	}

	/* Eliminates "Address already in use" error from bind. */
	if(setsockopt(listenfd, SOL_SOCKET, SO_REUSEADDR, (const void *)&optval , sizeof(int)) < 0)
	{
		die("Error configuring socket: ", strerror(errno));
	}

	/* Listenfd will be an endpoint for all requests to the port from any IP
	   address */
	bzero((char *) &addrs, sizeof(addrs));
	addrs.sin_family = AF_INET;
	addrs.sin_addr.s_addr = htonl(INADDR_ANY);
	addrs.sin_port = htons((unsigned short)port);
	if(bind(listenfd, (struct sockaddr *)&addrs, sizeof(addrs)) < 0)
	{
		die("Error in bind(): ", strerror(errno));
	}

	/* Make it a listening socket ready to accept connection requests */
	if(listen(listenfd, 1024) < 0)  // backlog of 1024
	{
		die("Error in listen(): ", strerror(errno));
	}

	return listenfd;
}

/*
 * handle_requests() - given a listening file descriptor, continually wait
 *                     for a request to come in, and when it arrives, pass it
 *                     to service_function.  Note that this is not a
 *                     multi-threaded server.
 */
void handle_requests(int listenfd, void (*service_function)(int, int), int param, bool multithread)
{
	while(1)
	{
		/* block until we get a connection */
		struct sockaddr_in clientaddr;
		memset(&clientaddr, 0, sizeof(sockaddr_in));
		socklen_t clientlen = sizeof(clientaddr);
		int connfd;
		if((connfd = accept(listenfd, (struct sockaddr *)&clientaddr, &clientlen)) < 0)
		{
			die("Error in accept(): ", strerror(errno));
		}

		/* print some info about the connection */
		struct hostent *hp;
		hp = gethostbyaddr((const char *)&clientaddr.sin_addr.s_addr, sizeof(clientaddr.sin_addr.s_addr), AF_INET);
		if(hp == NULL)
		{
			fprintf(stderr, "DNS error in gethostbyaddr() %d\n", h_errno);
			exit(0);
		}
		char *haddrp = inet_ntoa(clientaddr.sin_addr);
		printf("server connected to %s (%s)\n", hp->h_name, haddrp);

		/* serve requests */
		std::thread t(service_function, connfd, param);
  		t.detach();
		//service_function(connfd, param);
	}
}

void closeConn(int connfd) {
	/* clean up, await new connection */
	if(close(connfd) < 0)
	{
		die("Error in close(): ", strerror(errno));
	}
	return;
}

/*
 * file_server() - Read a request from a socket, satisfy the request, and
 *                 then close the connection.
 */
void file_server(int connfd, int lru_size)
{
	/* TODO: set up a few static variables here to manage the LRU cache of
	   files */
	static LRUCache cache (lru_size);
	const int MAXLINE = 8192;
		char* bufp = readFromClient(connfd);
		if (bufp==NULL) {
			free(bufp);
			closeConn(connfd);
			return;
		}
		if (strncmp(bufp, "PUTC", 4)==0) {
			char* filename = strtok(bufp+5, "\n");
			char* bytes = strtok(NULL, "\n");
			char* cmpString = strtok(NULL, "\n");
			//char* action = strtok(NULL, "\n");
			//action[3] = 0;
			char* content = strtok(NULL, "\0");
			content[strlen(content)-1] = 0;
			int size = strlen(content);
			char* mdString = checksum(content, size);
			if (strncmp(cmpString, mdString, 32)==0) {
			  //if (strncmp(action, "ab+", 3)==0) {
			  //		cache.update(filename, atoi(bytes), content);
			  //	}
			  //	else{
				cache.put(filename, atoi(bytes), content);
					//	}
				FILE *fp = fopen(filename, "wb+");
				flock(fileno(fp), LOCK_EX);
				int wr = fwrite (content, 1, size, fp);
				if (wr == size) {
					writeToClient(connfd, "OKC\n");					
				}
				else {
					writeToClient(connfd, "Server Write Error.\n");
				}
				flock(fileno(fp), LOCK_UN);
				fclose(fp);
			}
			else {
				writeToClient(connfd, "Communication Error.\n");
			}
			free(mdString);
		}
		else if (strncmp(bufp, "GETC", 4)==0) {
			bufp[strlen(bufp)-1] = 0;
			long size = cache.getFileSize(bufp+5);
			if (size < 0) {
				FILE* fp = fopen (bufp+5, "rb+");
				if (fp != NULL) {
					flock(fileno(fp), LOCK_EX);
					fseek (fp, 0, SEEK_END);
					long size = ftell (fp);   // get size
			  		fseek (fp, 0, SEEK_SET);
					char content[size+1];
					char *conptr = content;
					fread(conptr, 1, size, fp);
					content[size] = 0;
					GEThandler(connfd, bufp+5, size, conptr);
					flock(fileno(fp), LOCK_UN);
					fclose(fp);
				}
				else {
					writeToClient(connfd, "Server File Not Exist.\n");	
				}
			}
			else {
				char content[size+1];
				char* conptr = content;
				strncpy(conptr, cache.getFileContent(bufp+5), size);
				content[size] = 0;
				GEThandler(connfd, bufp+5, size, conptr);
			}
		}
		else {
			writeToClient(connfd, "Invalid Request.\n");
		}

	free(bufp);
	closeConn(connfd);
	return;
}

char* readFromClient(int connfd) {
	const int MAXLINE = 8192;
	char buf[MAXLINE];
	char* bufp = buf;
	ssize_t nremain = MAXLINE;
	size_t nsofar;
	while (1)
	{
		/* read some data; swallow EINTRs */
		if((nsofar = read(connfd, bufp, nremain)) < 0)
		{
			if(errno != EINTR)
			{
				die("read error: ", strerror(errno));
			}
			continue;
		}
		/* end service to this client on EOF */
		if(nsofar == 0)
		{
			fprintf(stderr, "received EOF\n");
			return NULL;
		}
		/* update pointer for next bit of reading */
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

void writeToClient(int connfd, char* bufp) {
	size_t nremain = strlen(bufp);
	ssize_t nsofar;
	//char *bufp = buf;
	while(nremain > 0)
	{
		if((nsofar = write(connfd, bufp, nremain)) <= 0)
		{
			if(errno != EINTR)
			{
				die("Write error: ", strerror(errno));
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
	strncat(msg, content, bSize);
	strcat(msg, "\n");
	return msg;
}

void GEThandler(int connfd, char* filename, int size, char* conptr) {
	const int MAXLINE = 8192;
	int maxSize = MAXLINE - (44+strlen(to_string(size).c_str())+strlen(filename));
	int block = ceil((float)size/maxSize);
	int i, bSize;
	for (i=0; i<block; i++) {
		if (strlen(conptr)>maxSize) {
			bSize = maxSize;
		}
		else {
			bSize = strlen(conptr);
		}
		char* mdString = checksum(conptr, bSize);
		//char action[4];
		//if (i==0) {
		  //strncpy(action, "wb+", 3);
			//}
		//else {
		  //strncpy(action, "ab+", 3);
			//}
		//action[3] = 0;
		char* msg = buildMsg("OKC ", filename, size, mdString, conptr, bSize);
		writeToClient(connfd, msg);
		conptr = conptr + bSize;
		free(mdString);
		free(msg);
	}
	return;
}

/*
 * main() - parse command line, create a socket, handle requests
 */
int main(int argc, char **argv)
{
	/* for getopt */
	long opt;
	int  lru_size = 10;
	int  port     = 9000;
	bool multithread = false;

	check_team(argv[0]);

	/* parse the command-line options.  They are 'p' for port number,  */
	/* and 'l' for lru cache size, 'm' for multi-threaded.  'h' is also supported. */
	while((opt = getopt(argc, argv, "hml:p:")) != -1)
	{
		switch(opt)
		{
		case 'h': help(argv[0]); break;
		case 'l': lru_size = atoi(optarg); break;
		case 'm': multithread = true;	break;
		case 'p': port = atoi(optarg); break;
		}
	}

	/* open a socket, and start handling requests */
	int fd = open_server_socket(port);
	handle_requests(fd, file_server, lru_size, multithread);

	exit(0);
}
