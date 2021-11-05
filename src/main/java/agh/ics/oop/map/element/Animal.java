package agh.ics.oop.map.element;

import agh.ics.oop.*;
import agh.ics.oop.map.*;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractMapElement implements IObservable {

    private MapDirection mapDirection;
    protected List<IPositionChangeObserver> observers;

    public Animal(IWorldMap map, Vector2d position) {
        super(map, position);
        this.mapDirection = MapDirection.NORTH;
        this.observers = new ArrayList<>();
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

    private void makeMoveTo(Vector2d newPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(this.position, newPosition);
        }

        this.position = newPosition;
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
                if (map.canMoveTo(newPos)) makeMoveTo(newPos);
                break;
            }

            case BACKWARD: {
                Vector2d newPos = position.add(mapDirection.toUnitVector().opposite());
                if (map.canMoveTo(newPos)) makeMoveTo(newPos);
                break;
            }
        }
    }

    @Override
    public boolean canWalkOver() {
        return false;
    }

    @Override
    public String getTexturePath() {
        System.out.println("texpath animal"+mapDirection.getCode()+".png");
        return "animal"+mapDirection.getCode()+".png";
    }

    @Override
    public String getName() {
        return "Z "+position;
    }

    @Override
    public String toString() {
        // return "{Zwięrzę na "+this.position+" kierunek "+this.mapDirection+"}";
        return this.mapDirection.getSymbol();
    }

    @Override
    public void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }
}
