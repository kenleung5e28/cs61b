public class LinkedListDeque<T> {
  private class Node {
    public T item;
    public Node prev;
    public Node next;
    public Node(T item, Node prev, Node next) {
      this.item = item;
      this.prev = prev;
      this.next = next;
    }
  }

  private int nodeCount;
  private Node sentinel;
  
  public LinkedListDeque() {
    nodeCount = 0;
    sentinel = new Node(null, null, null);
    sentinel.prev = sentinel;
    sentinel.next = sentinel;
  }

  public void addFirst(T item) {
    Node currFirst = sentinel.next;
    Node newFirst = new Node(item, sentinel, currFirst);
    currFirst.prev = newFirst;
    sentinel.next = newFirst;
    nodeCount += 1;
  }

  public void addLast(T item) {
    // TODO
  }

  public boolean isEmpty() {
    return nodeCount == 0;
  }

  public int size() {
    return nodeCount;
  }

  public void printDeque() {
    Node node = sentinel.next;
    while (node != sentinel) {
      System.out.print(node.item.toString() + " ");
      node = node.next;
    }
    System.out.println();
  }

  public T removeFirst() {
    // TODO
    return null;
  }

  public T removeLast() {
    // TODO
    return null;
  }

  public T get(int index) {
    // TODO
    return null;
  }

  public T getRecursive(int index) {
    // TODO
    return null;
  }
}
