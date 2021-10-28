package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {

    private final int width;
    private final int height;
    private final Vector2d upperRight;
    private final Vector2d lowerLeft;
    private final List<Animal> animals;
    private final MapVisualizer visualizer;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.upperRight = new Vector2d(this.width-1, this.height-1);
        this.lowerLeft = new Vector2d(0,0);
        this.animals = new ArrayList<>();
        this.visualizer = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeft) &&
                position.precedes(upperRight) &&
                !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())) {
            animals.add(animal);
            return true;
        }

        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return visualizer.draw(lowerLeft, upperRight);
    }
}
