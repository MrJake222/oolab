package agh.ics.oop.map;

import agh.ics.oop.Vector2d;
import agh.ics.oop.map.element.IMapElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    private final Map<Vector2d, IMapElement> mapElements;
    private final MapVisualizer visualizer;

    public AbstractWorldMap() {
        this.mapElements = new LinkedHashMap<>();
        this.visualizer = new MapVisualizer(this);
    }

    public abstract Vector2d getLowerLeft();

    public abstract Vector2d getUpperRight();

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
        if (isOccupied(element.getPosition()) && !objectAt(element.getPosition()).canWalkOver()) {
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

    public Collection<IMapElement> mapObjects() {
        return mapElements.values();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement element = objectAt(oldPosition);
        mapElements.remove(oldPosition);
        mapElements.put(newPosition, element);
    }

    @Override
    public String toString() {
        return visualizer.draw(getLowerLeft(), getUpperRight());
    }
}
