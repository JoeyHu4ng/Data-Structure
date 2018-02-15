package graph;

public class Edge {

  public Node start;
  public Node end;

  public Integer cost;

  public Edge() {}

  public Edge(Node start, Node end) {
    setStart(start);
    setEnd(end);
  }

  public Edge(Node start, Node end, Integer cost) {
    setStart(start);
    setEnd(end);
    setCost(cost);
  }

  public void setCost(Integer cost) {
    this.cost = cost;
  }

  public void setEnd(Node end) {
    this.end = end;
  }

  public void setStart(Node start) {
    this.start = start;
  }

  public Integer getCost() {
    return cost;
  }

  public Node getEnd() {
    return end;
  }

  public Node getStart() {
    return start;
  }

  public Integer getPosition(Node n) {
    return start.equals(n) ? 0 : 1;
  }

  @Override
  public String toString() {
    return "(" + getStart() + ")--" + getCost() + "--(" + getEnd() + ")";
  }

}
