package agh.ics.oop.map.element;

import agh.ics.oop.Vector2d;

public interface IMapElement {

    /**
     * Checks if Element is on {@param position}.
     * @param position Position to check for.
     * @return {@code true} if Element on given position.
     */
    boolean isAt(Vector2d position);

    /**
     * Gets current Element's position.
     * @return current position.
     */
    Vector2d getPosition();

    /**
     * Can the object be walked over by for ex. an animal.
     * @return {@code true} for grass for ex.
     */
    boolean canWalkOver();
}
