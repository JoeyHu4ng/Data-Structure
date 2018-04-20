package graph;

public interface Edge {

  /**
   * get the first node of the Edge.
   * @return the first Node.
   */
  public Node getFirstNode();
  
  /**
   * get the second node of the Edge.
   * @return the second Node.
   */
  public Node getSecondNode();
  
  /**
   * returns the position of Node a in the Edge.
   * @param a the Node to find
   * @return 1 if first Node, 2 if second Node, 0 if not included.
   */
  public int nodePosition(Node a);

  /**
   * compares two Edges and says if their elements are the same (order agnostic).
   * @param edge the Edge to be compared
   * @return true if the two id's are same as this Edge's two id's.
   */
  public boolean compareOrderAgnostic(Edge edge);
  
  /**
   * see if two edges are the same.
   * @param edge to be compared to.
   * @return true if firstNode of edge and secondNode of edge are equal to this edge's.
   */
  public boolean compare(Edge edge);
}
