import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOneLessByOne() {
        assertTrue(offByOne.equalChars('x', 'y'));
    }

    @Test
    public void testOffByOneMoreByOne() {
        assertTrue(offByOne.equalChars('b', 'a'));
    }
    @Test
    public void testOffByOneSameChar() {
        assertFalse(offByOne.equalChars('a', 'a'));
    }

    @Test
    public void testOffByOneLessByTwo() {
        assertFalse(offByOne.equalChars('i', 'k'));
    }

    @Test
    public void testOffByOneMoreByThree() {
        assertFalse(offByOne.equalChars('3', '0'));
    }

    @Test
    public void testOffByOneCaseInsensitive() {
        assertFalse(offByOne.equalChars('C', 'd'));
    }

    @Test
    public void testOffByOneNonAlphanumericTrue() {
        assertTrue(offByOne.equalChars('\\', ']'));
    }

    @Test
    public void testOffByOneNonAlphanumericFalse() {
        assertFalse(offByOne.equalChars('>', '<'));
    }
}
