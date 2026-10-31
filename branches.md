# branches

## What is a branch?

The tree metaphor is used often in computer science.

We see it make an appearance in data structures (binary search trees, red-black trees, etc).

It also makes an appearance in version control systems (Git, Mercurial, Apache Subversion, etc).

In the context of version control systems, a repository is like a tree of code.

By default, the repository has one branch, which is usually called main or master.

But you can make other branches, for the purpose of development.

This way, you can separate a working version (the main branch) from a rough draft (the feature branch).

When the feature branch is done, you can merge it into the main branch, so that the main branch contains the latest changes.

Branching and merging are two important concepts in version control systems.

It took me a long time to wrap my head around the subject.

It took a lot of study and practice to finally understand the subject... branching, merging, and version control.

## Branch history

Tuesday, October 27, 2026

[base64-encoding](https://github.com/ataylor89/argparse/tree/base64-encoding)

* Added support for base64 encoding to Rot13.java, Rot88.java, AddSubtract.java, and AddSubtract2.java
* Added the -b64 and --base64 flags
* For a detailed explanation of the changes, see [changes.txt](https://github.com/ataylor89/argparse/blob/base64-encoding/changes.txt)

Thursday, October 29, 2026 - Friday, October 30, 2026

[builder-pattern](https://github.com/ataylor89/argparse/tree/builder-pattern)

* Improved the ParseResult class, making it simpler and easier to use
* Added the builder pattern to the Option class, which simplifies the creation of complex options
* See [changes.txt](https://github.com/ataylor89/argparse/blob/builder-pattern/changes.txt)

Saturday, October 31, 2026

[parse-exception](https://github.com/ataylor89/argparse/tree/parse-exception)

* Created a new class, ParseException, and implemented uniform exception handling
* Implemented a simpler way to set an option's default value
* See [changes.txt](https://github.com/ataylor89/argparse/blob/parse-exception/changes.txt)
