# argparse

## Introduction

I just created version 1 of argparse.

To be more precise, we can call it version 1.0.0, which is a common format for version strings.

(If you have Java or Python installed, you can verify this by typing `java --version` or `python --version` on the command-line. If you're using macOS, like me, then you can run these commands in the Terminal application.)

I actually learned about version conventions, just now, myself.

I thought I would share what I learned :)

So I'm going to create a folder called 1.0.0 or v1.0.0, at some point, to store the first version of my library.

Having said all this... having talked at length about version conventions... let's talk about the library itself.

argparse is a Java library for command-line argument parsing.

I was inspired by [Apache Commons CLI](https://commons.apache.org/proper/commons-cli/) and [Python's built-in argparse module](https://docs.python.org/3/library/argparse.html).

I used Apache Commons CLI (Java) and argparse (Python) as models.

You can see that I blended elements from both.

I got the name ArgumentParser from Python's built-in argparse library.

I got the method signature for my ArgumentParser.parse method from CommandLineParser.parse in Apache Commons CLI.

But I also added my own twists.

I set the return type of my ArgumentParser.parse method to ParseResult, and created a ParseResult class.

I wanted to teach my reader the phrase "parse result".

I find the phrases "parse result" and "parse tree" to be very useful.

That's what a parser does - it reads an input, deciphers the input, and produces a parse result or a parse tree.

I also wanted to balance the code so that the work is divided evenly among classes.

You can see how the work is divided evenly among the five classes: ArgumentParser, ParseResult, Types, Option, and Options.

It's not a hard-and-fast rule... this idea of "balancing the code" so that work is divided evenly among classes.

But it made sense for this particular project.

I was careful about what I made static and what I made non-static (i.e. instance).

It made sense for the Types class to have static methods.

But this is the only class in my library that has static methods or static variables.

It also made sense for the Rot13 class to have static methods.

The Rot13 class is not part of the library, but instead it is used for testing.

Now, before I wrap up this introduction, I would like to clearly state my goals in writing this library.

My first goal, in writing the argparse library, is to make command-line argument parsing easy and simple.

It shouldn't take that much code to parse command-line arguments.

I want to make it easy, so that the programmer can parse command-line arguments in very little code.

My second goal, in writing the argparse library, is to make a library that is clear and instructive.

I want my open-source library to be an educational tool, both for me and my audience.

My third goal, in writing the argparse library, is to have fun, and to learn new things about Java.

I already know the Java programming language... but there's still a lot left for me to learn.

(I like the alliteration on the letter 'l' in the previous sentence!)

For example, in writing the argparse library, I learned new things about generics.

Generics is a subject in Java... a method can take a generic argument or return a generic type.

I also learned about class literals.

I also learned new things about the Java standard (built-in) library.

So you see, learning new things is always a goal of mine :)

Having stated my three goals, in writing the argparse library - to make it easy, to make it educational, and to have fun and learn new things about Java - I think it's time to wrap up my introduction.

Let's proceed to the next section, Example usage.

## Example usage (part one)

At the present moment, we can test the argparse library using the Rot13.java file.

I tested the argparse library by running the following commands in Terminal.

    # First I'll cd to the directory where the code is stored on my filesystem
    % cd ~/Github/argparse/src
    # Let's inspect the current directory
    % ls
    argparse	Rot13.java
    # Let's inspect the argparse folder
    % ls argparse
    ArgumentParser.java	Option.java		Options.java		ParseResult.java	Types.java
    # Now let's compile the Java source code (.java files) into bytecode (.class files)
    % javac Rot13.java argparse/*.java
    # Now let's run the code
    % java Rot13 "hello world today is saturday october 24 2026"
    uryyb jbeyq gbqnl vf fngheqnl bpgbore 24 2026
    # Ok, it worked! Now let's write the output to an outputfile
    % java Rot13 "hello world today is saturday october 24 2026" -o output.txt
    # Now let's read the input from the outputfile that we just created
    % java Rot13 -i output.txt
    hello world today is saturday october 24 2026

In the future, I plan to create more test files, so that I can test the argparse library more thoroughly.

# Example usage (part two)

We can test the argparse library using the AddSubtract.java file.

I did so by running the following commands in Terminal.

    # cd into the src directory
    % cd ~/Github/argparse/src

    # Inspect the src directory
    % ls
    AddSubtract.java	argparse		Rot13.java

    # Inspect the argparse directory
    % ls argparse
    ArgumentParser.java	Option.java		Options.java		ParseResult.java	Types.java

    # Compile the Java source code into bytecode
    % javac *.java argparse/*.java

    # Encrypt our message, with a key of 12345, and write the output to encrypted_message.txt
    % java AddSubtract -e "hello world today is sunday october 25 2026" -k 12345 -o encrypted_message.txt

    # Inspect the encrypted message
    % cat encrypted_message.txt

    # Decrypt the encrypted message, with the same key, using encrypted_message.txt as our input file
    % java AddSubtract -d -i encrypted_message.txt -k 12345
    hello world today is sunday october 25 2026

You can see that it works, and that the encrypted message is really beautiful.

I think that the encrypted message (arising from a key of 12345) consists of characters from the Japanese syllabary.
