package argparse;

/*
 * What is an exception? Here are some popular definitions.
 *
 * 1. An exception is an event, which occurs at runtime, that disrupts the program's flow of instructions.
 *    (Source: https://docs.oracle.com/javase/tutorial/essential/exceptions/definition.html)
 *
 * 2. An exception is an error detected at runtime.
 *    (Source: https://docs.python.org/3/tutorial/errors.html)
 *
 * Exceptions are a class of errors... they fall under the broader category of errors.
 *
 * Errors and error handling are a rich subject in computer science.
 *
 * If we draw the family tree (of errors), then it looks like this:
 *
 * Errors
 *      - Compiletime errors
 *           - The javac compiler reports any errors it finds when it compiles Java source code
 *      - Runtime errors
 *           - java.lang.Error
 *           - java.lang.Exception
 *                - java.lang.RuntimeException (unchecked exceptions)
 *                - All other subclasses (checked exceptions)
 * 
 * You can see that exceptions are, more specifically, a subclass of runtime errors.
 *
 * Exceptions are runtime errors that you can expect a program to recover from.
 *
 * They are handled in try-catch blocks.
 *
 * The try block contains code that might throw an exception (e.g. opening a file, reading from a file, etc). [1]
 *
 * The catch block is the exception handler.
 *
 * (You can have multiple catch blocks... multiple exception handlers... for one try block.)
 *
 * As you can see, from the family tree, above, there are two types of exceptions: checked and unchecked.
 *
 * Checked exceptions inherit from the java.lang.Exception base class, but they do not inherit from the java.lang.RuntimeException base class.
 *
 * Unchecked exceptions inherit from the java.lang.RuntimeException base class.
 *
 * If a line of code could possibly throw a checked exception, then it has to be enclosed by one of the following: (1) a try statement that catches the exception or (2) a method that specifies that it can throw the exception. [2]
 *
 * (The compiler enforces it.)
 *
 * If a line of code could possibly throw an unchecked exception, but not a checked exception, then the Catch or Specify rule, stated previously, does not apply, although the programmer can still enclose the code in a try-catch block if they want.
 *
 * Furthermore, if a method throws a checked exception, then it has to indicate this in the method signature.
 *
 * But if a method throws an unchecked exception, then it does not have to indicate this in the method signature.
 *
 * In summary, checked exceptions have to be declared and handled (the compiler enforces it) whereas unchecked exceptions don't have to be declared or handled (they give you a lot more leeway).
 *
 * So when you design an exception class, the first question you have to ask is, "Should it be checked or unchecked?"
 *
 * I'm asking myself this question right now...
 *
 * Should my ParseException class be checked or unchecked?
 *
 * I have spent a lot of time studying the Apache Commons CLI project, which is one of my models and inspirations.
 *
 * Apache Commons CLI has a ParseException class.
 *
 * It's a checked exception. It inherits directly from java.lang.Exception.
 *
 * So I think I will follow suit, and do the same.
 *
 * I will define a ParseException class that inherits directly from java.lang.Exception.
 *
 * I'll make it a checked exception.
 *
 * This means that, whenever I call my library's public-facing methods, I will have to wrap it in a try-catch block!
 *
 * But I'm okay with this. I think it's actually a good thing.
 * 
 * I want the programmer to handle any exceptions that might arise.
 *
 * Now, before I bring this long comment to a conclusion, I'd like to talk briefly about the code.
 *
 * The code itself is minimal, but what's important is the following:
 *
 * 1. It's a checked exception, because it directly subclasses java.lang.Exception
 * 2. It allows for an error message, which gives the caller important information about the cause of the exception
 * 
 * You can see that exception classes are very subtle.
 *
 * There are little details (like checked vs unchecked) that make a big impact on a program's behavior.
 *
 * Footnotes:
 *
 * [1] Opening a file can raise an exception in many circumstances, including: (1) the file is not found (2) the user or program does not have the permissions needed to open the file, read from the file, or write to the file
 *
 * [2] Cf. Catch or Specify Requirement (https://docs.oracle.com/javase/tutorial/essential/exceptions/catchOrDeclare.html)
 */
public class ParseException extends Exception {

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Exception exception) {
        super(exception);
    }

}
