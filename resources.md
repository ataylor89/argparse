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
