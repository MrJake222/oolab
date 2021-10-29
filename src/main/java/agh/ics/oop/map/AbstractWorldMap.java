package agh.ics.oop.map;

import agh.ics.oop.Vector2d;
import agh.ics.oop.map.element.IMapElement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected int width;
    protected int height;
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    private final Map<Vector2d, IMapElement> mapElements;
    private final MapVisualizer visualizer;

    public AbstractWorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(this.width-1, this.height-1);
        this.mapElements = new LinkedHashMap<>();
        this.visualizer = new MapVisualizer(this);
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (!isOccupied(position))
            return true;

        if (objectAt(position).canWalkOver())
            return true;

        return false;
    }

    @Override
    public boolean place(IMapElement element) {
        if (isOccupied(element.getPosition())) {
            throw new IllegalArgumentException("unable to place " + element + " on " + element.getPosition());
        }

        if (element instanceof IObservable) {
            ((IObservable) element).addObserver(this);
        }

        mapElements.put(element.getPosition(), element);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public IMapElement objectAt(Vector2d position) {
        return mapElements.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement element = objectAt(oldPosition);
        mapElements.remove(oldPosition);
        mapElements.put(newPosition, element);
    }

    @Override
    public String toString() {
        return visualizer.draw(lowerLeft, upperRight);
    }
}
