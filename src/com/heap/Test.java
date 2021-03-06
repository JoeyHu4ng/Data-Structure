package com.heap;

import com.tree.avl.Node;

public class Test {

  public static void main(String[] args) {
    ListMinHeap<Integer> minHeap = new ListMinHeap<Integer>();
    minHeap.insert(10);
    minHeap.insert(9);
    minHeap.insert(8);
    minHeap.insert(7);
    minHeap.insert(6);
    System.out.println(minHeap);
    Node<Integer>[] heap = new Node[1000];
    int i;
    for (i = 1; i < 10; i++) {
      heap[i] = new Node<Integer>(20 - i * 2);
    }
    minHeap.loadList(heap, i - 1);
    minHeap.heapify();
    System.out.println(minHeap);
    while (!minHeap.isEmpty()) {
      System.out.println(minHeap.extractMin().key);
    }

    System.out.println("--------fibonacci com.heap--------");
    /* testing for fibonacci com.heap */
    FibHeap fibHeap = new FibHeap();
    for (i = 0; i < 10; i++)
      fibHeap.insert(i * 5 + 7);
    while (!fibHeap.isEmpty())
      System.out.println(fibHeap.extractMin());
  }

}
