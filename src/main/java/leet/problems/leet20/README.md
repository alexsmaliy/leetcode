Problem Statement
-----------------

Given a string containing just the characters `(`, `)`, `{`, `}`, `[` and `]`,
determine if the input string is valid. An input string is valid if:
  1. Open brackets must be closed by the same type of brackets.
  2. Open brackets must be closed in the correct order.

Note that an empty string is also considered valid.

*Difficulty: easy.*

Discussion
----------

I think this is just one of those things you know, and it's easy if you know it.
It's a common textbook illustration of what a stack (the data structure) can be
used for, but I'm sure it comes up in other contexts as well.

Use a stack. If your language offers a stack implementation, use that. Or just
fake a stack using an array or list-like structure and keep track of where the
leading edge is.

Iterate over the characters in the input in order. If a given
character is some kind of open parens, put it on the stack. If it's some kind of
closing parens, check the most recent character on the stack. If it's the
matching open parens, pop the item from the stack (or decrement the cursor)
&mdash; you have closed a set of parentheses.

If the stack is not empty by the time you're done with the string, the string is
not valid (because some sets of parens were not closed). Alternatively, if you
find a closing parens, but the stack is empty, the string is not valid either.

In the context of Java, you may want `ArrayDeque<Character>` or
`LinkedList<Character>`, but note that boxing and unboxing `char` and
`Character` will come at a cost. If using an array of characters, it's easiest
to preallocate it to be the same length as the input string.

You can probably find more shortcuts. For example, you could declare the string
invalid if the stack grows to be half the size of the string: there's not enough
characters left in the string to close all the open parens. But that's a silly
way to use your time.
