import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testOffByNTrue() {
        OffByN cc = new OffByN(3);
        assertTrue(cc.equalChars('0', '3'));
    }

    @Test
    public void testOffByNFalse() {
        OffByN cc = new OffByN(2);
        assertFalse(cc.equalChars('b', 'a'));
    }
}
