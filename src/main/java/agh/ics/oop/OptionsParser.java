package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OptionsParser {

    public static List<MoveDirection> parse(String[] args) {
        return Arrays.stream(args)
                .filter(MoveDirection::allowed)
                .map(MoveDirection::fromCode)
                .collect(Collectors.toList());
    }
}
