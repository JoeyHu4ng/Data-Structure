package com.heap;

import com.tree.avl.Node;

public class ListMinHeap<K extends Comparable<K>> implements MinHeap<K> {

  private Node<K>[] nodes;
  private int size;

  ListMinHeap() {
    nodes = new Node[1000];
    size = 0;
  }

  @Override
  public void insert(K x) {
    /* adding into the end of the list */
    Node<K> newNode = new Node<>(x);
    nodes[++size] = newNode;
    bubbleUp(size);
  }

  @Override
  public Node<K> extractMin() {
    /* get the min and let the last element be the first for bubble down */
    swap(1, size--);
    bubbleDown(1);
    return nodes[size + 1];
  }

  @Override
  public void heapify() {
    int i;
    for (i = size / 2; i > 0; i--)
      bubbleDown(i);
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Typical Bubble Up algorithm.
   * @param pos is the position to start the bubble up
   */
  private void bubbleUp(int pos) {
    Node<K> child = nodes[pos];
    Node<K> parent = nodes[pos / 2];
    int r = parent == null ? 0 : parent.key.compareTo(child.key);
    if (r > 0) {
      swap(pos, pos / 2);
      bubbleUp(pos / 2);
    }
  }

  /**
   * Typical Bubble Down algorithm.
   * @param pos is the position to start the bubble down
   */
  private void bubbleDown(int pos) {
    Node<K> curr = nodes[pos];
    Node<K> leftChild = pos * 2 <= size ? nodes[pos * 2] : nodes[pos];
    Node<K> rightChild = pos * 2 + 1 <= size ? nodes[pos * 2 + 1] : nodes[pos];
    int rLeft = curr.key.compareTo(leftChild.key);
    int rRight = curr.key.compareTo(rightChild.key);
    int rLeftRight = leftChild.key.compareTo(rightChild.key);
    if (rLeft > 0 || rRight > 0) {
      if (rLeftRight > 0) {
        swap(pos, pos * 2 + 1);
        bubbleDown(pos * 2 + 1);
      } else {
        swap(pos, pos * 2);
        bubbleDown(pos * 2);
      }
    }
  }

  /**
   * Swap the position of two Nodes.
   * @param posOne is the first input position
   * @param posTwo is the second input position
   */
  private void swap(int posOne, int posTwo) {
    Node<K> tmp = nodes[posOne];
    nodes[posOne] = nodes[posTwo];
    nodes[posTwo] = tmp;
  }

  /**
   * Load the Node list into the nodes so that we could skip inserting.
   * @param heap is Node list to load
   */
  public void loadList(Node<K>[] heap, int size) {
    nodes = heap;
    this.size = size;
  }

  @Override
  public String toString() {
    int i;
    StringBuilder res = new StringBuilder();
    for (i = 0; i < size; i++) {
      res.append(nodes[i]).append("->");
    }
    res.append(nodes[i]);
    return res.toString();
  }

}
