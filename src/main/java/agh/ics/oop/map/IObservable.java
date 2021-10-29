package agh.ics.oop.map;

public interface IObservable {

    /**
     * Adds observer to be notified of position changes.
     * @param observer Observer instance.
     */
    void addObserver(IPositionChangeObserver observer);

    /**
     * Removes observer that no longer wants to be notified.
     * @param observer Observer instance.
     */
    void removeObserver(IPositionChangeObserver observer);
}
