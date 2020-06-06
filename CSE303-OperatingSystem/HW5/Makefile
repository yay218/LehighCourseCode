# Files to compile that don't have a main() function
CCFILES = team support

# Files to compile that do have a main() function
TARGETS = bench multikeybench

# Use 64-bit compilation
BITS = 64

# Directory names
ODIR := ./obj$(BITS)
output_folder := $(shell mkdir -p $(ODIR))

# Names of files that the compiler generates
EXEFILES  = $(patsubst %, $(ODIR)/%,    $(TARGETS))
OFILES    = $(patsubst %, $(ODIR)/%.o,  $(CCFILES))
EXEOFILES = $(patsubst %, $(ODIR)/%.o,  $(TARGETS))
DEPS      = $(patsubst %, $(ODIR)/%.d,  $(CCFILES) $(TARGETS))

# Use gcc
CXX = g++
CXXFLAGS = -MMD -O2 -m$(BITS) -ggdb -std=c++17 -O2
LDFLAGS = -m$(BITS) -lpthread

# Best to be safe...
.DEFAULT_GOAL = all
.PRECIOUS: $(OFILES) $(EXEOFILES)
.PHONY: all clean

# Goal is to build all executables
all: $(EXEFILES)

# Rules for building object files
$(ODIR)/%.o: %.cc
	@echo "[CXX] $< --> $@"
	@$(CXX) $< -o $@ -c $(CXXFLAGS)

# Rules for building executables
$(ODIR)/%: $(ODIR)/%.o $(OFILES)
	@echo "[LD] $< --> $@"
	@$(CXX) $^ -o $@ $(LDFLAGS)

# clean by clobbering the build folder
clean:
	@echo Cleaning up...
	@rm -rf $(ODIR)

# submit via script
turnin:
	@echo "Submitting..."
	~jloew/CSE303/cse303-submit-p5.pl

# Include dependencies
-include $(DEPS)
