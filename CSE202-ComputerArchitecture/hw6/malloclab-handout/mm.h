#include <stdio.h>

extern int mm_init (void);
extern void *mm_malloc (size_t size);
extern void mm_free (void *ptr);
extern void *mm_realloc(void *ptr, size_t size);

struct student_t
{
	const char *name1;		/* full name */
	const char *id1;		/* login ID */
};

extern student_t student;

