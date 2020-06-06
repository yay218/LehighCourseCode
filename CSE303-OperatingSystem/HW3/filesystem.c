#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <ctype.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "support.h"
#include "structs.h"
#include "filesystem.h"

void bitmapSetTrue(struct bitmap *bm, int position)
{
	int byteLocation = position/8;
	if (position > 4095)
	{
		bm = bm + SECTOR_SIZE;
	}
	int offset = position%8;
	bm->bits[byteLocation] |= 1 << offset;
}
void bitmapSetFalse(struct bitmap *bm, int position)
{
	int byteLocation = position/8;
	int offset = position%8;
	bm->bits[byteLocation] &= 0 << offset;
}
/*
void* recursivePWD(struct directory *current_directory, char* path, char* map)
{
	struct directory* pwdDirectory = current_directory;

	//Base Case
	if (pwdDirectory->dList[0].firstCluster == 35)
	{
		path = realloc(path, sizeof(char)*(strlen(path) + 1));
		path = strcat("/", path);
		printf("%s\n", path);
		free(path);
		free(pwdDirectory);
	}
	else
	{
		char* str = current_directory->dList[0].fileName;
		*path = realloc(*path, strlen(path)+strlen(str) + 1);
		*path = strcat("/", *path);
		*path = strcat(*str, *path);
		current_directory = GET_PAGE(map, current_directory->dList[1].firstCluster);
		recursivePWD(current_directory, path, map);
		free(str);
	}
}
*/
void initializeFileSystem(FILE* fs)
{
	//Creating root-sector and assigning values
	struct root_sector root;
	root.rootDirectory = 35;
	root.bitmap = 33;
	root.fat_table = 1;
	root.last_placed = 35;

	//Creating first FAT_Table
	struct FAT_table ft[32];
	memset(&ft[0].sectors, 0, 32*SECTOR_SIZE);
	ft[0].sectors[0] = MAGIC_NUMBER; //root sector
	int i;
	for (i = 1; i < 32; i++)
	{
		ft[0].sectors[i] = i + 1;
	}
	ft[0].sectors[33] = 34; //First half of bitmap
	ft[0].sectors[34] = STOP; //Second half of bitmap
	ft[0].sectors[35] = STOP; //Root directory


	//Creating bitmaps
	struct bitmap bm[2];
	memset(&bm[0].bits, 0, 2*SECTOR_SIZE);
	for (i = 0; i < 36; i++)
	{
		bitmapSetTrue(&bm[0], i);
	}



	//Creating Root Directory and first two records
	struct directory root_directory;
	struct metadata meta;
	memset(&meta, 0, 16);
	struct directory_record rd_dot;
	struct directory_record rd_dot_dot;
	memcpy(rd_dot.fileName, ".\0\0\0\0\0\0\0", 8);
	rd_dot.firstCluster = 35;
	memcpy(rd_dot_dot.fileName, "..\0\0\0\0\0\0", 8);
	memset(&root_directory.dList, 0, SECTOR_SIZE-16);
	rd_dot_dot.firstCluster = 35;
	root_directory.dList[0] = rd_dot;
	root_directory.dList[1] = rd_dot_dot;
	root_directory.meta.fType = 'd';
	root_directory.meta.size= 2*16;




	//Writing Root Sector
	fseek(fs, 0, SEEK_SET);
	fwrite(&root, SECTOR_SIZE, 1, fs);

	//Writing Fat_Table
	fseek(fs, root.fat_table*SECTOR_SIZE, SEEK_SET);
	fwrite(&ft, SECTOR_SIZE, 32, fs);

	//Writing Bitmap
	fseek(fs, root.bitmap*SECTOR_SIZE, SEEK_SET);
	fwrite(&bm, SECTOR_SIZE, 2, fs);
	//Writing Root_directory
	fseek(fs, root.rootDirectory*SECTOR_SIZE, SEEK_SET);
	
	if (fwrite(&root_directory, SECTOR_SIZE, 1, fs) != 1)
	{
		printf("ERROR FAILED TO WRITE");
	}
	fflush(fs);
}



/*
 * generateData() - Converts source from hex digits to
 * binary data. Returns allocated pointer to data
 * of size amt/2.
 */
char* generateData(char *source, size_t size)
{
	char *retval = (char *)malloc((size >> 1) * sizeof(char));

	for(size_t i=0; i<(size-1); i+=2)
	{
		sscanf(&source[i], "%2hhx", &retval[i>>1]);
	}
	return retval;
}


/*
 * filesystem() - loads in the filesystem and accepts commands
 */
