package agh.ics.oop.map;

import agh.ics.oop.Vector2d;
import agh.ics.oop.map.element.Animal;
import agh.ics.oop.map.element.IMapElement;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) {
        super(width, height);
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

        return false;
    }
}
