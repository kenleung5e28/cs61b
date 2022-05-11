import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindromeEmpty() {
        boolean actual = palindrome.isPalindrome("");
        assertTrue(actual);
    }

    @Test
    public void testIsPalindromeLengthOne() {
        boolean actual = palindrome.isPalindrome("B");
        assertTrue(actual);
    }

    @Test
    public void testIsPalindromeFalse() {
        boolean actual = palindrome.isPalindrome("aaabbbaabaaa");
        assertFalse(actual);
    }

    @Test
    public void testIsPalindromeTrue() {
        boolean actual = palindrome.isPalindrome("racecar");
        assertTrue(actual);
    }

    @Test
    public void testIsPalindromeOffByOneTrue() {
        OffByOne cc = new OffByOne();
        boolean actual = palindrome.isPalindrome("abcdefedcb", cc);
        assertTrue(actual);
    }

    @Test
    public void testIsPalindromeOffByOneFalse() {
        OffByOne cc = new OffByOne();
        boolean actual = palindrome.isPalindrome("123454321", cc);
        assertFalse(actual);
    }
}
