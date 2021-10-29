package agh.ics.oop.map;

import agh.ics.oop.Vector2d;
import agh.ics.oop.map.element.Animal;
import agh.ics.oop.map.element.IMapElement;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap {

    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    public RectangularMap(int width, int height) {
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width-1, height-1);
    }

    @Override
    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    @Override
    public Vector2d getUpperRight() {
        return upperRight;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) &&
                position.follows(lowerLeft) &&
                position.precedes(upperRight);
    }

    @Override
    public boolean place(IMapElement element) {
        if (element.getPosition().follows(lowerLeft) &&
            element.getPosition().precedes(upperRight)) {

            return super.place(element);
        }

        throw new IllegalArgumentException("unable to place "+element+" outside of map");
    }
}
