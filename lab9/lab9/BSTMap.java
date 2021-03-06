package lab9;

import java.util.*;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int sign = key.compareTo(p.key);
        if (sign == 0) {
            return p.value;
        } else if (sign < 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        }
        int sign = key.compareTo(p.key);
        if (sign < 0) {
            p.left = putHelper(key, value, p.left);
        } else if (sign > 0) {
            p.right = putHelper(key, value, p.right);
        } else {
            p.value = value;
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return new SetView();
    }

    private class SetView implements Set<K> {

        @Override
        public int size() {
            return BSTMap.this.size();
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public boolean contains(Object o) {
            K key = (K)o;
            return BSTMap.this.containsKey(key);
        }

        @Override
        public Iterator<K> iterator() {
            return BSTMap.this.iterator();
        }

        @Override
        public Object[] toArray() {
            Iterator<K> iter = iterator();
            int n = size();
            Object[] arr = new Object[n];
            for (int i = 0; i < n; i++) {
                arr[i] = iter.next();
            }
            return arr;
        }

        @Override
        public <T> T[] toArray(T[] a) {
            Iterator<K> iter = iterator();
            int n = size();
            ArrayList<T> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add((T)iter.next());
            }
            return list.toArray(a);
        }

        @Override
        public boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            for (Object item : c) {
                if (!contains(item)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean addAll(Collection<? extends K> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        RemoveResult result = removeHelper(key, null, root, false);
        root = result.node;
        return result.success ? result.value : null;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        RemoveResult result = removeHelper(key, value, root, true);
        root = result.node;
        return result.success ? result.value : null;
    }

    private class RemoveResult {
        public boolean success;
        public V value;
        public Node node;
        public RemoveResult(boolean success, V value, Node node) {
            this.success = success;
            this.value = value;
            this.node = node;
        }
    }

    private RemoveResult removeHelper(K key, V value, Node p, boolean checkValue) {
        if (p == null) {
            return new RemoveResult(false, null, null);
        }
        int sign = key.compareTo(p.key);
        RemoveResult result;
        if (sign < 0) {
            result = removeHelper(key, value, p.left, checkValue);
            p.left = result.node;
            result.node = p;
        } else if (sign > 0) {
            result = removeHelper(key, value, p.right, checkValue);
            p.right = result.node;
            result.node = p;
        } else {
            if (checkValue && p.value != value) {
                return new RemoveResult(false, null, p);
            }
            result = new RemoveResult(true, p.value, null);
            if (p.left != null && p.right != null) {
                result.node = removeNodeWithTwoChildren(p);
            } else {
                result.node = removeNodeWithAtMostOneChild(p);
            }
            size -= 1;
        }
        return result;
    }


    private Node removeNodeWithAtMostOneChild(Node p) {
        return p.right == null ? p.left : p.right;
    }

    // replace the node to be removed by the greatest node less than it
    private Node removeNodeWithTwoChildren(Node p) {
        Node parent = p;
        Node target = p.left;
        if (target.right == null) {
            target.left = removeNodeWithAtMostOneChild(target);
            target.right = p.right;
            return target;
        }
        while (target.right != null) {
            parent = target;
            target = target.right;
        }
        parent.right = removeNodeWithAtMostOneChild(target);
        target.left = p.left;
        target.right = p.right;
        return target;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        private final Stack<Node> stack;

        private void pushAllLeftChildren(Node p) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
        }

        public BSTMapIterator() {
            stack = new Stack<>();
            pushAllLeftChildren(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public K next() {
            Node p = stack.pop();
            pushAllLeftChildren(p.right);
            return p.key;
        }
    }
}
