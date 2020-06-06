#ifndef SUPPORT_H__
#define SUPPORT_H__

/*
 * Store information about the team who completed the assignment, to
 * simplify the grading process.  This is just a declaration.  The definition
 * is in team.c.
 */
extern struct team_t
{
	const char *name1;
	const char *email1;
	const char *name2;
	const char *email2;
} team;

/*
 * This function verifies that the student name is filled out
 */
void check_team(char *);

#endif
