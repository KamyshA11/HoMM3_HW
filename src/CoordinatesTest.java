import field.Field;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

@DisplayName("Correct Coordinates Test")
public class CoordinatesTest {
    private Field field;

    @Before
    public void setUp() {
        field = new Field(14, 14);
    }

    @Test
    public void inField() {
        boolean result = Main.checkCoordinates(10, 10, field);
        assertFalse(result);
    }

    @Test
    public void outOfField() {
        boolean result = Main.checkCoordinates(10, -1, field);
        assertTrue(result);
    }
}