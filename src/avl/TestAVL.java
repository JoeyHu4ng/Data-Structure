package avl;

public class TestAVL {

  public static void main(String[] argv) {

    AVL T = new AVL();
    T.root = mknode(mknode(mkleaf(13), 14, null), 16, mknode(mkleaf(18), 25, mkleaf(28)));

    AVL V = new AVL();
    V.root = mknode(mknode(mkleaf(13), 14, mkleaf(16)), 18, mknode(null, 25, mkleaf(28)));

    System.out.println(AVL.equalSet(T, V));

  }

  // Shorthand for one of the Node constructors.
  private static <T> Node<T> mknode(Node<T> left, T a, Node<T> right) {
    return new Node<T>(left, a, right);
  }

  // Shorthand for another Node constructor.
  private static <T> Node<T> mkleaf(T a) {
    return new Node<T>(a);
  }

}
