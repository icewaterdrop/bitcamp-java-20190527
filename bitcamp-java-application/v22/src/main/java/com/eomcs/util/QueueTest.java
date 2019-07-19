package com.eomcs.util;

public class QueueTest {
  public static void main(String[] args) {
    Queue<String> stack = new Queue<>();
    
    stack.offer("aaa");
    stack.offer("bbb");
    stack.offer("ccc");
    stack.offer("ddd");
    
    
    while (!stack.empty()) {
      System.out.println(stack.poll());
    }
  }
}
