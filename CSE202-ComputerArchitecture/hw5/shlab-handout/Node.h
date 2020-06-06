#ifndef NODE_H
#define NODE_H

#define MAXJOBS			16	// Maximum number of jobs at any point

#include<string>
#include<vector>

class Node
{
public:
	pid_t pid;				// Job PID
	size_t jid;				// Job ID [1, 2, ...]
	int state;				// UNDEF, BG, FG or ST
	std::string cmdline;	// Command Line

	Node();					// Empty constructor. Just like clear.
	void clear();			// Clear the current Node
};


// The job list
//std::vector<Node> jobs(MAXJOBS);

/* maxjid - Returns largest allocated job ID */
size_t maxjid(std::vector<Node> &jobs);

/* addjob - Add a job to the job list */
int addjob(std::vector<Node> &jobs, pid_t pid, int state, const char *cmdline);

/* deletejob - Delete a job whose PID=pid from the job list */
int deletejob(std::vector<Node> &jobs, pid_t pid);

/* fgpid - Return PID of current foreground job, 0 if no such job */
pid_t fgpid(std::vector<Node> &jobs);

/* getjobpid- Find a job (by PID) on the job list */
Node* getjobpid(std::vector<Node> &jobs, pid_t pid);

/* getjobjid- Find a job (by JID) on the job list */
Node* getjobjid(std::vector<Node> &jobs, size_t jid);

/* pid2jid - Map process ID to job ID */
int pid2jid(pid_t pid);

/* listjobs - Print the job list */
void listjobs(std::vector<Node> &jobs);
#endif
