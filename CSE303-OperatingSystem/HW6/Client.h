#pragma once

/*
 * help() - Print a help message
 */
void help(char *progname);

/*
 * die() - print an error and exit the program
 */
void die(const char *msg1, const char *msg2);

/*
 * connect_to_server() - open a connection to the server specified by the
 *                       parameters
 */
int connect_to_server(char *server, int port);

/*
 * echo_client() - this is dummy code to show how to read and write on a
 *                 socket when there can be short counts.  The code
 *                 implements an "echo" client.
 */
void echo_client(int fd);

/*
 * put_file() - send a file to the server accessible via the given socket fd
 */
void put_file(int fd, char *put_name, int encrypt);

/*
 * get_file() - get a file from the server accessible via the given socket
 *              fd, and save it according to the save_name
 */
void get_file(int fd, char *get_name, char *save_name, int encrypt);

char* readFromServer(int fd);
void writeToServer(int fd, char* buf);
char* checksum (char* conptr, int size);
char* buildMsg(char* title, char* filename, int size, char* mdString, char* content, int bSize);

//
RSA* createRSAWithFilename(char * filename,int pub);
int to_encrypt(unsigned char* data, int len, unsigned char *en);
int to_decrypt(unsigned char* data, int len, unsigned char *de);
char* encryption(int encrypt, char* conptr, int size);
char* decryption(int encrypt, char* conptr, int size);