void filesystem(char *file)
{
	/* pointer to the memory-mapped filesystem */
	char *map = NULL;

	/*
	 * open file, handle errors, create it if necessary.
	 * should end up with map referring to the filesystem.
	 */
	FILE *fp;
	if(file != NULL) {
		fp = fopen(file, "ab+");
	}
	else {
		perror("No file provided");
        exit(-1);
	}
	initializeFileSystem(fp);

	map = mmap(NULL, FILE_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, fileno(fp), 0);

	/* You will probably want other variables here for tracking purposes */


	/*
	 * Accept commands, calling accessory functions unless
	 * user enters "quit"
	 * Commands will be well-formatted.
	 */
	char *buffer = NULL;
	size_t size = 0;
	struct root_sector *rs = map;
	struct FAT_table *ft = map + (rs->fat_table)*SECTOR_SIZE + (rs->last_placed/256)*SECTOR_SIZE;
	struct directory *rd = (map + (rs->rootDirectory)*SECTOR_SIZE);
	struct bitmap *bm = (map +(rs->bitmap)*SECTOR_SIZE);
	struct directory *current_directory = rd;

	while(getline(&buffer, &size, stdin) != -1)
	{
		/* Basic checks and newline removal */
		size_t length = strlen(buffer);
		if(length == 0)
		{
			continue;
		}
		if(buffer[length-1] == '\n')
		{
			buffer[length-1] = '\0';
		}

		/* TODO: Complete this function */
		/* You do not have to use the functions as commented (and probably can not)
		 *	They are notes for you on what you ultimately need to do.
		 */

		if(!strcmp(buffer, "quit"))
		{
			break;
		}
		else if(!strncmp(buffer, "dump ", 5))
		{
			if(isdigit(buffer[5]))
			{
				int i;
				strtok(buffer, " ");
				char* number = strtok(NULL, " ");
				int num = atoi(number);
				
				for(i = 32*17*(num-1); i < 32*17*num; i++) {
					if(i % 34 == 0) {
                        printf("\n");
                	}
                	else if(i % 17 == 0) {
                		printf("    ");
                	}
                	else {
                        printf("%02x ", 0xFF & ((char*)map)[i]);
                	}
				}
			}
			else
			{
				/*char *filename = buffer + 5;
				char *space = strstr(buffer+5, " ");
				*space = '\0';*/
				strtok(buffer, " ");
        		char* filename = strtok(NULL, " ");
        		char* number = strtok(NULL, " ");
        		int num = atoi(number);
				int f;
       
       			if(open(filename,O_CREAT|O_RDWR,S_IRWXU)== (-1)) {
           			perror("Cannot open for writing");
       			}
       			else {
           			f = open(filename, O_CREAT|O_RDWR, S_IRWXU);
           			int i = 0;
           			char content[1024];
           			int save_file = dup(1);
           			dup2(f, 1);
           			for(i = 32*17*(num-1); i < 32*17*num; i++) {
           				if(i % 34 == 0) {
                        	printf("\n");
	                	}
	                	else if(i % 17 == 0) {
	                		printf("    ");
	                	}
	                	else {
	                        printf("%02x ", 0xFF & ((char*)map)[i]);
	                	}
					}
					printf("\n");
					dup2(save_file, 1);
					close(f);
					close(save_file);

       			}
			}
		}
		else if(!strncmp(buffer, "usage", 5))
		{
			int i;
			int count = 0;
			for (i = 0; i < 256; i++) {
				if(ft[0].sectors[i] != 0) {
					count++;
				}
			}
			printf("Space used by filesystem is %d\n", count*512);
			printf("Space used for actual files is %d\n", (count-35)*512);
			//usage();
		}
		else if(!strncmp(buffer, "pwd", 3))
		{
			int i;
			struct directory *current_location = current_directory;
			for (i = 0; i < 31; i++)
			{
				if (strlen(current_directory->dList[i].fileName) == 0)
				{
					break;
				}
				else if (strcmp(current_directory->dList[i].fileName, "..") == 0)
				{
					current_location = GET_PAGE(map, current_directory->dList[i].firstCluster);
					//printf("%s\n", current_location->dList[i].fileName);
				}
				if(strcmp(current_location->dList[i].fileName, ".") != 0 && strcmp(current_location->dList[i].fileName, "..") != 0)	{
					if(current_location->dList[i].size == 0) {
						printf("root directory\n");
						break;
					}
					else {
						printf("%s\n", current_location->dList[i].fileName);
						break;
					}
				}
				
			//cd(buffer+3);
			}

			//char* path = "";
			//recursivePWD(&current_directory, path, map);
			//printf("%s\n", current_directory->dList[i].fileName);
		}
		else if(!strncmp(buffer, "cd ", 3))
		{
			char* dName = buffer + 3;
			int i;
			for (i = 0; i < 31; i++)
			{
				if (strlen(current_directory->dList[i].fileName) == 0)
				{
					break;
				}
				else if (strcmp(current_directory->dList[i].fileName, dName) == 0)
				{
					current_directory = GET_PAGE(map, current_directory->dList[i].firstCluster);
				}	
			//cd(buffer+3);
			}
		}
		else if(!strncmp(buffer, "ls", 2))
		{
			int i = 0;
			for (i = 0; i < 31; i++)
			{
				if (strlen(current_directory->dList[i].fileName) == 0)
				{
					break;
				}
				else
				{
					printf("%s\n", current_directory->dList[i].fileName);
				}	
			}
		}
		else if(!strncmp(buffer, "mkdir ", 6))
		{
			char* dName = buffer + 6;
			struct directory newDirectory;
			int i;
			for (i = 0; i < 31; i++)
			{
				if (current_directory->dList[i].firstCluster == 0)
				{
					break;
				}
			}
			if (i == 32)
			{
				fprintf(stderr, "Directory Full");
			}
			else
			{
				//Create and place directory record
				struct directory_record ndr;
				memset(ndr.fileName, '\0', 8);
				memcpy(ndr.fileName, dName, strlen(dName));
				rs->last_placed = rs->last_placed + 1;
				ndr.firstCluster = rs->last_placed;
				if (rs->last_placed%256 == 0)
				{
					ft = ft + SECTOR_SIZE;
				}
				current_directory->dList[i] = ndr;
				ft->sectors[rs->last_placed%256] = STOP;
				bitmapSetTrue(bm, rs->last_placed);

				//Create Directory
				memset(&newDirectory, 0, SECTOR_SIZE);
				struct directory_record rd_dot;
				struct directory_record rd_dot_dot;
				memcpy(rd_dot.fileName, ".\0\0\0\0\0\0\0", 8);
				rd_dot.firstCluster = rs->last_placed;
				memcpy(rd_dot_dot.fileName, "..\0\0\0\0\0\0", 8);
				memset(&newDirectory.dList, 0, SECTOR_SIZE);
				rd_dot_dot.firstCluster = current_directory->dList[0].firstCluster;
				newDirectory.dList[0] = rd_dot;
				newDirectory.dList[1] = rd_dot_dot;
				fseek(fp, ndr.firstCluster*SECTOR_SIZE, SEEK_SET);
				fwrite(&newDirectory, SECTOR_SIZE, 1, fp);
				fflush(fp);
			}

		}
		else if(!strncmp(buffer, "cat ", 4))
		{
			//cat(buffer + 4);
		}
		else if(!strncmp(buffer, "write ", 6))
		{
			char *filename = buffer + 6;
			char *space = strstr(buffer+6, " ");
			*space = '\0';
			size_t amt = atoi(space + 1);
			space = strstr(space+1, " ");

			char *data = generateData(space+1, amt<<1);
			//write(filename, amt, data);
			free(data);
		}
		else if(!strncmp(buffer, "append ", 7))
		{
			char *filename = buffer + 7;
			char *space = strstr(buffer+7, " ");
			*space = '\0';
			size_t amt = atoi(space + 1);
			space = strstr(space+1, " ");

			char *data = generateData(space+1, amt<<1);
			//append(filename, amt, data);
			free(data);
		}
		else if(!strncmp(buffer, "getpages ", 9))
		{
			//getpages(buffer + 9);
		}
		else if(!strncmp(buffer, "get ", 4))
		{
			char *filename = buffer + 4;
			char *space = strstr(buffer+4, " ");
			*space = '\0';
			size_t start = atoi(space + 1);
			space = strstr(space+1, " ");
			size_t end = atoi(space + 1);
			//get(filename, start, end);
		}
		else if(!strncmp(buffer, "rmdir ", 6))
		{
			//rmdir(buffer + 6);
		}
		else if(!strncmp(buffer, "rm -rf ", 7))
		{
			//rmForce(buffer + 7);
		}
		else if(!strncmp(buffer, "rm ", 3))
		{
			//rm(buffer + 3);
		}
		else if(!strncmp(buffer, "scandisk", 8))
		{
			//scandisk();
		}
		else if(!strncmp(buffer, "undelete ", 9))
		{
			//undelete(buffer + 9);
		}



		free(buffer);
		buffer = NULL;
	}
	free(buffer);
	buffer = NULL;

}

/*
 * help() - Print a help message.
 */
void help(char *progname)
{
	printf("Usage: %s [FILE]...\n", progname);
	printf("Loads FILE as a filesystem. Creates FILE if it does not exist\n");
	exit(0);
}

/*
 * main() - The main routine parses arguments and dispatches to the
 * task-specific code.
 */
int main(int argc, char **argv)
{
	
	struct directory_record dr;
	struct directory d;
	struct FAT_table ft;
	struct file_page fp;
	struct root_sector rs;
	struct bitmap b;
	printf("%li\n%li\n%li\n%li\n%li\n%li\n", sizeof(dr), sizeof(d),sizeof(ft), sizeof(fp),sizeof(rs), sizeof(b));
	
	/* for getopt */
	long opt;

	/* run a student name check */
	check_student(argv[0]);

	/* parse the command-line options. For this program, we only support */
	/* the parameterless 'h' option, for getting help on program usage. */
	while((opt = getopt(argc, argv, "h")) != -1)
	{
		switch(opt)
		{
		case 'h':
			help(argv[0]);
			break;
		}
	}

	if(argv[1] == NULL)
	{
		fprintf(stderr, "No filename provided, try -h for help.\n");
		return 1;
	}

	filesystem(argv[1]);
	return 0;
}
