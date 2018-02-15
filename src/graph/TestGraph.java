package graph;

public class TestGraph {

  public static void main(String[] argv) {
    Node a = new Node(0);
    Node b = new Node(1);
    Edge ab = new Edge(a, b, 5);
    System.out.println(ab);
  }

}
