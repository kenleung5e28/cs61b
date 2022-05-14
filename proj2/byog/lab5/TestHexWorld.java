package byog.lab5;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static byog.lab5.HexWorld.*;

public class TestHexWorld {
    @Test
    public void testRowWidth() {
        assertEquals(3, rowWidth(3, 0));
        assertEquals(5, rowWidth(3, 1));
        assertEquals(7, rowWidth(3, 2));
        assertEquals(7, rowWidth(3, 3));
        assertEquals(5, rowWidth(3, 4));
        assertEquals(3, rowWidth(3, 5));
    }

    @Test
    public void testRowStart() {
        assertEquals(3, rowStart(4, 0, 3));
        assertEquals(2, rowStart(4, 1, 3));
        assertEquals(1, rowStart(4, 2, 3));
        assertEquals(0, rowStart(4, 3, 3));
        assertEquals(0, rowStart(4, 4, 3));
        assertEquals(1, rowStart(4, 5, 3));
        assertEquals(2, rowStart(4, 6, 3));
        assertEquals(3, rowStart(4, 7, 3));
    }
}
