package leet.problems.leet212;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leet212WordSearchIi {
    public static List<String> findWords(char[][] board, String[] words) {
        // Build prefix tree.
        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i]);
        }

        // It's silly, but LeetCode is strangely sensitive to new object creation.
        // We create these once and then call clear() after use.
        List<String> foundWords = new ArrayList<>();
        Set<String> searchResults = new HashSet<>();

        // Iterate over the entire board position by position.
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                // Search each position.
                searchResults =
                    search(board, r, c, board.length, board[0].length,
                        trie.children[board[r][c] - 'a'], searchResults);
                foundWords.addAll(searchResults);

                // Remove dictionary words that we have already located.
                for (String word : searchResults) {
                    trie.delete(word);
                }

                // Clear the search result holder for the next iteration.
                searchResults.clear();
            }
        }

        return foundWords;
    }

    private static Set<String> search(char[][] board,
                                      int r,
                                      int c,
                                      int nrow,
                                      int ncol,
                                      Trie trie,
                                      Set<String> bag) {
        // No word in the dictionary has the prefix we have traversed.
        if (trie == null) return bag;
        // A word in the dictionary is equal to the prefix we have traversed.
        // Add it to the results by traversing up the parent chain.
        if (trie.children[Trie.TERMINAL - 'a'] != null) {
            StringBuilder sb = new StringBuilder();
            Trie t = trie;
            while (t.parent != null) {
                sb.append(t.myChar);
                t = t.parent;
            }
            bag.add(sb.reverse().toString());
        }
        // Tag this cell of the board as visited by the current search.
        board[r][c] *= -1;
        // Visit each valid neighbor, up to all 4. The `(char)(-'z')` is because
        // Java chars are unsigned, so `-'z'` just wraps around to 2^16 - 'z'.
        if (r - 1 >= 0 && board[r - 1][c] < (char)(-'z')) {
            bag = search(board, r - 1, c, nrow, ncol,
                trie.children[board[r - 1][c] - 'a'], bag);
        }
        if (r + 1 < nrow && board[r + 1][c] < (char)(-'z')) {
            bag = search(board, r + 1, c, nrow, ncol,
                trie.children[board[r + 1][c] - 'a'], bag);
        }
        if (c - 1 >= 0 && board[r][c - 1] < (char)(-'z')) {
            bag = search(board, r, c - 1, nrow, ncol,
                trie.children[board[r][c - 1] - 'a'], bag);
        }
        if (c + 1 < ncol && board[r][c + 1] < (char)(-'z')) {
            bag = search(board, r, c + 1, nrow, ncol,
                trie.children[board[r][c + 1] - 'a'], bag);
        }
        board[r][c] *= -1;
        return bag;
    }

    // A prefix tree that has a pointer up to its parent and knows its own character, so we
    // can reassemble the prefix by traversing parent pointers. Also keeps track of the
    // number of its immediate children, so deletion can clean up after itself.
    private static final class Trie {
        private static final char TERMINAL = 'z' + 1;

        private final Trie parent;
        private final char myChar;
        private final Trie[] children;
        private int nonEmptyChildren;

        public Trie() {
            this(null, (char) 0);
        }

        private Trie(Trie parent, char myChar) {
            this.parent = parent;
            this.myChar = myChar;
            this.children = new Trie[27];
        }

        public boolean contains(String word) {
            return contains(word, word.length(), 0);
        }

        private boolean contains(String word, int length, int cursor) {
            if (cursor == length) return children[TERMINAL - 'a'] != null;
            if (children[word.charAt(cursor) - 'a'] == null) {
                return false;
            }
            return children[word.charAt(cursor) - 'a'].contains(word, length, cursor + 1);
        }

        public void insert(String word) {
            Trie trie = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (trie.children[c - 'a'] == null) {
                    trie.nonEmptyChildren++;
                    trie.children[c - 'a'] = new Trie(trie, c);
                }
                trie = trie.children[c - 'a'];
            }
            if (trie.children[TERMINAL - 'a'] == null) {
                trie.nonEmptyChildren++;
                trie.children[TERMINAL - 'a'] = new Trie(trie, TERMINAL);
            }
        }

        public void delete(String word) {
            delete(word, word.length(), 0);
        }

        private boolean delete(String word, int length, int cursor) {
            if (cursor == length && children[TERMINAL - 'a'] != null) {
                children[TERMINAL - 'a'] = null;
                nonEmptyChildren--;
                return true;
            }
            Trie trieForChar = children[word.charAt(cursor) - 'a'];
            if (trieForChar == null) {
                return false;
            }
            boolean ret = trieForChar.delete(word, length, cursor + 1);
            if (trieForChar.nonEmptyChildren == 0) {
                // Silly, but not that `trieForChar = null` would not accomplish what we want,
                // which is to break the parent-child link.
                children[word.charAt(cursor) - 'a'] = null;
                nonEmptyChildren--;
            }
            return ret;
        }
    }
}
