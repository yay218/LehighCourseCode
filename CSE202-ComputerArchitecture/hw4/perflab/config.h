/*********************************************************
 * config.h - Configuration data for the driver.c program.
 *********************************************************/
#ifndef _CONFIG_H_
#define _CONFIG_H_

/* 
 * CPEs for the baseline (naive) version of the flip function that
 * was handed out to the students. Rd is the measured CPE for a dxd
 * image. Run the driver.c program on your system to get these
 * numbers.  
 */
#define R64		1.9
#define R96		1.7
#define R128	1.6
#define R256	1.5
#define R512	1.4
#define R1024	1.4
#define R2048	1.7
#define R4096	1.7
#define R8192	1.7
#define R16384	1.8
#define R32768	1.8
#define R65536	1.8

/* 
 * CPEs for the baseline (naive) version of the smooth function that
 * was handed out to the students. Sd is the measure CPE for a dxd
 * image. Run the driver.c program on your system to get these
 * numbers.  
 */
#define S32		64.1
#define S64		64.6
#define S96		64.4
#define S128	64.6
#define S256	64.6
#define S512	64.6
#define S1024	64.6
#define S2048	67.9
#define S4096	67.9
#define S8192	114.5
#define S16384	114.5
#define S32768	113.9
#define S65536	114.5


#endif /* _CONFIG_H_ */
