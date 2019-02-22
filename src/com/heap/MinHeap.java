package com.heap;

import com.tree.avl.Node;

public interface MinHeap<K extends Comparable<K>> {

  public void insert(K x);

  public Node<K> extractMin();

  public void heapify();

  public boolean isEmpty();

}
