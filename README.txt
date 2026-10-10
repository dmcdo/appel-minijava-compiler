This is a compiler that compiles MiniJava to SPARC code built following
Modern Compiler Implementation in Java by Andrew Appel up to Chapter 12.

Building:
$ make support.jar            # https://cs.fit.edu/~ryan/cse4251/support.jar
$ export SUPPORT=support.jar
$ make javacc                 # Build only the javacc files.
$ make compiler               # Build only the compiler.
$ make assembler              # Build the compiler and assembler.
$ make                        # Same as 'make assembler.'

Support/src is a reimplementation of support.jar written from the way this
compiler uses it and from Appel's book; it is not the original code.
- Reimplementation by Claude Opus 5.5

Usage:
./compile Test.java
./assemble Test.s
