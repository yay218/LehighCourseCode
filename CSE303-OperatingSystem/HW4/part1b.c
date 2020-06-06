#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>
#include <dlfcn.h>
#include"support.h"


/* load_and_invoke() - load the given .so and execute the specified function */
void load_and_invoke(char *libname, char *funcname)
{
	/* TODO: complete this function */
	//so that it uses dlopen(), dlsym(), and dlclose()
	if(libname && funcname){
		int (*function_pointer)(void);

		void* handle = dlopen(libname, RTLD_LAZY);

		if (!handle) {
        fprintf(stderr, "%s\n", dlerror());
        exit(EXIT_FAILURE);
    }

		*(void **)(&function_pointer) = dlsym(handle, funcname);

		function_pointer();

	}
}

/* help() - Print a help message. */
void help(char *progname)
{
	printf("Usage: %s [OPTIONS]\n", progname);
	printf("Load the given .so and run the requested function from that .so\n");
	printf("  -l [string] The name of the .so to load\n");
	printf("  -f [string] The name of the function within that .so to run\n");
}

/* main() - The main routine parses arguments and invokes hello */
int main(int argc, char **argv)
{
	/* for getopt */
	long opt;

	/* run a student name check */
	check_team(argv[0]);
	char* libname;
	char* funcname;

	/* parse the command-line options. For this program, we only support */
	/* the parameterless 'h' option, for getting help on program usage.  */
	while((opt = getopt(argc, argv, "hl:f:")) != -1)
	{
		switch(opt)
		{
		case 'h':	help(argv[0]); 	break;
		case 'l':
			libname = malloc(sizeof(char)*500);
			libname = optarg;
			break;
		case 'f':
			funcname = malloc(sizeof(char)*500);
			funcname = optarg;
			break;
		}
	}

	/* call load_and_invoke() to run the given function of the given library */
	load_and_invoke(libname, funcname);


	exit(0);
}
