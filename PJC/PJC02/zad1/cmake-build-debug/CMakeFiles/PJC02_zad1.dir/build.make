# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.8

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /opt/clion/bin/cmake/bin/cmake

# The command to remove a file.
RM = /opt/clion/bin/cmake/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/lucas/CLionProjects/PJC02_zad1

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/lucas/CLionProjects/PJC02_zad1/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/PJC02_zad1.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/PJC02_zad1.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/PJC02_zad1.dir/flags.make

CMakeFiles/PJC02_zad1.dir/main.cpp.o: CMakeFiles/PJC02_zad1.dir/flags.make
CMakeFiles/PJC02_zad1.dir/main.cpp.o: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/lucas/CLionProjects/PJC02_zad1/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/PJC02_zad1.dir/main.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/PJC02_zad1.dir/main.cpp.o -c /home/lucas/CLionProjects/PJC02_zad1/main.cpp

CMakeFiles/PJC02_zad1.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/PJC02_zad1.dir/main.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/lucas/CLionProjects/PJC02_zad1/main.cpp > CMakeFiles/PJC02_zad1.dir/main.cpp.i

CMakeFiles/PJC02_zad1.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/PJC02_zad1.dir/main.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/lucas/CLionProjects/PJC02_zad1/main.cpp -o CMakeFiles/PJC02_zad1.dir/main.cpp.s

CMakeFiles/PJC02_zad1.dir/main.cpp.o.requires:

.PHONY : CMakeFiles/PJC02_zad1.dir/main.cpp.o.requires

CMakeFiles/PJC02_zad1.dir/main.cpp.o.provides: CMakeFiles/PJC02_zad1.dir/main.cpp.o.requires
	$(MAKE) -f CMakeFiles/PJC02_zad1.dir/build.make CMakeFiles/PJC02_zad1.dir/main.cpp.o.provides.build
.PHONY : CMakeFiles/PJC02_zad1.dir/main.cpp.o.provides

CMakeFiles/PJC02_zad1.dir/main.cpp.o.provides.build: CMakeFiles/PJC02_zad1.dir/main.cpp.o


# Object files for target PJC02_zad1
PJC02_zad1_OBJECTS = \
"CMakeFiles/PJC02_zad1.dir/main.cpp.o"

# External object files for target PJC02_zad1
PJC02_zad1_EXTERNAL_OBJECTS =

PJC02_zad1: CMakeFiles/PJC02_zad1.dir/main.cpp.o
PJC02_zad1: CMakeFiles/PJC02_zad1.dir/build.make
PJC02_zad1: CMakeFiles/PJC02_zad1.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/lucas/CLionProjects/PJC02_zad1/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable PJC02_zad1"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/PJC02_zad1.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/PJC02_zad1.dir/build: PJC02_zad1

.PHONY : CMakeFiles/PJC02_zad1.dir/build

CMakeFiles/PJC02_zad1.dir/requires: CMakeFiles/PJC02_zad1.dir/main.cpp.o.requires

.PHONY : CMakeFiles/PJC02_zad1.dir/requires

CMakeFiles/PJC02_zad1.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/PJC02_zad1.dir/cmake_clean.cmake
.PHONY : CMakeFiles/PJC02_zad1.dir/clean

CMakeFiles/PJC02_zad1.dir/depend:
	cd /home/lucas/CLionProjects/PJC02_zad1/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/lucas/CLionProjects/PJC02_zad1 /home/lucas/CLionProjects/PJC02_zad1 /home/lucas/CLionProjects/PJC02_zad1/cmake-build-debug /home/lucas/CLionProjects/PJC02_zad1/cmake-build-debug /home/lucas/CLionProjects/PJC02_zad1/cmake-build-debug/CMakeFiles/PJC02_zad1.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/PJC02_zad1.dir/depend

