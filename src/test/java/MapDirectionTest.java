import agh.ics.oop.MapDirection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapDirectionTest {

    @Test
    public void nextTest() {
        Assertions.assertEquals(MapDirection.SOUTH, MapDirection.EAST.next());
        Assertions.assertEquals(MapDirection.WEST, MapDirection.SOUTH.next());
        Assertions.assertEquals(MapDirection.NORTH, MapDirection.WEST.next());
        Assertions.assertEquals(MapDirection.EAST, MapDirection.NORTH.next());
    }

    @Test
    public void previousTest() {
        Assertions.assertEquals(MapDirection.NORTH, MapDirection.EAST.previous());
        Assertions.assertEquals(MapDirection.WEST, MapDirection.NORTH.previous());
        Assertions.assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous());
        Assertions.assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous());
    }
}
