package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class World {

    static List<Direction> convert(String[] args) {
        return Arrays.stream(args)
                .filter(Direction::allowed)
                .map(Direction::fromCode)
                .collect(Collectors.toList());
    }

    static void run(List<Direction> directions) {
        directions.stream()
                .map(dir -> dir.msg)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        System.out.println("system wystartował");

        run(convert(args));

        System.out.println("system zakończył działanie");
    }
}
