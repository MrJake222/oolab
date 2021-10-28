package agh.ics.oop.map.element;

import agh.ics.oop.Vector2d;
import agh.ics.oop.map.IWorldMap;

public abstract class AbstractMapElement implements IMapElement {

    protected IWorldMap map;
    protected Vector2d position;

    public AbstractMapElement(IWorldMap map, Vector2d position) {
        this.map = map;
        this.position = position;
    }

    @Override
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }
}
