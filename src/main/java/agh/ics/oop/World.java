package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
