import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    @Test
    public void equalsTest() {
        assertEquals(new Vector2d(1, 2), new Vector2d(1, 2));
        assertEquals(new Vector2d(-1, 2), new Vector2d(-1, 2));
        assertNotEquals(new Vector2d(-1, 2), new Vector2d(1, 2));
        assertNotEquals(new Vector2d(3, 4), new Vector2d(-3, 4));
    }

    @Test
    public void hashcodeTest() {
        Vector2d[] vectors = {
                new Vector2d(1,2),
                new Vector2d(1,2),
                new Vector2d(2,1),
                new Vector2d(3,4),
                new Vector2d(3,4)};

        for (Vector2d v1 : vectors) {
            for (Vector2d v2 : vectors) {
                assertEquals(v1.equals(v2), v1.hashCode() == v2.hashCode());
            }
        }
    }

    @Test
    public void toStringTest() {
        assertEquals("(1,2)", new Vector2d(1,2).toString());
        assertEquals("(-3,-4)", new Vector2d(-3,-4).toString());
        assertNotEquals("(4,3)", new Vector2d(3,4).toString());
        assertNotEquals("(3,4)", new Vector2d(-3,4).toString());
    }

    @Test
    public void precedesTest() {
        assertTrue(new Vector2d(4,3).precedes(new Vector2d(4,3)));
        assertTrue(new Vector2d(2,1).precedes(new Vector2d(3,1)));
        assertTrue(new Vector2d(2,1).precedes(new Vector2d(4,3)));
        assertFalse(new Vector2d(2,1).precedes(new Vector2d(1,4)));
        assertFalse(new Vector2d(1,4).precedes(new Vector2d(4,3)));
        assertFalse(new Vector2d(4,3).precedes(new Vector2d(1,4)));
    }

    @Test
    public void followsTest() {
        assertTrue(new Vector2d(4,3).follows(new Vector2d(4,3)));
        assertTrue(new Vector2d(4,3).follows(new Vector2d(3,1)));
        assertTrue(new Vector2d(4,3).follows(new Vector2d(2,1)));
        assertFalse(new Vector2d(4,3).follows(new Vector2d(1,4)));
        assertTrue(new Vector2d(3,1).follows(new Vector2d(2,1)));
    }

    @Test
    public void upperRightTest() {
        assertEquals(new Vector2d(4,3), new Vector2d(4,3).upperRight(new Vector2d(2,1)));
        assertEquals(new Vector2d(4,3), new Vector2d(4,3).upperRight(new Vector2d(3,1)));
        assertEquals(new Vector2d(4,4), new Vector2d(4,3).upperRight(new Vector2d(1,4)));
        assertEquals(new Vector2d(3,1), new Vector2d(2,1).upperRight(new Vector2d(3,1)));
        assertEquals(new Vector2d(2,4), new Vector2d(2,1).upperRight(new Vector2d(1,4)));
    }

    @Test
    public void lowerLeftTest() {
        assertEquals(new Vector2d(2,1), new Vector2d(4,3).lowerLeft(new Vector2d(2,1)));
        assertEquals(new Vector2d(3,1), new Vector2d(4,3).lowerLeft(new Vector2d(3,1)));
        assertEquals(new Vector2d(1,3), new Vector2d(4,3).lowerLeft(new Vector2d(1,4)));
        assertEquals(new Vector2d(2,1), new Vector2d(2,1).lowerLeft(new Vector2d(3,1)));
        assertEquals(new Vector2d(1,1), new Vector2d(2,1).lowerLeft(new Vector2d(1,4)));
    }

    @Test
    public void addTest() {
        assertEquals(new Vector2d(3,5), new Vector2d(1,4).add(new Vector2d(2,1)));
        assertEquals(new Vector2d(4,5), new Vector2d(1,4).add(new Vector2d(3,1)));
        assertEquals(new Vector2d(5,7), new Vector2d(1,4).add(new Vector2d(4,3)));
        assertEquals(new Vector2d(5,2), new Vector2d(2,1).add(new Vector2d(3,1)));
        assertEquals(new Vector2d(6,4), new Vector2d(2,1).add(new Vector2d(4,3)));
    }

    @Test
    public void subtractTest() {
        assertEquals(new Vector2d(-1,3), new Vector2d(1,4).subtract(new Vector2d(2,1)));
        assertEquals(new Vector2d(-2,3), new Vector2d(1,4).subtract(new Vector2d(3,1)));
        assertEquals(new Vector2d(-3,1), new Vector2d(1,4).subtract(new Vector2d(4,3)));
        assertEquals(new Vector2d(-1,0), new Vector2d(2,1).subtract(new Vector2d(3,1)));
        assertEquals(new Vector2d(-2,-2), new Vector2d(2,1).subtract(new Vector2d(4,3)));
    }

    @Test
    public void oppositeTest() {
        assertEquals(new Vector2d(-1,3), new Vector2d(1,-3).opposite());
        assertEquals(new Vector2d(1, 3), new Vector2d(-1,-3).opposite());
        assertNotEquals(new Vector2d(-2, 4), new Vector2d(-4,2).opposite());
        assertNotEquals(new Vector2d(-2, 4), new Vector2d(-2,4).opposite());
    }
}
