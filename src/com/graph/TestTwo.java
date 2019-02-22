package com.graph;

public class TestTwo {
  public static void main(String[] args) throws NodeNotFoundException {
    Node a = new NodeImpl(3);
    Node b = new NodeImpl(7);
    Node c = new NodeImpl(7);
    Node d = new NodeImpl(9);
    Node e = new NodeImpl(10);
    Node h = new NodeImpl(11);
    UndirectedGraphImpl g = new UndirectedGraphImpl();
    g.addNode(a);
    System.out.println(g.addNode(b));
    g.addNode(d);
    g.addNode(e);
    g.addNode(h);
    System.out.println(g.addEdge(a, c));
    System.out.println(g.addEdge(a, d));
    System.out.println(g.addEdge(a, e));
    System.out.println(g.addEdge(e, a));
    System.out.println(g.addEdge(a, h));
    System.out.println(g.addEdge(d, a));
    System.out.println("test");
    System.out.println(g.getConnectedNodes(a));
    System.out.println("test");
    System.out.println(g);
    System.out.println(g);
    System.out.println("5");
    Edge edge = new EdgeImpl(a, b);
    System.out.println(edge.nodePosition(null));
  }

}
