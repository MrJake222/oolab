package agh.ics.oop.engine;

import agh.ics.oop.Vector2d;
import agh.ics.oop.map.IObservable;
import agh.ics.oop.map.IPositionChangeObserver;
import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.element.Animal;
import agh.ics.oop.map.element.IMapElement;
import agh.ics.oop.map.element.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class ThreadedSimulationEngine extends Thread implements IEngine {

    private final List<Animal> animals;
    private final List<MoveDirection> directions;
    private final IWorldMap map;
    private List<IAnimalUpdateObserver> observers;

    public ThreadedSimulationEngine(IWorldMap map, List<Vector2d> positions, List<MoveDirection> directions) {
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

    public Animal getAnimalAt(int i) {
        return animals.get(i);
    }

    public void addObserverForAnimals(IPositionChangeObserver observer) {
        for (IMapElement element : map.mapObjects()) {
            if (element instanceof IObservable) {
                ((IObservable) element).addObserver(observer);
            }
        }
    }

    public void addObserver(IAnimalUpdateObserver observer) {
        observers.add(observer);
    }

    @Override
    public void run() {
        System.out.println("Starting thread");
        for (int i=0; i<directions.size(); i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println("Symulacja przerwana.");
            }

            System.out.println("move");
            animals.get(i % animals.size()).move(directions.get(i));

            for (IAnimalUpdateObserver observer : observers) {
                observer.animalUpdate();
            }


        }
    }
}
