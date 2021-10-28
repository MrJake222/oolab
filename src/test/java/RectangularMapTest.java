import agh.ics.oop.OptionsParser;
import agh.ics.oop.Vector2d;
import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.map.GrassField;
import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.RectangularMap;
import agh.ics.oop.map.element.Animal;
import agh.ics.oop.map.element.Grass;
import agh.ics.oop.map.element.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangularMapTest {

    @Test
    public void mapTest() {
        RectangularMap map = new RectangularMap(5, 5);
        assertTrue(map.place(new Grass(map, new Vector2d(2, 3))));
        assertTrue(map.place(new Grass(map, new Vector2d(0, 0))));
        assertTrue(map.place(new Animal(map, new Vector2d(1, 1))));
        assertTrue(map.place(new Animal(map, new Vector2d(4, 4))));
        assertFalse(map.place(new Animal(map, new Vector2d(5, 5))));

        assertTrue(map.canMoveTo(new Vector2d(0, 0))); // grass
        assertFalse(map.canMoveTo(new Vector2d(1, 1))); // animal
        assertFalse(map.canMoveTo(new Vector2d(-10, -4))); // outside
        assertFalse(map.place(new Animal(map, new Vector2d(4, 4)))); // place on animal
        assertFalse(map.place(new Grass(map, new Vector2d(0,0)))); // place on grass

        assertEquals(map.objectAt(new Vector2d(2, 3)), map.objectAt(new Vector2d(2, 3)));
        assertEquals(map.objectAt(new Vector2d(0, 1)), map.objectAt(new Vector2d(0, 1)));

        assertEquals(new Vector2d(0, 0), map.getLowerLeft());
        assertEquals(new Vector2d(4, 4), map.getUpperRight());
    }

    @Test
    public void walkingTest() {
        List<MoveDirection> moves = OptionsParser.parse("f b r l f f r r f f f f f f f f".split(" "));
        IWorldMap map = new RectangularMap(10, 5);
        List<Vector2d> positions = Arrays.asList(new Vector2d(2,2), new Vector2d(3,4));
        SimulationEngine engine = new SimulationEngine(map, positions, moves);
        engine.run();

        assertEquals(new Vector2d(2, 0), engine.getAnimalAt(0).getPosition());
        assertEquals(new Vector2d(3, 4), engine.getAnimalAt(1).getPosition());
    }
}
