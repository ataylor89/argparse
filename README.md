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

## Example usage (part two)

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

## Example usage (part three)

AddSubtract2.java is another use case, another test case.

(It's a new use case, because it expects a list of integers as its key.)

I tested this use case, by running the following commands in Terminal.

    # cd into the src directory
    % cd ~/Github/argparse/src

    # Inspect the src directory
    % ls
    AddSubtract.java	AddSubtract2.java	argparse		Rot13.java

    # Inspect the argparse directory
    % ls argparse
    ArgumentParser.java	Option.java		Options.java		ParseResult.java	Types.java

    # Compile the Java source code into bytecode
    % javac *.java argparse/*.java

    # Encrypt our message, with a key of "12345,12346,12347", and write the output to stdout
    % java AddSubtract2 -e "hello world today is sunday october 25 2026" -k 12345,123456,123457

    # Encrypt our message, with a key of "12345,12346,12347", and write the output to cipher.txt
    % java AddSubtract2 -e "hello world today is sunday october 25 2026" -k 12345,123456,123457 -o cipher.txt

    # Inspect the output file
    % cat cipher.txt

    # Decrypt the encrypted message, with the same key, using cipher.txt as our input file
    % java AddSubtract2 -d -i cipher.txt -k 12345,12346,12347

Once again, the encrypted message is really beautiful.

Once again, I think that the encrypted message is written in characters from the Japanese syllabary.

## Example usage (part four)

Rot88 is a rotation cipher, just like Rot13.

Rot13 rotates an English-language letter by 13 places (wrapping around when it reaches the end of the alphabet).

Rot88 rotates a Unicode character by 0x88000 places (wrapping around when it reaches the end of the code space).

0x88000 is actually hexadecimal notation for the decimal value 557,056.

0x88000 is half the size of the Unicode code space (the full size is 0x110000 which is equal to 1114112 in decimal).

You can see the parallel...

13 is half the size of the English-language alphabet.

0x88000 is half the size of the Unicode code space.

Now, you might object and say, "Why don't you name your algorithm Rot0x88000?"

I could name it that... but I named it Rot88 for shorthand :)

The 13 in Rot13 is a reference to the decimal value 13.

The 88 in Rot88 is a reference to the hexadecimal value 0x88000.

Now, having said all this, let's run the commands that are needed, in Terminal, to test out our new file.

(If you don't have the Terminal macOS application, than there are many other terminal emulators that would suffice.)

    # cd into the src directory
    % cd ~/Github/argparse/src

    # Inspect the src directory
    % ls
    AddSubtract.java	AddSubtract2.java	argparse		Rot13.java		Rot88.java

    # Inspect the argparse directory
    % ls argparse
    ArgumentParser.java	Option.java		Options.java		ParseResult.java	Types.java

    # Compile the Java source code into bytecode
    % javac *.java argparse/*.java

    # Encrypt a message using the Rot88 rotation cipher
    % java Rot88 "hello world today is sunday october 25 2026"

    # Encrypt a message using the Rot88 rotation cipher, and write the output to cipher.txt
    % java Rot88 "hello world today is sunday october 25 2026" -o cipher.txt

    # Inspect the output file
    % cat cipher.txt

    # Decrypt our message using the Rot88 rotation cipher, using cipher.txt as our input file
    % java Rot88 -i cipher.txt

Rot88, like Rot13, is a symmetric key algorithm.

This means that the same key is used for both encryption and decryption.

Rot88 is its own inverse.

We can write this mathematically as Rot88(Rot88(message)) = message.
`
XOR is another example of a symmetric key algorithm.

RSA is an example of an asymmetric key algorithm (it uses different keys for encryption and decryption).

I think that asymmetric key cryptography is the same thing as public key cryptography.

I think that these two terms are synonymous... that they mean the same thing.

Diffie-Hellman (DH) is a second example of asymmetric, public key cryptography.

Elliptic Curve Cryptography (ECC) is a third example of asymmetric, public key cryptography.

In public key cryptography, the encryption key is called the public key, and the decryption key is called the private key.

The encryption key - the public key - gets shared with your audience.

(It can be shared through in-person delivery, electronic delivery, or some other means of delivery.)

The decryption key - the private key - stays with you, and doesn't get shared with anyone.

The idea is this:

Alex shares his encryption key with Bob, and Bob shares his encryption key with Alex.

Alex uses Bob's encryption key to write Bob a message. Bob decrypts it using his decryption key.

Bob uses Alex's encryption key to write Alex a message. Alex decrypts it using his decryption key.

In the best case scenario, Alex and Bob find a secure way to exchange their encryption keys.

If Charlie intercepts a message that Alex sends to Bob, then it is very difficult for him to decrypt the message, because he is lacking both the encryption key and the decryption key, which gives him no easy way to crack the message.

In the worst case scenario, Alex's encryption key gets discovered by Charlie.

If Charlie intercepts a message, then it is still very difficult for him to decrypt the message, because the encryption key is not enough... he actually needs to know the decryption key in order to decrypt the message.

But it is actually possible for Charlie to derive the decryption key from the encryption key, if he has access to a very powerful computer, and if he generates a table of (n, e, d) tuples for the (n, e) tuples in the encryption key.

For this reason, it is best practice to keep your encryption key as secret as possible.

It's called a public key, but this is almost a misnomer, because you should keep your encryption key as secret as possible.

It is also best practice to keep your decryption key as secret as possible.

In fact, you should share your decryption key with no one.

If you ask, "Why should I keep my encryption key as secret as possible?"

The answer is this: "Because it's possible to derive a decryption key from an encryption key, especially if someone has access to a powerful computer and knows the algorithm for doing so."

Now, I must admit that I digressed.

We were talking about Rot88, and then we launched into a conversation about public key cryptography.

But it's all well and good, because I plan to create an RSA.java file, in this repository, in the future.

The discussion, heretofore, will come in handy, when we reach the example usage section for the RSA algorithm.

In other words, I'll be adding RSA.java to the list of use cases, at some point in the future :)

(Should I call it RSA.java, or Rsa.java? I'm not sure...)
