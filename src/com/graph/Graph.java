package com.graph;

import java.util.List;

public interface Graph {
  
  /**
   * Adds an edge between node a and node b.
   * @param a the first node.
   * @param b the second node.
   * @return true if edge was added, and false if the edge already existed.
   * @throws NodeNotFoundException if either a or b are not part of the graph, throw this 
   *                                exception.
   */
  public boolean addEdge(Node a, Node b) throws NodeNotFoundException;
  
  /**
   * add a node to graph.
   * @param a the node to be added.
   * @return true if node was added, false o/w.
   */
  public boolean addNode(Node a);
  
  /**
   * removes Edge between Node a and Node b.
   * @param a the first Node.
   * @param b the second Node.
   * @return true if edge was found and removed, false if edge was not in graph.
   * @throws NodeNotFoundException if either a or b are not part of the graph, throw this exception.
   */
  public boolean removeEdge(Node a, Node b) throws NodeNotFoundException;
  
  /**
   * Checks if Node a is contained in the graph.
   * @param a Node to be checked.
   * @return true if Node a is in the graph, false otherwise.
   */
  public boolean contains(Node a);
  
  /** 
   * Gives the number of nodes contained in the graph.
   * @return an int representing the number of nodes in the graph.
   */
  public int size();
  
  /**
   * Returns a list of the nodes connected to Node a.
   * @param a the Node of interest.
   * @return A list of Nodes connected to a.
   * @throws NodeNotFoundException if a is not in the Graph.
   */
  public List<Node> getConnectedNodes(Node a) throws NodeNotFoundException;
  
  
  /**
   * get a list containing all the edges.
   * @return a list of Edges contained in the Graph.
   */
  public List<Edge> getEdges();
  
  /**
   * A string representation of the graph.
   * @return String representation of graph in format a<->b;b<->c;d;e->f;
   */
  public String toString();
  
  

}
