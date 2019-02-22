package com.graph;

public class EdgeImpl implements Edge {

  // create firstNode and secondNode in Edge
  private Node firstNode;
  private Node secondNode;

  /**
   * The function is the potentially constructor which initiated listOfNode based on two given
   * Nodes.
   * 
   * @param firstNode first node to com.set.
   * @param secondNode second node to com.set.
   */
  public EdgeImpl(Node firstNode, Node secondNode) {
    // assign two input into global variables in Edge
    this.firstNode = firstNode;
    this.secondNode = secondNode;
  }

  /**
   * The function is used to get the first Node in listOfNode.
   * 
   * @return the first node in listOfNode.
   */
  @Override
  public Node getFirstNode() {
    return firstNode;
  }

  /**
   * The function is used to get the second Node in listOfNode.
   * 
   * @return the second node in listOfNode.
   */
  @Override
  public Node getSecondNode() {
    return secondNode;
  }

  /**
   * The function is used to get the position, 1 or 2, for given Node is it exists.
   * 
   * @param a is the node to find.
   * @return if a is first node return 1, if a is second node return 2, 0 otherwise.
   */
  @Override
  public int nodePosition(Node a) {
    // using node method to compare parameter a with nodes in edge
    // return 1 if a equals to first node, return b if a equals to second one, otherwise return 0
    if (firstNode.equals(a)) {
      return 1;
    } else if (secondNode.equals(a)) {
      return 2;
    } else {
      return 0;
    }
  }

  /**
   * The function is used to compare given edge and this edge.
   * 
   * @param edge the Edge to compare.
   * @return true if two edges have same nodes, false otherwise.
   */
  @Override
  public boolean compareOrderAgnostic(Edge edge) {
    // initiate result to return
    boolean result = false;
    // If all nodes in parameter edge are in this.listOfNode, then convert result to true.
    // it can only be two situation, one is same order, one is not same order
    if (this.compare(edge)
        || (firstNode.equals(edge.getSecondNode()) && secondNode.equals(edge.getFirstNode()))) {
      result = true;
    }
    // return the result
    return result;
  }

  /**
   * The function is used to compare given edge and this edge with directions.
   * 
   * @param edge the Edge to compare.
   * @return true if two edges have same nodes and directions, false otherwise.
   */
  @Override
  public boolean compare(Edge edge) {
    // initiate result to return
    boolean result = false;
    // if two edges have the same nodes with order, then convert result to true
    if (firstNode.equals(edge.getFirstNode()) && secondNode.equals(edge.getSecondNode())) {
      result = true;
    }
    // return the result
    return result;
  }

}
