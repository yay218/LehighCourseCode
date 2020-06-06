/*
 * tsh - A tiny shell program with job control
 *
 * Yang Yi
 * yay218
 */
#include<cstdio>
#include<iostream>
#include<cstdlib>
#include<unistd.h>
#include<string>
#include<string.h>
#include<ctype.h>
#include<signal.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<fcntl.h>
#include<cerrno>
#include<vector>
#include<algorithm>


//See Node.h for various functionality of our job list
#include"Node.h"

//See defs.h for various constants that are part of this program.
#include"defs.h"

/* Global variables */
extern char **environ;			// defined in libc
int verbose = 0;				// if true, print additional output
size_t nextjid = 1;				// next job ID to allocate
extern std::vector<Node> jobs;	//The job list: extern from Node.h
/* End global variables */

/* Function prototypes */
/* Here are the functions that you will implement */
void eval(const char *cmdline);
int builtin_cmd(char **argv);
void do_bgfg(char **argv);
void waitfg(pid_t pid);
void sigchld_handler(int sig);
void sigtstp_handler(int sig);
void sigint_handler(int sig);

/* Here are helper routines that we've provided for you */
int parseline(const char *cmdline, char **argv);
void sigquit_handler(int sig);

void usage(void);
void unix_error(const char *msg);
void app_error(const char *msg);
void(*signalWrapper(int, void(*handler)(int)))(int);

/* main - The shell's main routine */
int main(int argc, char **argv)
{
	char c;
	int emit_prompt = 1; /* emit prompt (default) */

	/* Redirect stderr to stdout (so that driver will get all output
	 * on the pipe connected to stdout) */
	dup2(1, 2);

	/* Parse the command line */
	while((c = getopt(argc, argv, "hvp")) != EOF)
	{
		switch(c)
		{
		case 'h':			 /* print help message */
			usage();
			break;
		case 'v':			 /* emit additional diagnostic info */
			verbose = 1;
			break;
		case 'p':			 /* don't print a prompt */
			emit_prompt = 0;/* handy for automatic testing */
			break;
		default:
			usage();
			break;
		}
	}

	/* Install the signal handlers */
	/* These are the ones you will need to implement */
	signalWrapper(SIGINT,sigint_handler);/* ctrl-c */
	signalWrapper(SIGTSTP, sigtstp_handler);/* ctrl-z */
	signalWrapper(SIGCHLD, sigchld_handler);/* Terminated or stopped child */

	/* This one provides a clean way to kill the shell */
	signalWrapper(SIGQUIT, sigquit_handler);

	const std::string prompt = "tsh> ";
	/* Execute the shell's read/eval loop */
	while(1)
	{
		/* Read command line */
		if(emit_prompt)
		{
			std::cout << prompt;
			std::cout.flush();
		}
		std::string cmdline;
		std::getline(std::cin, cmdline);

		if(std::cin.eof())
		{
			std::cout.flush();
			exit(0);
		}

		if(!std::cin.good())
		{
			app_error("fgets error");
		}

		/* Evaluate the command line */
		cmdline += '\n';
		eval(cmdline.c_str());
		std::cout.flush();
		std::cout.flush();
	}
	exit(0); /* control never reaches here */
}

/* eval - Evaluate the command line that the user has just typed in
 *
 * If the user has requested a built-in command (quit, jobs, bg or fg)
 * then execute it immediately. Otherwise, fork a child process and
 * run the job in the context of the child. If the job is running in
 * the foreground, wait for it to terminate and then return.Note:
 * each child process must have a unique process group ID so that our
 * background children don't receive SIGINT (SIGTSTP) from the kernel
 * when we type ctrl-c (ctrl-z) at the keyboard.
*/
void eval(const char *cmdline) //Reference: Textbook Page 755
{
	char *argv[MAXARGS];
	char buf[MAXLINE];
	int bg;
	pid_t pid;
	sigset_t mask_all;

	strcpy(buf, cmdline);
	bg = parseline(buf, argv);
	if(argv[0] == NULL) {
		return;
	}

	sigemptyset(&mask_all);
	sigaddset(&mask_all, SIGCHLD);
	sigaddset(&mask_all, SIGINT);
	sigaddset(&mask_all, SIGTSTP);


	
	//printf("%s\n", argv[0]);
	if(!builtin_cmd(argv)) {
		sigprocmask(SIG_BLOCK, &mask_all, NULL);
/*
		if((pid = fork()) < 0) {
			unix_error("fork less than 0\n");
		}
		*/
		if((pid = fork()) == 0) {
			//printf("after fork()\n");
			sigprocmask(SIG_UNBLOCK, &mask_all, NULL);
			setpgid(0, 0);	

			if(execve(argv[0], argv, environ) < 0) {
				printf("%s: Command not found.\n", argv[0]);
				exit(0);
			}
		}
		//printf("%d\n", pid);

		if(!bg) {
			//printf("before addjob in FG\n");
			addjob(jobs, pid, FG, cmdline);
			//printf("after addjob IN FG\n");
		}
		else {
			//printf("before addjob in BG\n");
			addjob(jobs, pid, BG, cmdline);
			//printf("after addjob in BG\n");
		}
		sigprocmask(SIG_UNBLOCK, &mask_all, NULL);
		if(!bg) {

			//printf("foreground\n");
			waitfg(pid);
			//printf("after wait\n");
			/*
			int status;
			if(waitpid(pid, &status, 0) < 0) {
				unix_error("waitfg: waitpid error");
			}
			*/
			//if(getjobpid(jobs, pid) -> state != ST) {
			//	deletejob(jobs, pid);
			//}
		}
		else {
			//printf("background\n");
			printf("[%d] (%d) %s", pid2jid(pid), pid, cmdline);
		}
		//printf("end of eval\n");
	}
	return;
}

