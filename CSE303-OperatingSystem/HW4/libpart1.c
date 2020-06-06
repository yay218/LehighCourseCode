#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
#include"support.h"

/* hello() - print some output so we know the shared object loaded.
 *
 * The function signature takes an untyped parameter, and returns an untyped
 * parameter. In this way, the signature *could* support any behavior, by
 * passing in a struct and returning a struct. */
void *hello(void *input)
{
	printf("hello from a .so\n");
	return NULL;
}

void *ucase(struct team_t *team){
	struct team_t new_team;
	new_team = *team;

	int i = 0;
	int length = strlen(team->name1);
	char* buffer = malloc(sizeof(char) * (length+1));

	while(i < length){
		buffer[i] = toupper(new_team.name1[i]);
		i++;
	}
	new_team.name1 = buffer;
	printf("Student 1 : %s\n", new_team.name1);

	i = 0;
	length = strlen(team->email1);
	free(buffer);
	buffer = malloc(sizeof(char) * (length+1));

	while(i < length){
		buffer[i] = toupper(team->email1[i]);
		i++;
	}
	new_team.email1 = buffer;
	printf("Email 1   : %s\n", new_team.email1);

	i = 0;
	length = strlen(team->name2);
	free(buffer);
	buffer = malloc(sizeof(char) * (length+1));

	while(i < length){
		buffer[i] = toupper(new_team.name2[i]);
		i++;
	}
	new_team.name2 = buffer;
	printf("Student 2 : %s\n", new_team.name2);

	i = 0;
	length = strlen(team->email2);
	free(buffer);
	buffer = malloc(sizeof(char) * (length+1));

	while(i < length){
		buffer[i] = toupper(new_team.email2[i]);
		i++;
	}
	new_team.email2 = buffer;
	printf("Email 2   : %s\n", new_team.email2);

	i = 0;
	length = strlen(team->name3);
	free(buffer);
	buffer = malloc(sizeof(char) * (length+1));

	while(i < length){
		buffer[i] = toupper(new_team.name3[i]);
		i++;
	}
	new_team.name3 = buffer;
	printf("Student 3 : %s\n", new_team.name3);

	i = 0;
	length = strlen(team->email3);
	free(buffer);
	buffer = malloc(sizeof(char) * (length+1));

	while(i < length){
		buffer[i] = toupper(new_team.email3[i]);
		i++;
	}
	new_team.email3 = buffer;
	printf("Email 3   : %s\n", new_team.email3);
	free(buffer);

}
