package agh.ics.oop;

import agh.ics.oop.map.element.MoveDirection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OptionsParser {

    public static List<MoveDirection> parse(String[] args) {
        return Arrays.stream(args)
                .map(MoveDirection::fromCode)
                .collect(Collectors.toList());
    }
}
