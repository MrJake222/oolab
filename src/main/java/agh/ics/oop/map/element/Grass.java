package agh.ics.oop.map.element;

import agh.ics.oop.Vector2d;
import agh.ics.oop.map.IWorldMap;

public class Grass extends AbstractMapElement {

    public Grass(IWorldMap map, Vector2d position) {
        super(map, position);
    }

    @Override
    public boolean canWalkOver() {
        return true;
    }

    @Override
    public String toString() {
        return "*";
    }
}
