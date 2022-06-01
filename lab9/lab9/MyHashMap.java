package lab9;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        ArrayMap<K, V> bucket = buckets[hash(key)];
        return bucket.containsKey(key) ? bucket.get(key) : null;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        ArrayMap<K, V> bucket = buckets[hash(key)];
        if (!bucket.containsKey(key)) {
            size += 1;
        }
        bucket.put(key, value);
        if (loadFactor() > MAX_LF) {
            ArrayMap<K, V>[] oldBuckets = buckets;
            buckets = new ArrayMap[2 * size()];
            clear();
            for (ArrayMap<K, V> oldBucket : oldBuckets) {
                for (K oldKey : oldBucket) {
                    V oldValue = oldBucket.get(oldKey);
                    put(oldKey, oldValue);
                }
            }
        }
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
        return new SetView(this);
    }

    private class SetView implements Set<K> {
        private MyHashMap<K, V> origin;

        public SetView(MyHashMap<K, V> hashMap) {
            origin = hashMap;
        }
        @Override
        public int size() {
            return origin.size();
        }

        @Override
        public boolean isEmpty() {
            return origin.size() == 0;
        }

        @Override
        public boolean contains(Object o) {
            K key = (K)o;
            return origin.containsKey(key);
        }

        @Override
        public Iterator<K> iterator() {
            return origin.iterator();
        }

        @Override
        public Object[] toArray() {
            Iterator<K> iter = origin.iterator();
            int n = origin.size();
            Object[] arr = new Object[n];
            for (int i = 0; i < n; i++) {
                arr[i] = iter.next();
            }
            return arr;
        }

        @Override
        public <T> T[] toArray(T[] a) {
            Iterator<K> iter = origin.iterator();
            int n = origin.size();
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
                if (!contains(c)) {
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

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        ArrayMap<K, V> bucket = buckets[hash(key)];
        return bucket.remove(key);
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        ArrayMap<K, V> bucket = buckets[hash(key)];
        return bucket.remove(key, value);
    }

    @Override
    public Iterator<K> iterator() {
       return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<K> {
        int count;
        int bucketIndex;
        Iterator<K> bucketIterator;

        public MyHashMapIterator() {
            count = 0;
            bucketIndex = 0;
            bucketIterator = buckets[bucketIndex].iterator();
        }

        @Override
        public boolean hasNext() {
            return count < size();
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator has reached the end");
            }
            while (!bucketIterator.hasNext()) {
                bucketIndex += 1;
                bucketIterator = buckets[bucketIndex].iterator();
            }
            K item = bucketIterator.next();
            count += 1;
            return item;
        }
    }
}
