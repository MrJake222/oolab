package agh.ics.oop.map;

import agh.ics.oop.Vector2d;

public interface IPositionChangeObserver {

    /**
     * Notifies observer about position change.
     * @param oldPosition Old position.
     * @param newPosition New position.
     */
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
