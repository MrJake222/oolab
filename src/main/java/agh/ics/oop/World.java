package agh.ics.oop;

import agh.ics.oop.engine.IEngine;
import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.RectangularMap;
import agh.ics.oop.map.element.Animal;
import agh.ics.oop.map.element.MoveDirection;

import java.util.Arrays;
import java.util.List;

public class World {

    static void run(Animal animal, List<MoveDirection> directions) {
        directions.forEach(animal::move);
        System.out.println(animal);
    }

    public static void main(String[] args) {
        System.out.println("system wystartował");

//        List<MoveDirection> moves = OptionsParser.parse(args);
//        Animal animal = new Animal();
//        run(animal, moves);
        List<MoveDirection> moves = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        List<Vector2d> positions = Arrays.asList(new Vector2d(2,2), new Vector2d(3,4));
        IEngine engine = new SimulationEngine(map, positions, moves);
        engine.run();

        System.out.println("system zakończył działanie");
    }
}
