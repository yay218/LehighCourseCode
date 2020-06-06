# Files to compile that don't have a main() function
CFILES = team support

# Files to compile that do have a main() function
TARGETS = Client Server

# Sunlab OpenSSL is 64-bit only!
BITS = 64

# Directory names
ODIR := ./obj$(BITS)
output_folder := $(shell mkdir -p $(ODIR))

# Names of files that the compiler generates
EXEFILES  = $(patsubst %, $(ODIR)/%,    $(TARGETS))
OFILES    = $(patsubst %, $(ODIR)/%.o,  $(CFILES))
EXEOFILES = $(patsubst %, $(ODIR)/%.o,  $(TARGETS))
DEPS      = $(patsubst %, $(ODIR)/%.d,  $(CFILES) $(TARGETS))

# Use g++
CC = g++
CFLAGS = -MMD -O2 -m$(BITS) -ggdb -D_GNU_SOURCE
LDFLAGS = -m$(BITS) -ldl -lcrypto -lssl

# Best to be safe...
.DEFAULT_GOAL = all
.PRECIOUS: $(OFILES) $(EXEOFILES)
.PHONY: all clean

# Goal is to build all executables
all: $(EXEFILES)

# Rules for building object files
$(ODIR)/%.o: %.cpp
	@echo "[CC] $< --> $@"
	@$(CC) $< -o $@ -c $(CFLAGS)

# Rules for building object files
$(ODIR)/%.o: %.c
	@echo "[CC] $< --> $@"
	@$(CC) $< -o $@ -c $(CFLAGS)

# Rules for building executables
$(ODIR)/%: $(ODIR)/%.o $(OFILES)
	@echo "[LD] $< --> $@"
	@$(CC) $^ -o $@ $(LDFLAGS)

# clean by clobbering the build folder
clean:
	@echo Cleaning up...
	@rm -rf $(ODIR)

# submit via script
submit:
	@echo "Submitting..."
	~jloew/CSE303/cse303-submit-p6.pl
turnin:
	@echo "Submitting..."
	~jloew/CSE303/cse303-submit-p6.pl

# Include dependencies
-include $(DEPS)
