import agh.ics.oop.*;
import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.map.AbstractWorldMap;
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

public class GrassFieldTest {

    @Test
    public void grassFieldTest() {
        GrassField map = new GrassField(0);
        map.place(new Grass(map, new Vector2d(-1, 0)));
        map.place(new Grass(map, new Vector2d(1, -1)));
        map.place(new Grass(map, new Vector2d(2, 3)));
        map.place(new Grass(map, new Vector2d(2, 7)));
        map.place(new Grass(map, new Vector2d(-2, -6)));
        map.place(new Grass(map, new Vector2d(-10, -4)));
        map.place(new Grass(map, new Vector2d(0, 0)));
        map.place(new Animal(map, new Vector2d(-1, -1)));

        assertTrue(map.canMoveTo(new Vector2d(0, 0))); // grass
        assertTrue(map.canMoveTo(new Vector2d(-10, -4))); // grass
        assertFalse(map.canMoveTo(new Vector2d(-1, -1))); // animal
        assertTrue(map.canMoveTo(new Vector2d(-100, -100))); // outside
        assertFalse(map.place(new Animal(map, new Vector2d(-1, -1)))); // place on animal
        assertFalse(map.place(new Grass(map, new Vector2d(0,0)))); // place on grass

        assertEquals(map.objectAt(new Vector2d(-10, -4)), map.objectAt(new Vector2d(-10, -4)));
        assertEquals(map.objectAt(new Vector2d(0, 1)), map.objectAt(new Vector2d(0, 1)));

        assertEquals(new Vector2d(-10, -6), map.getLowerLeft());
        assertEquals(new Vector2d(2, 7), map.getUpperRight());
    }

    @Test
    public void walkingTest() {
        List<MoveDirection> moves = OptionsParser.parse("f b r l f f r r f f f f f f f f".split(" "));
        IWorldMap map = new GrassField(0);
        map.place(new Grass(map, new Vector2d(2, 1)));
        List<Vector2d> positions = Arrays.asList(new Vector2d(2,2), new Vector2d(3,4));
        SimulationEngine engine = new SimulationEngine(map, positions, moves);
        engine.run();

        assertEquals(new Vector2d(2, -1), engine.getAnimalAt(0).getPosition());
        assertEquals(new Vector2d(3, 7), engine.getAnimalAt(1).getPosition());
    }

    @Test
    public void grassTest() {
        AbstractWorldMap map = new GrassField(10);
        Vector2d l = map.getLowerLeft();
        Vector2d r = map.getUpperRight();

        int cnt = 0;
        for (int x=l.x; x<=r.x; x++) {
            for (int y=l.y; y<=r.y; y++) {
                if (map.objectAt(new Vector2d(x, y)) instanceof Grass) {
                    cnt++;
                }
            }
        }

        assertEquals(10, cnt);
    }
}
