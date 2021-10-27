package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class World {

    static List<MoveDirection> convert(String[] args) {
        return Arrays.stream(args)
                .filter(MoveDirection::allowed)
                .map(MoveDirection::fromCode)
                .collect(Collectors.toList());
    }

    static void run(List<MoveDirection> directions) {
        directions.stream()
                .map(dir -> dir.msg)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        System.out.println("system wystartował");

//        run(convert(args));
//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.upperRight(position2));

        for (MapDirection d : MapDirection.values()) {
            System.out.println(d+" unit="+d.toUnitVector());
        }

        System.out.println("system zakończył działanie");
    }
}
