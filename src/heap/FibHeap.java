package heap;

import java.util.ArrayList;
import java.util.List;

public class FibHeap {

  private class Node {

    private Integer key;
    private Integer degree;
    private boolean marked;
    private Node parent;
    private List<Node> children;

    Node(Integer key) {
      this.key = key;
      degree = 0;
      marked = false;
      parent = null;
      children = new ArrayList<>();
    }

    void setChild(Integer index, Node node) {
      if (index <= degree++) {
        children.add(index, node);
        node.parent = this;
      }
    }

    Node removeChild() {
      return children.get(--degree);
    }

    @Override
    public String toString() {
      return key.toString();
    }

  }

  private List<Node> list;
  private Node min;

  FibHeap() {
    list = new ArrayList<>();
    min = null;
  }

  /**
   * The function adds the new key into the root list.
   * @param key is the new key to add
   */
  public void insert(Integer key) {
    Node newNode = new Node(key);
    list.add(newNode);
    if (min == null)
      min = newNode;
    else if (min.key > key)
      min = newNode;
  }

  /**
   * The function will returns the min Node and then consolidate the heap.
   * @return the Node with the min key value
   */
  public Node extractMin() {
    if (isEmpty())
      return null;
    Node res = min;
    int degree;
    for (degree = min.degree; degree > 0; degree--) {
      Node child = min.removeChild();
      list.add(child);
    }
    list.remove(res);
    if (!list.isEmpty())
      consolidate();
    return res;
  }

  /**
   * The function is a private help function for extract_min.
   */
  private void consolidate() {
    int i;
    Node[] A = new Node[1000];
    for (i = 0; i < list.size(); i++) {
      Node x = list.get(i);
      while (A[x.degree] != null) {
        Node y = A[x.degree];
        A[x.degree] = null;
        if (x.key > y.key) {
          Node tmp = x;
          x = y;
          y = tmp;
        }
        list.remove(y);
        x.setChild(x.degree, y);
        y.marked = false;
        i--;
      }
      A[x.degree] = x;
    }
    min = list.get(0);
    for (Node x : list) {
      if (x.key < min.key)
        min = x;
    }
  }

  /**
   * The function will change the priority of the input key,
   * and then cuts all its parent which are makred until the
   * root.
   * @param key is the key to decrease the priority
   */
  public void decreasePriority(Integer key) {
    //TODO: find the key and "cascading cuts"
  }

  /**
   * The function will test if the heap is empty.
   * @return if the heap is empty
   */
  public boolean isEmpty() {
    return list.isEmpty();
  }

}
