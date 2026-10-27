# Glossary of terms

## Introduction

I thought it would help to compile a glossary of terms that are used in my source code.

## Glossary

| Term | Definition | Examples |
| ---- | ---------- | -------- |
| Algorithm | A list of instructions | List algorithms (Quicksort, Quickselect, Binary Search), Tree algorithms (Binary search tree, Red-black tree), Graph algorithms (Snakes and ladders), Stack algorithms (mathematical expression parsing and evaluation), Queue algorithms (Priority queue), Cryptography algorithms (AddSubtract, ROT13, ROT88, XOR, RSA) |
| Cipher | A cryptography algorithm; an algorithm for encrypting or decrypting a message | AddSubtract, ROT13, ROT88, XOR, RSA |
| Ciphertext | An encrypted message; encrypted information ||
| Data structure | A data structure is a strategy for storing data | Arrays, lists (e.g. Python's built-in list type), linked lists, stacks, queues, maps, hash maps (e.g. java.util.HashMap), tree maps (e.g. java.util.TreeMap, which is based on a red-black tree), dictionaries (e.g. Python's built-in dict type), binary search trees, red-black trees, priority queues, graphs |
| Decode | To convert code into human-readable data | ASCII, Unicode |
| Encode | To convert human-readable data into code | ASCII, Unicode |
| Input validation | Input validation is the process of making sure that an input meets the requirements ||
| Option | An option is a command-line argument that is prefixed by a single hyphen (-) or a double hyphen (--). A single hyphen denotes a short name (e.g. -v) and a double hyphen denotes a long name (e.g. --verbose). An option can accept an argument, but it doesn't have to. When an option accepts an argument, it gets its argument from the token that follows. When an option does not accept an argument, it is called a flag or a switch. An option can be required or not required (optional), depending on the choice of the programmer. Options are distinguished from positional arguments, which are not prefixed by a hyphen. | In the commands, `python -c "print('hello world')"` and `python -m json data.json`, the options are -c and -m, and they both accept an argument. |
| Parser | A parser is a tool that reads an input, deciphers an input, and produces a parse tree or a parse result, which makes the input easier to work with and understand. (Etymology: from the Latin word "pars, partis" which means "part" or "parts") | Command-line argument parser, HTML parser, XML parser, JSON parser, YAML parser, CSV parser, SQL parser, compilers and interpeters, natural language parser |
| Plaintext | An unencrypted message; unencrypted information ||
