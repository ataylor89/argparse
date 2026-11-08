# resources

## Introduction

In the process of writing this library, I studied many subjects, and referred to many resources.

For example, I studied parsing, generics, reflection, cryptography, and many other subjects.

I'd like to keep a running list of resources and reference materials in the section below.

## Resources

1. [argparse](https://docs.python.org/3/library/argparse.html)
    - I was inspired by argparse, and it's one of my models
    - Python is a dynamically typed language, whereas Java is a statically typed language
    - If you compare Python's argparse with my argparse, you see the difference between dynamic and static typing
2. [Apache Commons CLI](https://commons.apache.org/proper/commons-cli/)
    - I was inspired by Apache Commons CLI, and it's one of my models
    - I learned a lot from their use of generics and class literals
    - They use class literals as runtime type tokens to determine type information at runtime... and I do too :)
3. [Lesson: Generics by Gilad Bracha](https://docs.oracle.com/javase/tutorial/extra/generics/index.html)
    - I learned a lot from reading this tutorial by Gilad Bracha, one of the creators of Java's generics feature
    - Gilad introduces generics as a feature that "allows a type or method to operate on objects of various types"
    - I benefited a lot from reading the section "Class Literals as Runtime-Type Tokens"... I ended up using it!
4. [Lesson: Generics](https://docs.oracle.com/javase/tutorial/java/generics/index.html)
    - This is one of the main tutorials on generics on Oracle's web page
    - I haven't read it yet... but I'd like to get around to reading it
    - You don't have to read a book from cover to cover, or a tutorial from start to finish
    - You can cherry pick and read the parts that you want to read
5. [Dynamic typing vs. static typing](https://docs.oracle.com/cd/E57471_01/bigData.100/extensions_bdd/src/cext_transform_typing.html)
    - This is an article on static typing vs dynamic typing, on Oracle's website
6. [Type system concepts](https://typing.python.org/en/latest/spec/concepts.html)
    - This is an article on static typing vs dynamic typing, on Python's website
7. [Trail: The Reflection API](https://docs.oracle.com/javase/tutorial/reflect/index.html)
    - This is one of the main tutorials on reflection on Oracle's web page
    - I use reflection, in Types.java, to invoke the valueOf method on a generic type
    - I don't know, at compiletime, if the type will be String, Integer, Double, or Boolean
    - I solve this problem by making use of reflection, class literals, and generics
8. [Lesson: Aggregate Operations](https://docs.oracle.com/javase/tutorial/collections/streams/index.html)
    - This is a tutorial on the Streams API, which was introduced in Java 8
    - This tutorial covers streams, pipelines, and aggregate operations
    - It defines a stream as a "sequence of elements"
    - It defines a pipeline as a "sequence of aggregate operations"
    - The Stream API, in my opinion, is mostly based on the java.util.stream.Stream interface
9. [Processing Data with Java SE 8 Streams, Part 1](https://www.oracle.com/technical-resources/articles/java/ma14-java-se-8-streams.html)
    - This is a very instructive article on streams
    - The author, Raoul-Gabriel Urma, defines a stream as a "sequence of elements from a source that supports aggregate operations"
    - He goes on to explain what a sequence of elements is, what a source is, and what an aggregate operation is
    - In Primegen.java, I end up calling the Stream#map and Stream#collect methods, which are examples of aggregate operations
    - It's worth pointing out that I first create a Stream from an ArrayList, before calling Stream#map and Stream#collect
