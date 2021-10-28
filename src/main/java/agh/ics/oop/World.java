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

        List<MoveDirection> moves = OptionsParser.parse(args);
        Animal animal = new Animal();
        run(animal, moves);

        System.out.println("system zakończył działanie");
    }
}
