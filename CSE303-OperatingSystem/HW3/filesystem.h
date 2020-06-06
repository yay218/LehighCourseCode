#ifndef FILESYSTEM_H
#define FILESYSTEM_H


#define MAGIC_NUMBER 1855
#define SECTOR_SIZE 512
#define STOP 0xFFFF
#define FILE_SIZE (1024*1024*4)
#define NUM_PAGES (1024*8)
#define GET_PAGE(start, pageNum) (start + (pageNum*SECTOR_SIZE))


/*
 *	Prototypes for our filesystem functions.
 *
 *
 */
//Changes bitmap value to true
void bitmapSetTrue(struct bitmap *bm, int position);

//Changes bitmap value to false
void bitmapSetFalse(struct bitmap *bm, int position);

//Initialize the file system. A+ for naming btw.
void initializeFileSystem(FILE* fs);

//Verify the file system. Another A+.
//void verifyFileSystem(FILE* fs)
//Help dialog
void help(char *progname);

//Main filesystem loop
void filesystem(char *file);

//Converts source data into appropriate binary data.
//User must free the returned pointer
char* generateData(char *source, size_t size);


#endif
