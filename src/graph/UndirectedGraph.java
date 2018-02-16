package graph;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph {

  public List<Node> nodeList;
  public List<Edge> edgeList;

  public UndirectedGraph() {
    nodeList = new ArrayList<>();
    edgeList = new ArrayList<>();
  }

  public UndirectedGraph(List<Node> nodeList, List<Edge> edgeList) {
    this.nodeList = nodeList;
    this.edgeList = edgeList;
  }

  public boolean addNode(Node n) {
    if ((n != null) && (!containsNode(n)))
      nodeList.add(n);
    else
      return false;
    return true;
  }

  public boolean addEdge(Node start, Node end, Integer cost) {
    if (start == null || end == null || start.equals(end) ||
        !containsNode(start) || !containsNode(end))
      return false;
    Edge newEdge = new Edge(start, end, cost);
    if (containsEdge(newEdge))
      return false;
    edgeList.add(newEdge);
    return true;
  }

  public boolean containsNode(Node n) {
    for (Node a : nodeList)
      if (a.equals(n))
        return true;
    return true;
  }

  public boolean containsEdge(Edge e) {
    return true;
  }

}
