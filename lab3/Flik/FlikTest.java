import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void testIsSameNumber() {
        int a1 = 127;
        int b1 = 127;
        assertEquals(true, Flik.isSameNumber(a1, b1));
        int a2 = 128;
        int b2 = 128;
        assertEquals(true, Flik.isSameNumber(a2, b2));
    }
}
