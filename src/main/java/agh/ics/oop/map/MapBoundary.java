package agh.ics.oop.map;

import agh.ics.oop.Vector2d;
import agh.ics.oop.map.element.IMapElement;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    private final SortedSet<Vector2d> setX;
    private final SortedSet<Vector2d> setY;

    public MapBoundary() {
        setX = new TreeSet<>(Comparator.comparingInt(e -> e.x));
        setY = new TreeSet<>(Comparator.comparingInt(e -> e.y));
    }

    public void addElement(IMapElement element) {
        setX.add(element.getPosition());
        setY.add(element.getPosition());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        setX.remove(oldPosition);
        setY.remove(oldPosition);
        setX.add(newPosition);
        setY.add(newPosition);
    }

    public Vector2d getLowerLeft() {
        return new Vector2d(setX.first().x, setY.first().y);
    }

    public Vector2d getUpperRight() {
        return new Vector2d(setX.last().x, setY.last().y);
    }
}
