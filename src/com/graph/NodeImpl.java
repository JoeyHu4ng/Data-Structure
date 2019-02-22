package com.graph;

public class NodeImpl implements Node {

  // initiate nodeId
  private int nodeId;

  /**
   * This is the potentially constructor which initiate nodeId based on the given int value.
   * 
   * @param nodeId the ID to be com.set for this Node.
   */
  public NodeImpl(int nodeId) {
    this.nodeId = nodeId;
  }

  /**
   * This is a constructor for some need such as in UndirectedGraphImpl and DirectedGraphImpl.
   * Sometimes, when initializing a Node without give a value will use it. This constructor does
   * nothing.
   */
  NodeImpl() {}

  /**
   * The function is used to get nodeId.
   * 
   * @return the nodeId of the node.
   */
  @Override
  public int getId() {
    return nodeId;
  }

  /**
   * The function is used to compare nodeId between given Node n and this Node.
   * 
   * @param n the node to be compared to.
   * @return true if nodes have same nodeId, false otherwise.
   */
  @Override
  public boolean equals(Node n) {
    // if n is a null type, just return false
    if (n == null) {
      return false;
    }
    // initiate the result and get the value
    boolean result = n.getId() == nodeId;
    // return the result
    return result;
  }

}
