Problem Statement
-----------------

Serialization is the process of converting a data structure or object into a
sequence of bits so that it can be stored in a file or memory buffer, or
transmitted across a network connection link to be reconstructed later in the
same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no
restriction on how your serialization/deserialization algorithm should work. You
just need to ensure that a binary tree can be serialized to a string and this
string can be deserialized to the original tree structure.

Note: Do not use class member/global/static variables to store states. Your
serialize and deserialize algorithms should be stateless.

*Difficulty: hard.*

Discussion
----------

I'm not sure why this is categorized as a hard problem. The user is invited to
design a disk storage format for integer-containing BSTs. My dead-simple
submission using preorder traversal and recursion managed to beat 98% of other
submissions (as of 2019-10-18). Maybe other users are experimenting a lot with
this question?

The encoder traverses the tree recursively, depth-first, and turns every triplet
of parent, left child, and right child into "parent encode(left child)
encode(right child)." I used a one-character placeholder to indicate null
children, to distinguish a parent with just a left child from a parent with just
a right child.
<pre><tt>       1
      / \
     2   3
    /         ===>  "1 2 . . 3 4 5 . . 6 . . ."
   4
  / \
 5   6
</tt></pre>
The decoder is also dead-simple. It parses `.` into null, and everything else
into "integer parse(child) parse(child)." One could parse integer values using
whatever facilities your language offers. In Java, you could skip until the next
space in the encoded string and parse the substring using `Integer.parseInt()`.
Alternatively, you could implement it by hand, which seems to be almost as fast.

I did encounter one surprising thing. Namely, when I timed the performance of
my encoder and decoder, I discovered that encoding was about 15 times slower
than decoding. I had written it as a recursive call to `String.format()`:
```java
String encoded = root == null
    ? "."
    : String.format(
        "%d %s %s",
        root.val,
        codec.serialize(root.left),
        codec.serialize(root.right));
```
This was basically the entire "encoder." Turns out `String.format()` is
dramatically slower than using a `StringBuilder`.
