package graph;

import java.util.List;
import java.util.ArrayList;;

public class Test {
  public static void main(String[] args) throws NodeNotFoundException {
    Node a = new NodeImpl(1);
    Node b = new NodeImpl(2);
    Node c = new NodeImpl(3);
    Node d = new NodeImpl(4);
    Node h = new NodeImpl(3);
    DirectedGraphImpl graph = new DirectedGraphImpl();
    graph.addNode(a);
    graph.addNode(b);
    graph.addNode(c);
    graph.addNode(d);
    /*
    System.out.println(graph.addNode(null));
    System.out.println(graph.addEdge(null, null));
    System.out.println(graph.addEdge(a, null));
    System.out.println(graph.addEdge(null, a));
    System.out.println(graph.removeEdge(null, null));
    System.out.println(graph);
    System.out.println(graph.addEdge(a, b));
    System.out.println(graph.addEdge(b, a));
    System.out.println(graph.removeEdge(a, b));
    System.out.println(graph.getEdges());
    System.out.println(graph);
    */
    System.out.println(graph.addEdge(a, a));
    graph.addEdge(a, h);
    graph.addEdge(a, d);
    graph.addEdge(h, a);
    System.out.println(graph.getEdges());
    System.out.println(graph);
    Node e = new NodeImpl(5);
    graph.addNode(e);
    System.out.println(graph);

    Node f = new NodeImpl(1);
    graph.addNode(f);
    System.out.println(graph);
    System.out.println(graph.getConnectedNodes(a));
    
  }

}
