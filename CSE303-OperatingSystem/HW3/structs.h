#ifndef STRUCTS_H
#define STRUCTS_H
#include "stdint.h"

/*
 *
 * Define page/sector structures here as well as utility structures
 * such as directory entries.
 *
 * Sectors/Pages are 512 bytes
 * The filesystem is 4 megabytes in size.
 * You will have 8K pages total.
 *
 */
struct root_sector
{
	uint16_t rootDirectory;
	uint16_t bitmap;
	uint16_t fat_table;
	uint16_t last_placed;
	char padding[504];


};
struct directory_record
{
	char fileName[8]; //Max file name length is 11 bytes
	uint16_t firstCluster;
	char padding[2];
	uint32_t size;
};
struct metadata
{
	char fType;
	uint32_t size;
	char padding[7];
};
struct directory
{
	struct metadata meta;
	struct directory_record dList[31]; //Each entry is 16 bytes
};
struct FAT_table
{
	uint16_t sectors[256];
};

struct file_page
{
	struct metadata meta;
	unsigned char data[496];
};

struct bitmap
{
	unsigned char bits[512];
};


#endif
