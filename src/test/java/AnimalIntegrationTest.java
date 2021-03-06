import agh.ics.oop.*;
import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.MapDirection;
import agh.ics.oop.map.RectangularMap;
import agh.ics.oop.map.element.Animal;
import agh.ics.oop.map.element.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalIntegrationTest {

    @Test
    public void orientationTest() {
        Animal animal = new Animal();
        assertEquals(MapDirection.NORTH, animal.getDirection());
        animal.move(MoveDirection.RIGHT); assertEquals(MapDirection.EAST, animal.getDirection());
        animal.move(MoveDirection.LEFT); assertEquals(MapDirection.NORTH, animal.getDirection());
        animal.move(MoveDirection.LEFT); assertEquals(MapDirection.WEST, animal.getDirection());
        animal.move(MoveDirection.LEFT); assertEquals(MapDirection.SOUTH, animal.getDirection());
    }

    @Test
    public void positionTest() {
        Animal animal = new Animal();
        assertTrue(animal.isAt(new Vector2d(2, 2)));
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(2, 3)));
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(2, 4)));
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(2, 4))); // border
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(3, 4)));
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(4, 4)));
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(4, 4))); // border
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(4, 3)));
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(4, 2)));
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(4, 1)));
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(4, 0)));
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(4, 0))); // border
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(3, 0)));
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(2, 0)));
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(1, 0)));
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(0, 0)));
        animal.move(MoveDirection.FORWARD); assertTrue(animal.isAt(new Vector2d(0, 0))); // border
    }

    @Test
    public void parserTest() {
        Animal animal = new Animal();
        OptionsParser.parse("ffbrf".split("")).forEach(animal::move);
        assertTrue(animal.isAt(new Vector2d(3, 3)));
        OptionsParser.parse("rffflf".split("")).forEach(animal::move);
        assertTrue(animal.isAt(new Vector2d(4, 0)));
        OptionsParser.parse("bbbb".split("")).forEach(animal::move);
        assertTrue(animal.isAt(new Vector2d(0, 0)));
        OptionsParser.parse("bbbb".split("")).forEach(animal::move);
        assertTrue(animal.isAt(new Vector2d(0, 0))); // border
        OptionsParser.parse("lrf".split("")).forEach(animal::move);
        assertTrue(animal.isAt(new Vector2d(1, 0)));
    }

    @Test
    public void parserThrowsTest() {
        // Invalid move
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse("lrfg".split("")));
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse("a".split("")));
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse("h".split("")));
    }
}
