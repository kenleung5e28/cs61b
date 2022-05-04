public class ArrayDeque<T> {
  private static final int INIT_SIZE = 8;

  private T[] items;
  private int nextFirst;
  private int nextLast;

  public ArrayDeque() {
    items = (T[])new Object[INIT_SIZE];
    nextFirst = 0;
    nextLast = INIT_SIZE - 1;
  }

  public void addFirst(T item) {}

  public void addLast(T item) {}

  public boolean isEmpty() { return size() == 0; }

  public int size() { return wrapIndex(nextLast - nextFirst) - 1; }

  public void printQueue() {
    for (int i = indexIncrement(nextFirst); i < nextLast; i = indexIncrement(i)) {
      System.out.print(items[i].toString() + " ");
    }
    System.out.println();
  }

  public T removeFirst() {}

  public T removeLast() {}

  public T get(int index) {}


  private int wrapIndex(int index) {
    if (index < 0) {
      return index + items.length;
    }
    if (index >= items.length) {
      return index - items.length;
    }
    return index;
  }
  private int indexIncrement(int index) {
    return wrapIndex(index + 1);
  }

  private int indexDecrement(int index) {
    return wrapIndex(index - 1);
  }
}
