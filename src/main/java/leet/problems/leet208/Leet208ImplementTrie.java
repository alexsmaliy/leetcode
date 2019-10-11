package leet.problems.leet208;

public class Leet208ImplementTrie {

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    class Trie {

        Trie f;
        Trie t;

        /** Initialize your data structure here. */
        public Trie() {
            this.t = null;
            this.f = null;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            boolean[] b = reduce(word);
            Trie t = this;
            for (int i = 0; i < b.length; i++) {
                if (b[i]) {
                    if (t.t == null) {
                        t.t = new Trie();
                    }
                    t = t.t;
                } else {
                    if (t.f == null) {
                        t.f = new Trie();
                    }
                    t = t.f;
                }
            }
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return searchInternal(word, 0);
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return searchInternal(prefix, 8);
        }

        private boolean searchInternal(String s, int offsetFromEnd) {
            boolean[] b = reduce(s);
            Trie t = this;
            for (int i = 0; i < b.length - offsetFromEnd; i++) {
                if (b[i]) {
                    if (t.t == null) {
                        return false;
                    }
                    t = t.t;
                } else {
                    if (t.f == null) {
                        return false;
                    }
                    t = t.f;
                }
            }
            return true;
        }

        private boolean[] reduce(String s) {
            int sl8 = s.length() * 8;
            boolean[] b = new boolean[sl8 + 8];
            for (int i = 0; i < s.length(); i++) {
                int i8 = i * 8;
                char ith = s.charAt(i);
                b[i8    ] = (ith &   1) != 0;
                b[i8 + 1] = (ith &   2) != 0;
                b[i8 + 2] = (ith &   4) != 0;
                b[i8 + 3] = (ith &   8) != 0;
                b[i8 + 4] = (ith &  16) != 0;
                b[i8 + 5] = (ith &  32) != 0;
                b[i8 + 6] = (ith &  64) != 0;
                b[i8 + 7] = (ith & 128) != 0;
            }
            b[sl8    ] = false;
            b[sl8 + 1] = false;
            b[sl8 + 2] = false;
            b[sl8 + 3] = false;
            b[sl8 + 4] = false;
            b[sl8 + 5] = false;
            b[sl8 + 6] = false;
            b[sl8 + 7] = false;
            return b;
        }

        private String unreduce(boolean[] b) {
            char[] c = new char[(b.length >> 3) - 1];
            for (int i = 0; i < c.length; i++) {
                int i8 = i * 8;
                c[i] = (char)
                    ((b[i8    ] ?   1 : 0)
                        + (b[i8 + 1] ?   2 : 0)
                        + (b[i8 + 2] ?   4 : 0)
                        + (b[i8 + 3] ?   8 : 0)
                        + (b[i8 + 4] ?  16 : 0)
                        + (b[i8 + 5] ?  32 : 0)
                        + (b[i8 + 6] ?  64 : 0)
                        + (b[i8 + 7] ? 128 : 0));
            }
            return String.valueOf(c);
        }
    }
}
