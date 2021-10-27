import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class Vector2dTest {

    @Test
    public void equalsTest() {
        Assertions.assertEquals(new Vector2d(1, 2), new Vector2d(1, 2));
        Assertions.assertEquals(new Vector2d(-1, 2), new Vector2d(-1, 2));
        Assertions.assertNotEquals(new Vector2d(-1, 2), new Vector2d(1, 2));
        Assertions.assertNotEquals(new Vector2d(3, 4), new Vector2d(-3, 4));
    }

    @Test
    public void toStringTest() {
        Assertions.assertEquals("(1,2)", new Vector2d(1,2).toString());
        Assertions.assertEquals("(-3,-4)", new Vector2d(-3,-4).toString());
        Assertions.assertNotEquals("(4,3)", new Vector2d(3,4).toString());
        Assertions.assertNotEquals("(3,4)", new Vector2d(-3,4).toString());
    }

    @Test
    public void precedesTest() {
        Assertions.assertTrue(new Vector2d(4,3).precedes(new Vector2d(4,3)));
        Assertions.assertTrue(new Vector2d(2,1).precedes(new Vector2d(3,1)));
        Assertions.assertTrue(new Vector2d(2,1).precedes(new Vector2d(4,3)));
        Assertions.assertFalse(new Vector2d(2,1).precedes(new Vector2d(1,4)));
        Assertions.assertFalse(new Vector2d(1,4).precedes(new Vector2d(4,3)));
        Assertions.assertFalse(new Vector2d(4,3).precedes(new Vector2d(1,4)));
    }

    @Test
    public void followsTest() {
        Assertions.assertTrue(new Vector2d(4,3).follows(new Vector2d(4,3)));
        Assertions.assertTrue(new Vector2d(4,3).follows(new Vector2d(3,1)));
        Assertions.assertTrue(new Vector2d(4,3).follows(new Vector2d(2,1)));
        Assertions.assertFalse(new Vector2d(4,3).follows(new Vector2d(1,4)));
        Assertions.assertTrue(new Vector2d(3,1).follows(new Vector2d(2,1)));
    }

    @Test
    public void upperRightTest() {
        Assertions.assertEquals(new Vector2d(4,3), new Vector2d(4,3).upperRight(new Vector2d(2,1)));
        Assertions.assertEquals(new Vector2d(4,3), new Vector2d(4,3).upperRight(new Vector2d(3,1)));
        Assertions.assertEquals(new Vector2d(4,4), new Vector2d(4,3).upperRight(new Vector2d(1,4)));
        Assertions.assertEquals(new Vector2d(3,1), new Vector2d(2,1).upperRight(new Vector2d(3,1)));
        Assertions.assertEquals(new Vector2d(2,4), new Vector2d(2,1).upperRight(new Vector2d(1,4)));
    }

    @Test
    public void lowerLeftTest() {
        Assertions.assertEquals(new Vector2d(2,1), new Vector2d(4,3).lowerLeft(new Vector2d(2,1)));
        Assertions.assertEquals(new Vector2d(3,1), new Vector2d(4,3).lowerLeft(new Vector2d(3,1)));
        Assertions.assertEquals(new Vector2d(1,3), new Vector2d(4,3).lowerLeft(new Vector2d(1,4)));
        Assertions.assertEquals(new Vector2d(2,1), new Vector2d(2,1).lowerLeft(new Vector2d(3,1)));
        Assertions.assertEquals(new Vector2d(1,1), new Vector2d(2,1).lowerLeft(new Vector2d(1,4)));
    }

    @Test
    public void addTest() {
        Assertions.assertEquals(new Vector2d(3,5), new Vector2d(1,4).add(new Vector2d(2,1)));
        Assertions.assertEquals(new Vector2d(4,5), new Vector2d(1,4).add(new Vector2d(3,1)));
        Assertions.assertEquals(new Vector2d(5,7), new Vector2d(1,4).add(new Vector2d(4,3)));
        Assertions.assertEquals(new Vector2d(5,2), new Vector2d(2,1).add(new Vector2d(3,1)));
        Assertions.assertEquals(new Vector2d(6,4), new Vector2d(2,1).add(new Vector2d(4,3)));
    }

    @Test
    public void subtractTest() {
        Assertions.assertEquals(new Vector2d(-1,3), new Vector2d(1,4).subtract(new Vector2d(2,1)));
        Assertions.assertEquals(new Vector2d(-2,3), new Vector2d(1,4).subtract(new Vector2d(3,1)));
        Assertions.assertEquals(new Vector2d(-3,1), new Vector2d(1,4).subtract(new Vector2d(4,3)));
        Assertions.assertEquals(new Vector2d(-1,0), new Vector2d(2,1).subtract(new Vector2d(3,1)));
        Assertions.assertEquals(new Vector2d(-2,-2), new Vector2d(2,1).subtract(new Vector2d(4,3)));
    }

    @Test
    public void oppositeTest() {
        Assertions.assertEquals(new Vector2d(-1,3), new Vector2d(1,-3).opposite());
        Assertions.assertEquals(new Vector2d(1, 3), new Vector2d(-1,-3).opposite());
        Assertions.assertNotEquals(new Vector2d(-2, 4), new Vector2d(-4,2).opposite());
        Assertions.assertNotEquals(new Vector2d(-2, 4), new Vector2d(-2,4).opposite());
    }
}
