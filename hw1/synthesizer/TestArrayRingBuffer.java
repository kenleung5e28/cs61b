package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testCapacity() {
        ArrayRingBuffer<Integer> arb1 = new ArrayRingBuffer<>(5);
        assertEquals(5, arb1.capacity());
        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(20);
        assertEquals(20, arb2.capacity());
    }
    @Test
    public void testEnqueue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(100);
        assertEquals(1, arb.fillCount());
        arb.enqueue(200);
        arb.enqueue(300);
        assertEquals(3, arb.fillCount());
    }

    @Test
    public void testDeque() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(100);
        arb.enqueue(200);
        arb.enqueue(300);
        int item1 = arb.dequeue();
        assertEquals(2, arb.fillCount());
        assertEquals(100, item1);
        int item2 = arb.dequeue();
        assertEquals(1, arb.fillCount());
        assertEquals(200, item2);
    }

    @Test
    public void testPeek() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<>(10);
        arb.enqueue("First");
        arb.enqueue("Second");
        String item = arb.peek();
        assertEquals(2, arb.fillCount());
        assertEquals("First", item);
    }

    @Test
    public void testFillCount() {
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<>(10);
        arb.enqueue("First");
        arb.enqueue("Second");
        arb.dequeue();
        arb.dequeue();
        assertEquals(0, arb.fillCount());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
