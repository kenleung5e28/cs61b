public class LinkedListDeque<T> {
    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int nodeCount;
    private final Node sentinel;

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
        Node currLast = sentinel.prev;
        Node newLast = new Node(item, currLast, sentinel);
        currLast.next = newLast;
        sentinel.prev = newLast;
        nodeCount += 1;
    }

    public boolean isEmpty() {
        return size() == 0;
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
        if (isEmpty()) {
            return null;
        }
        Node first = sentinel.next;
        Node second = first.next;
        sentinel.next = second;
        second.prev = sentinel;
        nodeCount -= 1;
        return first.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node last = sentinel.prev;
        Node secondToLast = last.prev;
        sentinel.prev = secondToLast;
        secondToLast.next = sentinel;
        nodeCount -= 1;
        return last.item;
    }

    public T get(int index) {
        if (index >= size() || index < 0) {
            return null;
        }
        Node node = sentinel;
        for (int i = 0; i <= index; i++) {
            node = node.next;
        }
        return node.item;
    }

    public T getRecursive(int index) {
        if (index >= size() || index < 0) {
            return null;
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
