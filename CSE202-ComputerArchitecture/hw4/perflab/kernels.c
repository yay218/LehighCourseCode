
/********************************************************
 * Kernels to be optimized for the CS:APP Performance Lab
 ********************************************************/

#include <stdio.h>
#include <stdlib.h>
#include "defs.h"
#include <string.h>

/* Please fill in the following team struct */
struct student_t student = {
/* Full name, email address */
	"Yang Yi",
	"yay218@lehigh.edu",
};

/***************
 * FLIP KERNEL
 ***************/

/******************************************************
 * Your different versions of the flip kernel go here
 ******************************************************/

/*
 * naive_flip - The naive baseline version of flip
 */
char naive_flip_descr[] = "naive_flip: Naive baseline implementation";
void naive_flip(int dim, struct pixel_t *src, struct pixel_t *dst) 
{
  int i, j;
    for(i = 0; i < dim; i++)
      {
	for(j = 0; j < dim; j++)
	  {
	    dst[RIDX(dim-1-i, j, dim)] = src[RIDX(i, j, dim)];
	  }
      }
}

/* 
 * flip - Your current working version of flip
 * IMPORTANT: This is the version you will be graded on
 */
char flip_descr[] = "flip: Current working version";
void flip(int dim, struct pixel_t *src, struct pixel_t *dst)
{
  int i;
  for(i = 0; i < dim; i++)
    memcpy(dst+(dim-i-1)*dim, src+i*dim, dim*sizeof(struct pixel_t));
}

/*********************************************************************
 * register_flip_functions - Register all of your different versions
 *     of the flip kernel with the driver by calling the
 *     add_flip_function() for each test function. When you run the
 *     driver program, it will test and report the performance of each
 *     registered test function.  
 *********************************************************************/

void register_flip_functions() 
{
    add_flip_function(&naive_flip, naive_flip_descr);   
    add_flip_function(&flip, flip_descr);   
    /* ... Register additional test functions here */
}


/***************
 * SMOOTH KERNEL
 **************/

/***************************************************************
 * Various typedefs and helper functions for the smooth function
 * You may modify these any way you like.
 **************************************************************/

/* A struct used to compute averaged pixel value */
typedef struct {
    int red;
    int green;
    int blue;
    int num;
} pixel_sum;

/* Compute min and max of two integers, respectively */
static int min(int a, int b) { return (a < b ? a : b); }
static int max(int a, int b) { return (a > b ? a : b); }

/* 
 * initialize_pixel_sum - Initializes all fields of sum to 0 
 */
static void initialize_pixel_sum(pixel_sum *sum) 
{
	sum->red = sum->green = sum->blue = 0;
	sum->num = 0;
}

/* 
 * accumulate_sum - Accumulates field values of p in corresponding 
 * fields of sum 
 */
static void accumulate_sum(pixel_sum *sum, struct pixel_t p) 
{
    sum->red += (int) p.red;
    sum->green += (int) p.green;
    sum->blue += (int) p.blue;
    sum->num++;
}

/* 
 * assign_sum_to_pixel - Computes averaged pixel value in current_pixel 
 */
static void assign_sum_to_pixel(struct pixel_t *current_pixel, pixel_sum sum) 
{
    current_pixel->red = (unsigned short) (sum.red/sum.num);
    current_pixel->green = (unsigned short) (sum.green/sum.num);
    current_pixel->blue = (unsigned short) (sum.blue/sum.num);
}

/* 
 * avg - Returns averaged pixel value at (i,j) 
 */
static struct pixel_t avg(int dim, int i, int j, struct pixel_t *src) 
{
    int ii, jj;
    pixel_sum sum;
    struct pixel_t current_pixel;

    initialize_pixel_sum(&sum);
    for(ii = max(i-1, 0); ii <= min(i+1, dim-1); ii++)
	{
		for(jj = max(j-1, 0); jj <= min(j+1, dim-1); jj++)
		{
	    	accumulate_sum(&sum, src[RIDX(ii, jj, dim)]);
		}
	}

    assign_sum_to_pixel(&current_pixel, sum);
    return current_pixel;
}

/******************************************************
 * Your different versions of the smooth kernel go here
 ******************************************************/

/*
 * naive_smooth - The naive baseline version of smooth 
 */
char naive_smooth_descr[] = "naive_smooth: Naive baseline implementation";
void naive_smooth(int dim, struct pixel_t *src, struct pixel_t *dst) 
{
    int i, j;

    for(i = 0; i < dim; i++)
	{
		for(j = 0; j < dim; j++)
		{
	    	dst[RIDX(i, j, dim)] = avg(dim, i, j, src);
		}
	}
}

/*
 * smooth - Your current working version of smooth. 
 * IMPORTANT: This is the version you will be graded on
 */
