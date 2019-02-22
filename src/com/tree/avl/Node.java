package com.tree.avl;

public class Node<K>
{
  public K key;
  public Node<K> left, right;
  public Node<K> parent;            // you may use or ignore
  public int height;
  public int num;                   // you may use or ignore

  /* Various constructors for various styles of building new nodes. */

  public Node(K k) {
    this(null, k);
  }

  public Node(Node<K> p, K k) {
    this(p, null, k, null);
  }

  public Node(Node<K> l,    K k,    Node<K> r) {
    this(null, l, k, r);
  }

  public Node(           Node<K> p,
      Node<K> l,    K k,    Node<K> r) {
    key = k;
    parent = p;
    left = l;
    right = r;
    if (left != null) {
      left.parent = this;
    }
    if (right != null) {
      right.parent = this;
    }
    update();
  }

  /* And helpers for surgery. */

  // Set new left child (can be null) and updating fields (num, height)
  // assumes children's fields are correct
  public void link_left(Node<K> new_child) {
    left = new_child;
    if (new_child != null) {
      new_child.parent = this;
    }
    update();
  }

  // Set new right child (can be null) and updating fields (num, height)
  // assumes children's fields are correct
  public void link_right(Node<K> new_child) {
    right = new_child;
    if (new_child != null) {
      new_child.parent = this;
    }
    update();
  }

  // Refreshes num and height.
  public void update() {
    num = 1 + size(left) + size(right);
    height = 1 + Math.max(height(left), height(right));
  }

  /* Querying size and height. Takes Node parameter because it can be null. */

  public static <K> int size(Node<K> v) {
    return (v == null ? 0 : v.num);
  }

  public static <K> int weight(Node<K> v) {
    return (v == null ? 1 : v.num + 1);
  }

  public static <K> int height(Node<K> v) {
    return (v == null ? 0 : v.height);
  }

  /* This displays the tree in the format:
       (...left subtree...)key(...right subtree...)
     down to 10 levels.
     You may find it useful when debugging.
   */
  public String toString() {
    return toStringLevel(10);
  }

  /* This displays the tree in the format:
       (...left subtree...)key(...right subtree...)
     down to the level specified.
     You may find it useful when debugging.
   */
  public String toStringLevel(int lvl) {
    if (lvl == 0) {
      return "...";
    }
    String s = "";
    if (left != null) {
      s += '(' + left.toStringLevel(lvl - 1) + ')';
    }
    s += key.toString();
    if (right != null) {
      s += '(' + right.toStringLevel(lvl - 1) + ')';
    }
    return s;
  }
}
