import static org.junit.Assert.*;
import org.junit.Test;

public class AssertionsTest {

    @Test
    public void testAssertions() {

        // Check if two values are equal
        assertEquals(5, 2 + 3);

        // Check if condition is true
        assertTrue(5 > 3);

        // Check if condition is false
        assertFalse(5 < 3);

        // Check if object is null
        assertNull(null);

        // Check if object is not null
        assertNotNull(new Object());
    }
}