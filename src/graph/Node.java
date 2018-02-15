package graph;

public class Node {

  public Integer id;

  public Node() {}

  public Node(Integer id) {
    setID(id);
  }

  public void setID(Integer id) {
    this.id = id;
  }

  public Integer getID() {
    return id;
  }

  public boolean equals(Node n) {
    return (n != null) && id.equals(n.getID());
  }

  @Override
  public String toString() {
    return getID() + "";
  }

}