char smooth_descr[] = "smooth: Current working version";
void smooth(int dim, struct pixel_t *src, struct pixel_t *dst) 
{
  int i, j;

            dst[RIDX(0, 0, dim)].red = (src[RIDX(0, 0, dim)].red + src[RIDX(0, 1, dim)].red + src[RIDX(1, 0, dim)].red + src[RIDX(1, 1, dim)].red)/4;
            dst[RIDX(0, 0, dim)].green = (src[RIDX(0, 0, dim)].green + src[RIDX(0, 1, dim)].green + src[RIDX(1, 0, dim)].green + src[RIDX(1, 1, dim)].green)/4;
            dst[RIDX(0, 0, dim)].blue = (src[RIDX(0, 0, dim)].blue + src[RIDX(0, 1, dim)].blue + src[RIDX(1, 0, dim)].blue + src[RIDX(1, 1, dim)].blue)/4;

            dst[RIDX(dim - 1, 0, dim)].red = (src[RIDX(dim - 1, 0, dim)].red + src[RIDX(dim - 1-1, 0, dim)].red + src[RIDX(dim - 1-1, 1, dim)].red + src[RIDX(dim - 1, 1, dim)].red)/4;
            dst[RIDX(dim - 1, 0, dim)].green = (src[RIDX(dim - 1, 0, dim)].green + src[RIDX(dim - 1-1, 0, dim)].green + src[RIDX(dim - 1-1, 1, dim)].green + src[RIDX(dim - 1, 1, dim)].green)/4;
            dst[RIDX(dim - 1, 0, dim)].blue = (src[RIDX(dim - 1, 0, dim)].blue + src[RIDX(dim - 1-1, 0, dim)].blue + src[RIDX(dim - 1-1, 1, dim)].blue + src[RIDX(dim - 1, 1, dim)].blue)/4;

            dst[RIDX(0, dim - 1, dim)].red = (src[RIDX(0, dim - 1, dim)].red + src[RIDX(0, dim - 1-1, dim)].red + src[RIDX(1, dim - 1-1, dim)].red + src[RIDX(1, dim - 1, dim)].red)/4;
            dst[RIDX(0, dim - 1, dim)].green = (src[RIDX(0, dim - 1, dim)].green + src[RIDX(0, dim - 1-1, dim)].green + src[RIDX(1, dim - 1-1, dim)].green + src[RIDX(1, dim - 1, dim)].green)/4;
            dst[RIDX(0, dim - 1, dim)].blue = (src[RIDX(0, dim - 1, dim)].blue + src[RIDX(0, dim - 1-1, dim)].blue + src[RIDX(1, dim - 1-1, dim)].blue + src[RIDX(1, dim - 1, dim)].blue)/4;

            dst[RIDX(dim - 1, dim - 1, dim)].red = (src[RIDX(dim - 1, dim - 1, dim)].red + src[RIDX(dim - 1, dim - 1-1, dim)].red + src[RIDX(dim - 1-1, dim - 1-1, dim)].red + src[RIDX(dim - 1-1, dim - 1, dim)].red)/4;
            dst[RIDX(dim - 1, dim - 1, dim)].green = (src[RIDX(dim - 1, dim - 1, dim)].green + src[RIDX(dim - 1, dim - 1-1, dim)].green + src[RIDX(dim - 1-1, dim - 1-1, dim)].green + src[RIDX(dim - 1-1, dim - 1, dim)].green)/4;
            dst[RIDX(dim - 1, dim - 1, dim)].blue = (src[RIDX(dim - 1, dim - 1, dim)].blue + src[RIDX(dim - 1, dim - 1-1, dim)].blue + src[RIDX(dim - 1-1, dim - 1-1, dim)].blue + src[RIDX(dim - 1-1, dim - 1, dim)].blue)/4;

        for (j = 1; j < dim - 1; j++) {    
            i = 0;
            dst[RIDX(i, j, dim)].red = (src[RIDX(i, j, dim)].red + src[RIDX(i, j+1, dim)].red + src[RIDX(i, j-1, dim)].red + src[RIDX(i+1, j, dim)].red + src[RIDX(i+1, j+1, dim)].red+ src[RIDX(i+1, j-1, dim)].red)/6;
            dst[RIDX(i, j, dim)].green = (src[RIDX(i, j, dim)].green + src[RIDX(i, j+1, dim)].green + src[RIDX(i, j-1, dim)].green + src[RIDX(i+1, j, dim)].green + src[RIDX(i+1, j+1, dim)].green + src[RIDX(i+1, j-1, dim)].green)/6;
            dst[RIDX(i, j, dim)].blue = (src[RIDX(i, j, dim)].blue + src[RIDX(i, j+1, dim)].blue + src[RIDX(i, j-1, dim)].blue + src[RIDX(i+1, j, dim)].blue + src[RIDX(i+1, j+1, dim)].blue + src[RIDX(i+1, j-1, dim)].blue)/6;
        }

        for (j = 1; j < dim - 1; j++) {
            i = dim - 1;    
            dst[RIDX(i, j, dim)].red = (src[RIDX(i, j, dim)].red + src[RIDX(i, j+1, dim)].red + src[RIDX(i, j-1, dim)].red + src[RIDX(i-1, j, dim)].red + src[RIDX(i-1, j+1, dim)].red+ src[RIDX(i-1, j-1, dim)].red)/6;
            dst[RIDX(i, j, dim)].green = (src[RIDX(i, j, dim)].green + src[RIDX(i, j+1, dim)].green + src[RIDX(i, j-1, dim)].green + src[RIDX(i-1, j, dim)].green + src[RIDX(i-1, j+1, dim)].green + src[RIDX(i-1, j-1, dim)].green)/6;
            dst[RIDX(i, j, dim)].blue = (src[RIDX(i, j, dim)].blue + src[RIDX(i, j+1, dim)].blue + src[RIDX(i, j-1, dim)].blue + src[RIDX(i-1, j, dim)].blue + src[RIDX(i-1, j+1, dim)].blue + src[RIDX(i-1, j-1, dim)].blue)/6;
        }

        for (i = 1; i < dim - 1; i++) {
            j = 0;
            dst[RIDX(i, j, dim)].red = (src[RIDX(i, j, dim)].red + src[RIDX(i+1, j, dim)].red + src[RIDX(i-1, j, dim)].red + src[RIDX(i+1, j+1, dim)].red + src[RIDX(i-1, j+1, dim)].red+ src[RIDX(i, j+1, dim)].red)/6;
            dst[RIDX(i, j, dim)].green = (src[RIDX(i, j, dim)].green + src[RIDX(i+1, j, dim)].green + src[RIDX(i-1, j, dim)].green + src[RIDX(i+1, j+1, dim)].green + src[RIDX(i-1, j+1, dim)].green + src[RIDX(i, j+1, dim)].green)/6;
            dst[RIDX(i, j, dim)].blue = (src[RIDX(i, j, dim)].blue + src[RIDX(i+1, j, dim)].blue + src[RIDX(i-1, j, dim)].blue + src[RIDX(i+1, j+1, dim)].blue + src[RIDX(i-1, j+1, dim)].blue + src[RIDX(i, j+1, dim)].blue)/6;
        }

        for (i = 1; i < dim - 1; i++) {
            j = dim - 1;
            dst[RIDX(i, j, dim)].red = (src[RIDX(i, j, dim)].red + src[RIDX(i+1, j, dim)].red + src[RIDX(i-1, j, dim)].red + src[RIDX(i+1, j-1, dim)].red + src[RIDX(i-1, j-1, dim)].red+ src[RIDX(i, j-1, dim)].red)/6;
            dst[RIDX(i, j, dim)].green = (src[RIDX(i, j, dim)].green + src[RIDX(i+1, j, dim)].green + src[RIDX(i-1, j, dim)].green + src[RIDX(i+1, j-1, dim)].green + src[RIDX(i-1, j-1, dim)].green + src[RIDX(i, j-1, dim)].green)/6;
            dst[RIDX(i, j, dim)].blue = (src[RIDX(i, j, dim)].blue + src[RIDX(i+1, j, dim)].blue + src[RIDX(i-1, j, dim)].blue + src[RIDX(i+1, j-1, dim)].blue + src[RIDX(i-1, j-1, dim)].blue + src[RIDX(i, j-1, dim)].blue)/6;
        }

        for (i = 1; i < dim - 1; i++) {
            for (j = 1; j < dim - 1; j++) {
                dst[RIDX(i, j, dim)].red = (src[RIDX(i, j, dim)].red + src[RIDX(i+1, j, dim)].red + src[RIDX(i-1, j, dim)].red + src[RIDX(i, j+1, dim)].red + src[RIDX(i, j-1, dim)].red+ src[RIDX(i-1, j-1, dim)].red+ src[RIDX(i-1, j+1, dim)].red+ src[RIDX(i+1, j-1, dim)].red+ src[RIDX(i+1, j+1, dim)].red)/9;
                dst[RIDX(i, j, dim)].green = (src[RIDX(i, j, dim)].green + src[RIDX(i+1, j, dim)].green + src[RIDX(i-1, j, dim)].green + src[RIDX(i, j+1, dim)].green + src[RIDX(i, j-1, dim)].green + src[RIDX(i-1, j-1, dim)].green+ src[RIDX(i-1, j+1, dim)].green+ src[RIDX(i+1, j-1, dim)].green+ src[RIDX(i+1, j+1, dim)].green)/9;
                dst[RIDX(i, j, dim)].blue = (src[RIDX(i, j, dim)].blue + src[RIDX(i+1, j, dim)].blue + src[RIDX(i-1, j, dim)].blue + src[RIDX(i, j+1, dim)].blue + src[RIDX(i, j-1, dim)].blue + src[RIDX(i-1, j-1, dim)].blue+ src[RIDX(i-1, j+1, dim)].blue+ src[RIDX(i+1, j-1, dim)].blue+ src[RIDX(i+1, j+1, dim)].blue)/9;
            }
        }
}


/********************************************************************* 
 * register_smooth_functions - Register all of your different versions
 *     of the smooth kernel with the driver by calling the
 *     add_smooth_function() for each test function.  When you run the
 *     driver program, it will test and report the performance of each
 *     registered test function.  
 *********************************************************************/

void register_smooth_functions() {
    add_smooth_function(&smooth, smooth_descr);
    add_smooth_function(&naive_smooth, naive_smooth_descr);
    /* ... Register additional test functions here */
}
