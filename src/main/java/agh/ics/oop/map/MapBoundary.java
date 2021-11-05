package agh.ics.oop.map;

import agh.ics.oop.Vector2d;
import agh.ics.oop.map.element.IMapElement;

import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {

    private final SortedSet<Vector2d> setX;
    private final SortedSet<Vector2d> setY;

    private static int compareX(Vector2d v1, Vector2d v2) {
        if (v1.x == v2.x)
            return v1.y - v2.y;

        return v1.x - v2.x;
    }

    private static int compareY(Vector2d v1, Vector2d v2) {
        if (v1.y == v2.y)
            return v1.x - v2.x;

        return v1.y - v2.y;
    }

    public MapBoundary() {
        setX = new TreeSet<>(MapBoundary::compareX);
        setY = new TreeSet<>(MapBoundary::compareY);
    }

    public void addElement(IMapElement element) {
        setX.add(element.getPosition());
        setY.add(element.getPosition());
        System.out.println(setX);
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
