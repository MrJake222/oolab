package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {

    private final List<Animal> animals;
    private final List<MoveDirection> directions;
    private final IWorldMap map;

    public SimulationEngine(IWorldMap map, List<Vector2d> positions, List<MoveDirection> directions) {
        this.map = map;
        this.animals = new ArrayList<>();
        this.directions = directions;

        positions.forEach(position -> {
            Animal animal = new Animal(map, position);
            animals.add(animal);
            map.place(animal);
        });
    }

    public Animal getAnimalAt(int i) {
        return animals.get(i);
    }

    @Override
    public void run() {
        for (int i=0; i<directions.size(); i++) {
//            System.out.println(map);
            animals.get(i % animals.size()).move(directions.get(i));
        }

//        System.out.println(map);
    }
}
