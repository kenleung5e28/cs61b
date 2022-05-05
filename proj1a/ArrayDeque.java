/*
* TODO:
* 1. implement expanding items array upon usage factor exceeds a threshold
* 2. implement shrinking items array upon usage factor falls below a threshold
*/

public class ArrayDeque<T> {
  private static final int INIT_SIZE = 8;

  private T[] items;
  private int nextFirst;
  private int nextLast;

  public ArrayDeque() {
    items = (T[])new Object[INIT_SIZE];
    nextFirst = INIT_SIZE - 1;
    nextLast = 0;
  }

  public void addFirst(T item) {
    // temp behavior: throw exception when items array is full
    if (size() == items.length) {
      throw new RuntimeException("unable to add new element when deque is full");
    }

    items[nextFirst] = item;
    nextFirst = decIndex(nextFirst);
  }

  public void addLast(T item) {
    // TODO
  }

  public boolean isEmpty() { return size() == 0; }

  public int size() { return wrapIndex(nextLast - nextFirst) - 1; }

  public void printDeque() {
    int count = size();
    for (int i = 0; i < count; i++) {
      System.out.print(get(i).toString() + " ");
    }
    System.out.println();
  }

  public T removeFirst() {
    if (isEmpty()) {
      throw new RuntimeException("unable to remove element when deque is empty");
    }

    T result = get(0);
    nextFirst = incIndex(nextFirst);
    return result;
  }

  public T removeLast() {
    // TODO
    return null;
  }

  public T get(int index) {
    if (index < 0 || index >= size()) {
      throw new ArrayIndexOutOfBoundsException("index out of bound");
    }
    int realIndex = wrapIndex(nextFirst + 1 + index);
    return items[realIndex];
  }


  private int wrapIndex(int index) {
    if (index < 0) {
      return index + items.length;
    }
    if (index >= items.length) {
      return index - items.length;
    }
    return index;
  }
  private int incIndex(int index) {
    return wrapIndex(index + 1);
  }

  private int decIndex(int index) {
    return wrapIndex(index - 1);
  }
}
