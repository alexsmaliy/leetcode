Problem Statement
-----------------

Given a 2D board and a list of words from the dictionary, find all words in the
board.

Each word must be constructed from letters of sequentially adjacent cell, where
"adjacent" cells are those horizontally or vertically neighboring. The same
letter cell may not be used more than once in a word.

Note:
1. All inputs are consist of lowercase letters a-z.
1. The values of words are distinct.

*Difficulty: hard.*

Discussion
----------

Here's one solution that's not very complicated conceptually:
- Iterate over the board character by character.
- Find all words in the dictionary that start with that character.
- For each word that could start at this point, DFS the board for that word,
  starting from the current character.

The recursion depth of the DFS is limited by the length of the word being
checked, which seems safe enough.

Because we only have to locate a word once for it to count, we can save a little
time by removing it from the dictionary if we ever locate it.

If multiple words in the dictionary begin with the current character, we would
search once for every possible word. A more complicated optimization is to
search using a prefix tree (trie), in order to find every dictionary word that
begins from the current position.

Every search would then return a collection of results. The search backtracks if
the prefix tree has noting for the current character. When we fan out to the
neighbors of the current point, we pass prefixes of the tree into each of the
recursive calls. All the searches can just pass a single set-like object around
to collect found words.
