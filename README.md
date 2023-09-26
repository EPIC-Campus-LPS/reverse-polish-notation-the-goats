# Lab 6: Reverse Polish Notation

## Objective

- Create the class `PIP` that contains the following methods
    - `static String evaluatePostfix(String input)`: Takes in a postfix expression as a String, evaluates it, and
      returns the answer
    - `static String infixToPostfix(String input)`: Takes in an infix expression as a String, converts it to a postfix
      expression and returns it

## Evaluating Postfix Expressions

In this lab, you are to write a program that will be able to compute the value of a postfix expression that uses numbers
and the four basic arithmetic operations. In our traditional math classes, we write our operators using infix notation,
where the operators are written between the operands. For instance, (5 + 3) – 7 * 9 + 2 is an example of infix notation.
However, it is difficult for a computer algorithm to evaluate an infix expression, so often, we see it perform a
two-step process instead: change the expression to postfix, then evaluate the postfix expression. Why is the postfix
advantageous? It is easier for an algorithm to evaluate with the use of a stack.

To illustrate the algorithm, consider how we would evaluate the postfix expression:7 2 + 9 / 3 5 * -

|                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Read the 7.  Push on the stack.<br><br>Read the 2. Push on the stack.<br><br>Read the +. Retrieve the 7 and 2 from the stack. Evaluate the operation. Push 9 on the stack.<br><br>Read the 9. Push on the stack.<br><br>Read the /. Retrieve the 9 and 9 from the stack. Evaluate, then push 1 on the stack.<br><br>Read the 3. Push on the stack.<br><br>Read the 5. Push on the stack.<br><br>Read the *. Retrieve the 3 and 5. Push 15 on the stack.<br><br>Read the -. Retrieve the 1 and the 15. Push -14 on the stack.<br><br>No more elements to read. Return -14. |

Write a method (and whatever helper methods necessary) to evaluate postfix. A call to the method will look like
following:`PIP.evaluatePostfix(someStringExpression)`

For example, `PIP.evaluatePostfix(“7 2 + 9 / 3 5 * -”)` should return -14. If given an invalid postfix expression, it
should throw an `IllegalArgumentException`. For example, `PIP.evaluatePostfix(“7 2 + 9 / 3 5 *”)` should throw
an `IllegalArgumentException` whose message states that this is an invalid postfix expression.

The input will be a string where the numbers and operators will be space separated. Negative numbers will may also be a
part of the input.

## Infix to Postfix Translator

Your program should also be able to take an infix expression and convert it to postfix. In this case, a stack will still
be needed, but it will be used for operators rather than operands. Consider the following expression: a + b * c + (d *
e + f) * g. Here’s how we might change this to postfix:

|                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Read the a. Put into the output.<br><br>Read the +. Push onto the stack.<br><br>Read the b. Put into the output.<br><br>Read the *. Check the stack. Since + is lower precedence, push * onto the stack.<br><br>Read the c. Put into the output.<br><br>Read the +. Check the stack. * is higher precedence, so pop and add * to output. Since the +  <br>          (now on top of the stack) is not of lower precedence, pop that too and add to output. <br><br>          Push the  + that was just read from the input.<br><br>Read the (. Since this is highest precedence, place on stack.<br><br>Read the d. Put into the output.<br><br>Read the *. Push on the stack.<br><br>Read the e. Put into the output.<br><br>Read the +. Pop the * and push the +.<br><br>Read the f. Put into the output.<br><br>Read the ). Empty the stack back to the (.  (In this case, the + is added to the output.)<br><br>Read the *. Push onto the stack.<br><br>Read the g. Put into the output.<br><br>End of input, so pop the *, add to the output. Repeat with the + left in the stack. |

After this process, we should get the expression: a b c * + d e * f + g * +

Write a method (and whatever helper methods necessary) to translate to postfix. A call to the method will look like
following:`PIP.infixToPostFix(someStringExpression)`

## Extensions
Include a prefix or postfix to infix translator, an evaluator of prefix expressions or an infix to prefix translator

## Rubric

4 points – All requested items are present. Functional PIP class with the required methods. Javadoc reference for the class. Reflection questions were answered in the README

3 points – Some of the requested items are missing. Classes are complete but are missing Javadoc reference, etc.

2 points – Missing or incomplete. Student should re-attempt

---

# Style Guidelines

## Lab 6: Style Guide

### Comments

The expectations for Javadoc comments are the same as the previous
lab.  [Here is the IntelliJ  documentation](https://www.jetbrains.com/help/idea/javadocs.html) again if you need more
information. In addition to Javadoc comments, your code should include regular comments to explain anything that is not
immediately obvious.

# Data Structures Style Guide

In this course, we will not only practice writing code but how to write good code. Learning how to write good code
includes a number of stylistic conventions. As we move further into the course, the expectations for appropriate style
and documentation will become more extensive as we continue to practice. It is expected that you keep the style
guidelines introduced in previous assignments in mind during the current assignment. This document will include a
generic introduction to aspects of style relevant to this class in addition to specifics regarding this assignment.

## Types of Style Guidelines

There are six main categories of guidelines to look out for during this course.

### Formatting

Formatting refers to the way code is structured. This includes indentations, brackets, and whitespace. Using clear and
consistent formatting throughout makes writing and reading code easier. It is even more important when multiple people
are working on the same program. In IntelliJ, there are built-in formatting rules which you can apply by:

- Going in the _Code_ menu and selecting _Reformat Code_.
- Using the keyboard shortcut, which by default is Ctrl+Alt+L on Windows and Opt+Cmd+L on Mac.

### Comments

Comments are statements of code that are not executed by the compiler or interpreter. We use them to explain what
different pieces of do. Regardless of the complexity of the program, commenting all of your work appropriately is a good
habit to get into.

In general, your comments should:

- Be concise: only write as much as is necessary to convey relevant information
- Help the reader: write them with the intention of a third party using them to understand your code, especially if it
  is not immediately obvious
- Break the code into smaller units: Comments help separate code at logical breaks like the beginning of a loop, a new
  step in a larger calculation, or at the beginning of a function

Commenting can be used as part of an effective code writing strategy as well. Instead of commenting after the code is
written, try commenting before writing the code. By breaking down your program logically into smaller chunks and then
working on those small chunks individually, you can avoid some bugs and logical errors

#### Javadoc

Javadoc is a tool that generates Java code documentation in the HTML format from Java source code. The documentation is
formed from Javadoc comments that are usually placed above classes, methods, or fields.

### Naming

Naming variables, constants, functions, and classes is key to writing good code. Names should help the reader understand
what is going on in your program.

In general, names should be:

- Accurate and informative: Names should reflect the contents or purpose of the entity as much as possible
- Concise: Names should be as concise as possible without sacrificing the above bullet point too much. It's a balance.
- Consistent: Use consistent names and naming conventions throughout your programs. See the section below for more
  information about Java-specific conventions.

#### Java Conventions

In Java there are a few different conventions programmers use.

- For variables and functions, we will typically use camelCase
- For constants, we use CAPS_SNAKE_CASES
- For files, we use UpperCamelCase

### Maintainability

Maintainable code is easy to work on, update, and change without the original author needing to be present.
Maintainability is a broad catch-all category for other aspects of good code that make it easy for you and others on
your team to work on and debug code.

### Efficiency

It is important to not only write code that is correct, but efficiently uses resources (primarily memory and time). We
will talk about this more extensively later in the course, but is something to keep in mind. Efficient code is
increasing important as we write code to handle larger and larger inputs.

### Concision

Your code should be as concise as possible without sacrificing readability. Just like with commenting, this is a
balance.  