/* parseline - Parse the command line and build the argv array.
 *
 * Characters enclosed in double quotes are treated as a single
 * argument. Return true if the user has requested a BG job, false if
 * the user has requested a FG job.
 */
int parseline(const char *cmdline, char **argv)
{
	static std::string buf;			// string to statically hold command
	size_t delim;					// points to first space delimiter
	int argc = 0;					// number of args
	int bg;							// background job?
	size_t loc = 0;					// location within the string

	buf = cmdline;
	buf[buf.size()-1] = ' ';/* replace trailing '\n' with space */

	buf.erase(loc, buf.find_first_not_of(" ", loc) - loc);

	/* Build the argv list */
	if(buf[loc] == '"')
	{
		loc++;
		delim = buf.find('"', loc);
	}
	else
	{
		delim = buf.find(' ', loc);
	}

	while(delim != std::string::npos)
	{
		argv[argc++] = &buf[loc];
		buf[delim] = '\0';
		loc = delim + 1;

		buf.erase(loc, buf.find_first_not_of(" ", loc) - loc);

		if(buf[loc] == '"')
		{
			loc++;
			delim = buf.find('"', loc);
		}
		else
		{
			delim = buf.find(' ', loc);
		}
	}
	argv[argc] = NULL;

	if(argc == 0)/* ignore blank line */
	{
		return 1;
	}

	/* should the job run in the background? */
	if((bg = (*argv[argc-1] == '&')) != 0)
	{
		argv[--argc] = NULL;
	}

	return bg;
}

/* builtin_cmd - If the user has typed a built-in command then execute
 * it immediately.
 */
int builtin_cmd(char **argv)
{
	//printf("in builtin\n");
	//printf("%s\n",argv[0]);
	if(argv[0] == NULL) {
		return 1;
	}
	
	if(!strcmp(argv[0], "quit")) {
		exit(0);
	}

	if (!strcmp(argv[0], "&")) {
		return 1;
	}

	if(!strcmp(argv[0], "jobs")) {
		listjobs(jobs);
		return 1;
	}

	if(!strcmp(argv[0], "fg")) {
		//printf("in cmd");
		do_bgfg(argv);
		return 1;
	}

	if(!strcmp(argv[0], "bg")) {
		//printf("\n", "XXXXXXXXX");
		do_bgfg(argv);	
		return 1;
	}
	
	return 0;	 /* not a builtin command */
}

/* do_bgfg - Execute the builtin bg and fg commands */
void do_bgfg(char **argv)
{
	//printf("in do_bgfg\n");
	char *id = argv[1];
	int jid = 0;
	pid_t pid;
	int p;
	Node *getJob = getjobjid(jobs, jid);

	if(id == NULL) {
		printf("%s command requires PID or %%jobid argument\n", argv[0]);
		return;
	}


	if(id[0] == '%') {  
		jid = atoi(&id[1]);  
		getJob = getjobjid(jobs, jid);
		if(!getJob) {  
			printf("%s: No such job\n", id);  
			return;  
		} 
	} 
	else if(isdigit(id[0])) { 
		pid = atoi(id);  
		
		if(!getJob) {  
			printf("(%d): No such process\n", pid);  
			return;  
		}
	}
	else {
		printf("%s: argument must be a PID or %%jobid\n", argv[0]);
		return;
	}



	char *cmd = const_cast<char*> (getJob->cmdline.c_str());
	p = getJob->pid;
	if(!strcmp("bg", argv[0])) {
		printf("[%d] (%d) %s", jid, p, cmd);
		kill(-p, SIGCONT);
		getJob->state = BG;
	}

	if(!strcmp("fg", argv[0])) {
		//printf("in fg\n");
		getJob->state = FG;
		kill(-p, SIGCONT);
		//printf("after kill\n");

		waitfg(p);
		//printf("after wait\n");
	}




	/*
	pid_t pid;
	Node *getJob;
	int jobId = 0;

	if(argv[1][0] == '%') {
		jobId = atoi(argv[1] + 1);
	}

	//printf("\n", "XXXXXXXXX");
	if(!strcmp(argv[0], "bg")) {
		
		getJob->state = BG;
		pid = getJob->pid;
		kill(-pid, SIGCONT);
		printf("[%d] (%d) %s", (getJob->jid), (getJob->pid), (getJob->cmdline));
	}
	*/
	return;
}

