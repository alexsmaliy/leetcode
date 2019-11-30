Problem Statement
-----------------

Given a non-empty string _s_ and a dictionary _wordDict_ containing a list of
non-empty words, add spaces in _s_ to construct a sentence where each word is a
valid dictionary word. Return all such possible sentences.

Note:
- The same word in the dictionary may be reused multiple times in the
  segmentation.
- You may assume the dictionary does not contain duplicate words.

*Difficulty: hard.*

Discussion
----------

This is a natural extension of the earlier [Word Break I](../leet139). The
strategy we pursued in that problem involved iterating over the input string and
marking possible positions for word breaks. The entire string could be split
into tokens from the word dictionary if we managed to reach the end of the input
and put a word break there.

In particular, the strategy was as follows:
1. Initialize a tracker (such as a boolean array) as long as the input string.
1. Check if the string starts with any valid words. Mark every tracker position
   where a valid word ends. (Example: mark positions 3 and 8 if the input is
   "pineappleXXXX" and the word list includes "pine" and "pineapple.")
1. Iterate from 0 to the end of the string. If the current cursor position has
   been marked as a potential word break, check if any substrings just past the
   potential word break are valid words in our word dictionary. You can reduce
   the number of checks by keeping track of word _lengths_ to check. Mark word
   breaks if a substring starting from the current position is a valid word.
   It is easy to make off-by-one errors here.
1. If you managed to reach the end of the input, you have found a valid way to
   partition it into words from the dictionary.

We now extend this problem. We want to recover all valid partitions of the input
string, not just whether one exists.

To accomplish this, we modify our tracker to keep track of the lengths of words
that end at a given position in the string, not just whether a word break can be
placed there. We do a forward sweep, exactly as outlined above, to identify the
word breaks and determine whether the string can be validly divided at all.

Then we begin at the end and recurse backwards, jumping from word break to word
break, accumulating a collection of valid lists of tokens. Recursive calls
return when they reach the beginning of the string. We know they will reach the
beginning, because we are only using the valid word breaks that we identified in
the forward pass.
<pre>string = "catsanddogs"
dict = ["and", "cat", "cats", "dogs", "sand"]

Forward pass: [ C A T S A N D D O G S ]
                    &uarr; &uarr;     &uarr;       &uarr;
                    3 4    3,4      4
Backwards pass:

             ┌ &rarr; "and dogs" &rarr; "cats and dogs" &rarr; RETURN!
"" &rarr; "dogs" &rarr;
             └ &rarr; "sand dogs" &rarr; "cat sand dogs" &rarr; RETURN!</pre>
