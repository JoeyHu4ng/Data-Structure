package com.graph;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraphImpl implements Graph, Reversible {

  private List<Node> nodeList = new ArrayList<>();
  private List<Edge> edgeList = new ArrayList<>();

  /**
   * Change directions of all the nodes in the graph.
   */
  @Override
  public void reverse() {
    // create a new Edge ArrayList to store reversed Edges
    List<Edge> newEdgeList = new ArrayList<>();
    // loop through each Edge in edgeList
    for (Edge edge : edgeList) {
      // create a new Edge with reversed direction and add into newEdgeList
      Edge newEdge = new EdgeImpl(edge.getSecondNode(), edge.getFirstNode());
      newEdgeList.add(newEdge);
    }
    // let this.edgeList pointer to reversed Edge ArrayList
    this.edgeList = newEdgeList;
  }

  /**
   * The function is used to add an edge between two nodes, a and b, with directions.
   * 
   * @param a is the first node.
   * @param b is the second node.
   * @return true if edge added, false if edge was already there.
   * @throws NodeNotFoundException if either a or b are not in the graph, throw this Exception.
   */
  @Override
  public boolean addEdge(Node a, Node b) throws NodeNotFoundException {
    // check if either a or b is null or they are equal, if it is, return false
    if (a == null || b == null || a.equals(b)) {
      return false;
    }
    // check if both two nodes are in the nodeList
    if ((!contains(a)) || (!contains(b))) {
      // if either a or b not in nodeList, then throw the NodeNotFoundException
      throw new NodeNotFoundException();
    }
    
    // initiate the result to return
    boolean result = false;
    // create a new edge newEdge to compare with edges in edgeList
    Edge newEdge = new EdgeImpl(a, b);
    // initiate isInEdgeList to false
    boolean isInEdgeList = false;
    // loop through each edge in edgeList to check if newEdge has been in edgeList
    for (Edge edge : edgeList) {
      // determine if the edge is already there
      if (edge.compare(newEdge)) {
        isInEdgeList = true;
      }
    }

    // if newEdge has not been in edgeList, then add it into edgeList and convert result to true
    if (!isInEdgeList) {
      edgeList.add(newEdge);
      result = true;
    }
    return result;
  }

  /**
   * Add the node a into graph.
   * 
   * @param a is the node to add.
   * @return true if a is added, false if a was already there.
   */
  @Override
  public boolean addNode(Node a) {
    // initiate the result to return
    boolean result = false;
    // if a is not null and nodeList does not already have a, then add a into nodeList
    if ((a != null) && (!contains(a))) {
      nodeList.add(a);
      // convert the result to true
      result = true;
    }
    return result;
  }

  /**
   * Remove the edge between two edges which are a and b with directions.
   * 
   * @param a is the first edge.
   * @param b is the second edge.
   * @return true if removed the edge, false if the edge wasn't already there.
   * @throws NodeNotFoundException if either a or b are not in the graph, throw this Exception.
   */
  @Override
  public boolean removeEdge(Node a, Node b) throws NodeNotFoundException {
    // check if either a or b is null, if it is, return false
    if (a == null || b == null) {
      return false;
    }
    // check if both two nodes are in the nodeList
    if ((!contains(a)) || (!contains(b))) {
      // if either a or b not in nodeList, then throw the NodeNotFoundException
      throw new NodeNotFoundException();
    }

    // Initiate result to return
    boolean result = false;
    // create a new edge newEdge to compare with edges in edgeList
    Edge newEdge = new EdgeImpl(a, b);
    // initiate isInEdgeList to false, and index
    boolean isInEdgeList = false;
    int index = 0;
    // loop through each edge in edgeList
    for (Edge edge : edgeList) {
      // if edge is already in edgeList then get the index of it and change isInEdgeList to true
      if (edge.compare(newEdge)) {
        isInEdgeList = true;
        index = edgeList.indexOf(edge);
      }
    }

    // if edge is in edgeList, then remove it and convert the result to true
    if (isInEdgeList) {
      this.edgeList.remove(index);
      result = true;
    }
    return result;
  }

  /**
   * Determine if given node a is in the graph.
   * 
   * @param a is the node to check.
   * @return true if a is in the graph, false otherwise.
   */
  @Override
  public boolean contains(Node a) {
    // check if a is null, if it is, return false
    if (a == null) {
      return false;
    }
    // initiate inInNodeList to return and index for loop
    boolean isInNodeList = false;
    int index = 0;
    // loop through each node in nodeList
    while ((index < size()) && (!isInNodeList)) {
      // check if any of them equals to a
      if (nodeList.get(index).equals(a)) {
        // if it is then convert isInNodeList to true
        isInNodeList = true;
      }
      index++;
    }
    return isInNodeList;
  }

  /**
   * Get the number of how many nodes in the graph.
   * 
   * @return the size of nodeList which have all the nodes in graph.
   */
  @Override
  public int size() {
    return nodeList.size();
  }

  /**
   * Get a List which contains only nodes are directly relevant to the given node a, and all the
   * nodes which are the same will only appear once.
   * 
   * @param a is the node to compare.
   * @return a List contains all the nodes which are directly relevant to a.
   * @throws NodeNotFoundException if either a or b are not in the graph, throw this Exception.
   */
  @Override
  public List<Node> getConnectedNodes(Node a) throws NodeNotFoundException {
    // check if Node a in the nodeList
    if (!contains(a)) {
      // if either a or b not in nodeList, then throw the NodeNotFoundException
      throw new NodeNotFoundException();
    }

    // initiate a List of Node to store all connected Node
    List<Node> connectedNodes = new ArrayList<>();
    // loop through each Edge in edgeList
    for (Edge edge : edgeList) {
      // let position be the where the node is in edge
      int position = edge.nodePosition(a);
      // if a is the first Node, then add second Node into connectedNodes
      if (position == 1) {
        Node connectedNode = edge.getSecondNode();
        // create a boolean to check if it is already in the list
        boolean isInConnectedNodes = false;
        // loop through each node in list to check if it already exist
        for (Node node : connectedNodes) {
          // if exist, then change isInConnectedNodes to true
          if (node.equals(connectedNode)) {
            isInConnectedNodes = true;
          }
        }
        // add node into list only when it is not already there
        if (!isInConnectedNodes) {
          connectedNodes.add(connectedNode);
        }
      } else if (position == 2) {
        // if a is the second Node, then add first Node into connectedNodes
        Node connectedNode = edge.getFirstNode();
        // create a boolean to check if it is already in the list
        boolean isInConnectedNodes = false;
        // loop through each node in list to check if it already exist
        for (Node node : connectedNodes) {
          // if exist, then change isInConnectedNodes to true
          if (node.equals(connectedNode)) {
            isInConnectedNodes = true;
          }
        }
        // add node into list only when it is not already there
        if (!isInConnectedNodes) {
          connectedNodes.add(connectedNode);
        }
      }
    }
    return connectedNodes;
  }

  /**
   * Get all edges in the graph.
   * 
   * @return edgeList which contains all the edges in the graph.
   */
  @Override
  public List<Edge> getEdges() {
    return edgeList;
  }

  /**
   * Get a String which is the representation of the graph.
   * 
   * @return the representation of the graph: edge with both two directions connect two nodes by
   *         "<->", edge with single direction connect two nodes by "->".
   */
  @Override
  public String toString() {
    // initiate a String to return
    String result = new String();
    // get a copy of nodeList
    List<Node> copyOfNodeList = new ArrayList<>();
    copyOfNodeList.addAll(nodeList);
    // create a new Edge ArrayList to store added Edge
    List<Edge> addedEdgeList = new ArrayList<>();

    // loop through each Edge in edgeList
    for (Edge edge : edgeList) {
      // if edge is not added into result
      if (!addedEdgeList.contains(edge)) {
        // initiate a index for current edge in edgeList, and boolean bothDiection
        int edgeIndex = edgeList.indexOf(edge);
        boolean bothDirection = false;
        // do the following only when edge is not the last element in edgeList
        if (edgeIndex < edgeList.size() - 1) {
          // loop through each Edge in edgeList from edgeIndex to the end
          for (Edge nextEdge : edgeList.subList(edgeIndex + 1, edgeList.size())) {
            // if nextEdge has the same Nodes with edge
            if (edge.compareOrderAgnostic(nextEdge)) {
              // convert bothDirection to true, and add nextEdge into addedEdgeList
              bothDirection = true;
              addedEdgeList.add(nextEdge);
            }
          }
        }
        // if current edge has both direction
        if (bothDirection) {
          // add Nodes and Edge into result of the form a<->b
          result =
              result + edge.getFirstNode().getId() + "<->" + edge.getSecondNode().getId() + ";";
        } else {
          // otherwise, add Nodes and Edge into result of the form a->b
          result = result + edge.getFirstNode().getId() + "->" + edge.getSecondNode().getId() + ";";
        }
        // initial a index and two boolean for the loop
        int indexOfCopyList = 0;
        boolean foundFirst = false;
        boolean foundSecond = false;
        // initial two nodes to store first and second node in edge
        Node getFirst = new NodeImpl();
        Node getSecond = new NodeImpl();
        // loop through each node in copy list of the node
        while ((indexOfCopyList < copyOfNodeList.size()) && (!foundFirst || !foundSecond)) {
          // if node in copy list is equal to first node, then store this node
          if (copyOfNodeList.get(indexOfCopyList).equals(edge.getFirstNode()) && (!foundFirst)) {
            foundFirst = true;
            getFirst = copyOfNodeList.get(indexOfCopyList);
          }
          // if node in copy list is equal to second node, then store this node
          if (copyOfNodeList.get(indexOfCopyList).equals(edge.getSecondNode()) && (!foundSecond)) {
            foundSecond = true;
            getSecond = copyOfNodeList.get(indexOfCopyList);
          }
          indexOfCopyList++;
        }
        // remove first and second node in copy list
        copyOfNodeList.remove(getFirst);
        copyOfNodeList.remove(getSecond);
      }
    }

    // loop through each Node in nodeList
    for (Node node : copyOfNodeList) {
      // result add all single Nodes
      result = result + node.getId() + ";";
    }
    return result;
  }
}
