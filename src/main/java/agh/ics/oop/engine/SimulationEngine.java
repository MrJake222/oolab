package agh.ics.oop.engine;

import agh.ics.oop.Vector2d;
import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.element.Animal;
import agh.ics.oop.map.element.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable {

    private final List<Animal> animals;
    private List<MoveDirection> directions;
    private final IWorldMap map;
    private final List<IAnimalUpdateObserver> observers;

    private long moveDelay = 0;

    public SimulationEngine(IWorldMap map, List<Vector2d> positions, List<MoveDirection> directions) {
        this.map = map;
        this.animals = new ArrayList<>();
        this.directions = directions;
        this.observers = new ArrayList<>();

        positions.forEach(position -> {
            Animal animal = new Animal(map, position);
            animals.add(animal);
            map.place(animal);
        });
    }

    public void setMoveDelay(long moveDelay) {
        this.moveDelay = moveDelay;
    }

    public void addObserver(IAnimalUpdateObserver observer) {
        observers.add(observer);
    }

    public void setDirections(List<MoveDirection> directions) {
        this.directions = directions;
    }

    public Animal getAnimalAt(int i) {
        return animals.get(i);
    }

    @Override
    public void run() {
        for (int i=0; i<directions.size(); i++) {
//            System.out.println(map);
            animals.get(i % animals.size()).move(directions.get(i));

            for (IAnimalUpdateObserver observer : observers) {
                observer.animalUpdate();
            }

            if (moveDelay > 0) {
                try {
                    Thread.sleep(moveDelay);
                } catch (InterruptedException e) {
                    System.out.println("Symulacja przerwana.");
                }
            }
        }

//        System.out.println(map);
    }
}
