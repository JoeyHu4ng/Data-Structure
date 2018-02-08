package avl;

public class AVL<K extends Comparable<K>> {
  Node<K> root;

  /**
   * This algorithm is based on the union algorithm taught in class.
   * This algorithm will use split and join from union algorithm.
   * The split will return three values T < k, b, and T > k, so if split, which
   * is called by compareAVL, also return b = true, then T will have all V's keys.
   * After that we just need to make sure they have the same length, then
   * they will be same tree; therefore, it takes O(m*lg(n/m + !)) as union did.
   * @param T is the AVL to compare.
   * @param V is the AVL to compare with.
   * @param <K> is key which is comparable.
   * @return true if T and V have same keys, false otherwise.
   */
  public static <K extends Comparable<K>> boolean equalSet(AVL<K> T, AVL<K> V) {
    // check for num of nodes and call helper function
    return Node.size(T.root) == Node.size(V.root) && compareAVL(T.root, V.root);
  }

  /**
   * This class represents a set of three values which are Node, boolean, and Node.
   * This is created to avoid unchecked casting warning by using list because
   * list must has same, but by dong so, and cast it to its correct type, compiler
   * will give unchecked casting warning, so this class is needed here.
   * @param <K> is the comparable key.
   */
  public static class Set<K extends Comparable<K>> {

    Node<K> left;
    boolean b;
    Node<K> right;

    void setB(boolean b) {
      this.b = b;
    }

    void setRight(Node<K> right) {
      this.right = right;
    }

    void setLeft(Node<K> left) {
      this.left = left;
    }

    Node<K> getLeft() {
      return left;
    }

    Node<K> getRight() {
      return right;
    }

    boolean getB() {
      return b;
    }
  }

  /**
   * The function compares two Nodes to check whether they have same keys.
   */
  private static <K extends Comparable<K>> boolean compareAVL(Node<K> T, Node<K> V) {
    // check for boundary cases
    if (T == null && V == null)
      return true;
    if (T == null || V == null)
      return false;

    // check for other left and right child
    Set<K> result = split(T, V.key);
    if (result.getB()) {
      if (compareAVL(result.getLeft(), V.left))
        return compareAVL(result.getRight(), V.right);
    }
    return false;
  }

  /**
   * The function from union algorithm which taught in class.
   */
  private static <K extends Comparable<K>> Set<K> split(Node<K> T, K key) {
    Set<K> result = new Set<>();
    if (T == null) {
      result.setLeft(null);
      result.setB(false);
      result.setRight(null);
      return result;
    }

    int compare = key.compareTo(T.key);
    if (compare < 0) {
      Set<K> subResult = split(T.left, key);
      Node<K> r = join(subResult.getRight(), T.key, T.right);
      result.setLeft(subResult.getLeft());
      result.setB(subResult.getB());
      result.setRight(r);
    } else if (compare > 0) {
      Set<K> subResult = split(T.right, key);
      Node<K> l = join(T.left, T.key, subResult.getLeft());
      result.setLeft(l);
      result.setB(subResult.getB());
      result.setRight(subResult.getRight());
    } else {
      result.setLeft(T.left);
      result.setB(true);
      result.setRight(T.right);
    }
    return result;
  }

  /**
   * The function from union algorithm which taught in class.
   * @param Lt is the tree has small keys.
   * @param key is the key which Lt, Gt compare to.
   * @param Gt is the tree has large keys.
   * @param <K> is key which is comparable.
   * @return the join of Lt, new key, and Gt.
   */
  private static <K extends Comparable<K>> Node<K> join(Node<K> Lt, K key, Node<K> Gt) {
    if (Node.height(Lt) > Node.height(Gt) + 1)
      return joinRight(Lt, key, Gt);
    else if (Node.height(Lt) + 1 < Node.height(Gt))
      return joinLeft(Lt, key, Gt);
    else {
      return new Node<>(Lt, key, Gt);
    }
  }

  /**
   * One case of join function, which height of right is larger.
   */
  private static <K extends Comparable<K>> Node<K> joinLeft(Node<K> Lt, K key, Node<K> Gt) {
    Node<K> keyNode;
    if (Node.height(Gt.left) == Node.height(Gt.right) + 1) {
      keyNode = new Node<>(Gt.left.left, key, Lt);
      Node<K> root = Gt.left;
      Gt.left = root.right;
      root.right = Gt;
      root.left = keyNode;

      keyNode.update();
      root.update();
      root.left.update();

      return root;
    } else {
      keyNode = new Node<>(Lt, key, Gt.left);
      Gt.left = keyNode;

      keyNode.update();
      Gt.update();

      return Gt;
    }
  }

  /**
   * Another case of join function, which height of left is large.
   */
  private static <K extends Comparable<K>> Node<K> joinRight(Node<K> Lt, K key, Node<K> Gt) {
    Node<K> keyNode;
    if (Node.height(Lt.right) == Node.height(Lt.left) + 1) {
      keyNode = new Node<>(Lt.right.right, key, Gt);
      Node<K> root = Lt.right;
      Lt.right = root.left;
      root.left = Lt;
      root.right = keyNode;

      keyNode.update();
      root.update();
      root.right.update();

      return root;
    } else {
      keyNode = new Node<>(Lt.right, key, Gt);
      Lt.right = keyNode;

      keyNode.update();
      Lt.update();

      return Lt;
    }
  }

}