/* waitfg - Block until process pid is no longer the foreground process */
void waitfg(pid_t pid)
{
	while (pid == fgpid(jobs)) {  
        sleep(0);  
    } 
	return;
}

/*****************
 * Signal handlers
 *****************/

/* sigchld_handler - The kernel sends a SIGCHLD to the shell whenever
 * a child job terminates (becomes a zombie), or stops because it
 * received a SIGSTOP or SIGTSTP signal. The handler reaps all
 * available zombie children, but doesn't wait for any other
 * currently running children to terminate.
 */
void sigchld_handler(int sig)
{
	//int olderrno = errno;
 	sigset_t mask_all;//, prev_all;
 	pid_t pid;
 	int status;//, jid;
 
 	sigfillset(&mask_all);
 
 	while ((pid = waitpid(-1, &status, WNOHANG | WUNTRACED)) > 0) { /* Reap child */
 		if(WIFSTOPPED(status)) {
 			getjobpid(jobs, pid) -> state = ST;
 			printf("Job [%d] (%d) stopped by signal %d\n", pid2jid(pid), pid, 20);
        	//deletejob(jobs, pid);
 		}
 		else if((WIFSIGNALED(status))) {
 			//sigint_handler(-2);  
 			printf("Job [%d] (%d) terminated by signal %d\n", pid2jid(pid), pid, 2);  
 			//sigprocmask(SIG_BLOCK, &mask_all, &prev_all);
 			deletejob(jobs, pid); /* Delete the child from the job list */
 			//sigprocmask(SIG_SETMASK, &prev_all, NULL);
 		}
 		else if(WIFEXITED(status)) {
 			//jid = pid2jid(pid);
 			//sigprocmask(SIG_BLOCK, &mask_all, &prev_all);
			deletejob(jobs, pid);
			//sigprocmask(SIG_SETMASK, &prev_all, NULL);
 		}
 		return;
 	}

 	//if (errno != ECHILD)
 	//	sio_error("waitpid error");
 	//errno = olderrno;
	return;
}

/* sigint_handler - The kernel sends a SIGINT to the shell whenver the
 * user types ctrl-c at the keyboard.Catch it and send it along
 * to the foreground job.
 */
void sigint_handler(int sig)
{
	pid_t pid = fgpid(jobs);  
    if(pid != 0){  
        //printf("Job [%d] (%d) terminated by signal %d\n", pid2jid(pid), pid, sig);  
        //deletejob(jobs, pid);  
        kill(-pid, sig);  
    }  
	return;
}

/* sigtstp_handler - The kernel sends a SIGTSTP to the shell whenever
 * the user types ctrl-z at the keyboard. Catch it and suspend the
 * foreground job by sending it a SIGTSTP.
 */
void sigtstp_handler(int sig)
{
	pid_t pid = fgpid(jobs);  
  
    if(pid != 0){
    	//getjobpid(jobs, pid) -> state = ST;
        //printf("Job [%d] (%d) stopped by signal %d\n", pid2jid(pid), pid, sig);
        kill(-pid, sig);  
    }  
	return;
}

/*********************
 * End signal handlers
 *********************/


/***********************
 * Other helper routines
 ***********************/

/* usage - print a help message */
void usage(void)
{
	printf("Usage: shell [-hvp]\n");
	printf("-h print this message\n");
	printf("-v print additional diagnostic information\n");
	printf("-p do not emit a command prompt\n");
	exit(1);
}

/* unix_error - unix-style error routine */
void unix_error(const char *msg)
{
	fprintf(stdout, "%s: %s\n", msg, strerror(errno));
	exit(1);
}

/* app_error - application-style error routine */
void app_error(const char *msg)
{
	fprintf(stdout, "%s\n", msg);
	exit(1);
}

/* signalWrapper - wrapper for the sigaction function */
void(*signalWrapper(int signum, void(*handler)(int)))(int)
{
	struct sigaction action, old_action;

	action.sa_handler = handler;
	sigemptyset(&action.sa_mask); /* block sigs of type being handled */
	action.sa_flags = SA_RESTART; /* restart syscalls if possible */

	if(sigaction(signum, &action, &old_action) < 0)
	{
		unix_error("Signal error");
	}
	return (old_action.sa_handler);
}

/* sigquit_handler - The driver program can gracefully terminate the
 * child shell by sending it a SIGQUIT signal.
 */
void sigquit_handler(int sig)
{
	ssize_t result = write(1, "Terminating after receipt of SIGQUIT signal\n", 44);
	if(result == -1)
	{
		exit(2);
	}
	exit(1);
}

