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
    Node node = sentinel;
    for (int i = 0; i < size(); i++) {
      node = node.next;
      System.out.print(node.item.toString() + " ");
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
    if (index >= size() || index < 0) {
      throw new IndexOutOfBoundsException("index must be a positive integer less than the size");
    }
    Node node = sentinel;
    for (int i = 0; i <= index; i++) {
      node = node.next;
    }
    return node.item;
  }

  public T getRecursive(int index) {
    if (index >= size() || index < 0) {
      throw new IndexOutOfBoundsException("index must be a positive integer less than the size");
    }
    return getNodeRecursive(sentinel.next, index).item;
  }

  private Node getNodeRecursive(Node start, int index) {
    if (index == 0) {
      return start;
    }
    return getNodeRecursive(start.next, index - 1);
  }
}
