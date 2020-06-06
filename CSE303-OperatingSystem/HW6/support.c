#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "support.h"

/*
 * Make sure that the student name and email fields are not empty.
 */
void check_team(char * progname)
{
	if((strcmp("", team.name1) == 0) || (strcmp("", team.email1) == 0))
	{
		printf("%s: Please fill in the student struct in team.c\n", progname);
		exit(1);
	}
	printf("Student 1: %s\n", team.name1);
	printf("Email 1  : %s\n", team.email1);
	printf("Student 2: %s\n", team.name2);
	printf("Email 2  : %s\n", team.email2);
	printf("\n");
}
