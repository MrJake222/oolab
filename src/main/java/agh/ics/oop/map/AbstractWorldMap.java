package agh.ics.oop.map;

import agh.ics.oop.Vector2d;
import agh.ics.oop.map.element.IMapElement;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap {

    protected int width;
    protected int height;
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    private final List<IMapElement> mapElements;
    private final MapVisualizer visualizer;

    public AbstractWorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(this.width-1, this.height-1);
        this.mapElements = new ArrayList<>();
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
        if (!isOccupied(element.getPosition())) {
            mapElements.add(element);
            return true;
        }

        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (IMapElement element : mapElements) {
            if (element.isAt(position)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public IMapElement objectAt(Vector2d position) {
        for (IMapElement element : mapElements) {
            if (element.isAt(position)) {
                return element;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return visualizer.draw(lowerLeft, upperRight);
    }
}
