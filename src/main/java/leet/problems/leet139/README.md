Problem Statement
-----------------

Given a non-empty string *s* and a dictionary `wordDict` containing a list of
non-empty words, determine if *s* can be segmented into a space-separated
sequence of one or more dictionary words.

Note:
- The same word in the dictionary may be reused multiple times in the
  segmentation.
-  You may assume the dictionary does not contain duplicate words.

*Difficulty: medium.*

Discussion
----------
 The task is to verify whether a string can be tokenized into valid substring
 tokens.

 The bottom-up approach makes intuitive sense here. Let's suppose we know that
 we can break *s* between 0 and some *i* into tokens. Then we just need to check
 whether any valid tokens begin at *i*, and mark those offsets tokenized as
 well.

 We track whether we can tokenize *s* from 0 to *i* in a boolean array of length
 |&thinsp;*s*&thinsp;|. We set the array to true at every position where the
 first word break could be placed. (If there aren't any, we could just abort.)
 Then we iterate over the array. The *i*-th position is true in one of two
 cases:
 1. We have already set it to true earlier. For example, if we parse
    *pineapple*, advancing past *pine* and checking again for *apple* is
    redundant.
 1. A valid token ends at *i*, and the preceding prefix is completely
    tokenizable.

 For any given *i*, we only need to look as far ahead as the length of the
 longest word in the dictionary. We could even keep a list of the exact word
 lengths to avoid checing substrings that cannot be valid words, or even keep
 the words in a prefix tree, but that seems like overkill.
 <pre><tt>Words: and, cat, cats, dog, dogs, sand

[ c a t s a n d o g s ]
[ - - T T - - - - - - ] "c" and "ca" are not words, but "cat" and "cats" are.
  i = 0
[ - - T T - - - - - - ] Prefix "c" was not tokenizable.
    i = 1
[ - - T T - - - - - - ] Prefix "ca" was not tokenizable.
      i = 2
[ - - T T - - T - - - ] Found "sand" after "cat."
        i = 3
[ - - T T - - T - - - ] Redundantly found "and" after "cats."
          i = 4
[ - - T T - - T - - - ] Prefix "catsa" was not tokenizable.
            i = 5
[ - - T T - - T - - - ] Prefix "catsan" was not tokenizable.
              i = 6
[ - - T T - - T - - - ] Prefix "catsand" was marked tokenizable, but "og" and "ogs" aren't valid words.
                i = 7
[ - - T T - - T - - - ] Prefix "catsando" was not tokenizable.
                  i = 8
[ - - T T - - T - - - ] Prefix "catsandog" was not tokenizable.
                    i = 9
Verdict:not tokenizable.
</tt></pre>
