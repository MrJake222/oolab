package agh.ics.oop.map.element;

import agh.ics.oop.*;
import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.MapDirection;
import agh.ics.oop.map.RectangularMap;

public class Animal extends AbstractMapElement {

    private MapDirection mapDirection;

    public Animal(IWorldMap map, Vector2d position) {
        super(map, position);
        this.mapDirection = MapDirection.NORTH;
    }

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2, 2));
    }

    public Animal() {
        this(new RectangularMap(5, 5));
    }

    public MapDirection getDirection() {
        return mapDirection;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT: {
                mapDirection = mapDirection.next();
                break;
            }

            case LEFT: {
                mapDirection = mapDirection.previous();
                break;
            }

            case FORWARD: {
                Vector2d newPos = position.add(mapDirection.toUnitVector());
                if (map.canMoveTo(newPos)) position = newPos;
                break;
            }

            case BACKWARD: {
                Vector2d newPos = position.add(mapDirection.toUnitVector().opposite());
                if (map.canMoveTo(newPos)) position = newPos;
                break;
            }
        }
    }

    @Override
    public boolean canWalkOver() {
        return false;
    }

    @Override
    public String toString() {
        // return "{Zwięrzę na "+this.position+" kierunek "+this.mapDirection+"}";
        return this.mapDirection.getSymbol();
    }
}
