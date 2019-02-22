package com.set;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet<K extends Comparable<K>> {

  private Map<K, K> parent;
  private Map<K, Integer> rank;

  public DisjointSet() {
    parent = new HashMap<>();
    rank = new HashMap<>();
  }

  public void makeSet(K x) {
    parent.put(x, x);
    rank.put(x, 0);
  }

  public K find(K x) {
    if (parent.get(x).compareTo(x) != 0)
      parent.replace(x, find(parent.get(x)));
    return parent.get(x);
  }

  public void union(K x, K y) {
    link(find(x), find(y));
  }

  private void link(K x, K y) {
    if (rank.get(x) > rank.get(y))
      parent.replace(y, x);
    else {
      parent.replace(x, y);
      if (rank.get(x).equals(rank.get(y)))
        rank.replace(y, rank.get(y) + 1);
    }
  }

  public boolean contains(K x) {
    return parent.containsKey(x);
  }

}
