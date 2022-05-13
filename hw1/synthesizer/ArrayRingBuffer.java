// DONE: Make sure to make this class a part of the synthesizer package
package synthesizer;
import synthesizer.AbstractBoundedQueue;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//DONE: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // DONE: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        fillCount = 0;
        first = 0;
        last = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // DONE: Enqueue the item. Don't forget to increase fillCount and update last.
        if (fillCount() == capacity()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount += 1;
        last = increment(last);
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // DONE: Dequeue the first item. Don't forget to decrease fillCount and update
        if (fillCount() == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T item = rb[first];
        fillCount -= 1;
        first = increment(first);
        return item;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // DONE: Return the first item. None of your instance variables should change.
        if (fillCount() == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }


    // TODO: When you get to part 5, implement the needed code to support iteration.


    // Private methods
    private int increment(int index) {
        int result = index + 1;
        if (result >= capacity()) {
            return result - capacity();
        }
        return result;
    }
}
