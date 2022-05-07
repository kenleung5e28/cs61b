public class ArrayDeque<T> {
    private static final int INIT_SIZE = 8;
    private static final int FACTOR = 2;

    private T[] items;
    private int nextFirst;
    private int nextLast;

    private double usageFactor() {
        return (double) size() / (double) items.length;
    }

    private boolean needExpansion() {
        return usageFactor() > 0.75;
    }

    private boolean needShrinkage() {
        return items.length > FACTOR * INIT_SIZE && usageFactor() < 0.25;
    }

    private void copyAndReplaceItems(T[] newItems) {
        int itemsSize = size();
        int begin = incIndex(nextFirst);
        int end = nextLast;
        // items are wrapped
        if (end < begin) {
            System.arraycopy(items, begin, newItems, 0, items.length - begin);
            System.arraycopy(items, 0, newItems, items.length - begin, end);
        } else {
            System.arraycopy(items, begin, newItems, 0, itemsSize);
        }
        nextFirst = newItems.length - 1;
        nextLast = itemsSize;
        items = newItems;
    }

    private void expand() {
        T[] newItems = (T[]) new Object[items.length * FACTOR];
        copyAndReplaceItems(newItems);
    }

    private void shrink() {
        T[] newItems = (T[]) new Object[items.length / FACTOR];
        copyAndReplaceItems(newItems);
    }

    public ArrayDeque() {
        items = (T[]) new Object[INIT_SIZE];
        nextFirst = INIT_SIZE - 1;
        nextLast = 0;
    }

    public void addFirst(T item) {
        if (needExpansion()) {
            expand();
        }
        items[nextFirst] = item;
        nextFirst = decIndex(nextFirst);
    }

    public void addLast(T item) {
        if (needExpansion()) {
            expand();
        }
        items[nextLast] = item;
        nextLast = incIndex(nextLast);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        int begin = incIndex(nextFirst);
        int end = nextLast;
        // items are wrapped
        if (end < begin) {
            return items.length - begin + end;
        } else {
            return end - begin;
        }
    }

    public void printDeque() {
        int count = size();
        for (int i = 0; i < count; i++) {
            System.out.print(get(i).toString() + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int first = incIndex(nextFirst);
        T result = items[first];
        items[first] = null;
        nextFirst = first;
        if (needShrinkage()) {
            shrink();
        }
        return result;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int last = decIndex(nextLast);
        T result = items[last];
        items[last] = null;
        nextLast = last;
        if (needShrinkage()) {
            shrink();
        }
        return result;
    }

    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
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
