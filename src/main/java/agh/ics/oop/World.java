package agh.ics.oop;

import agh.ics.oop.engine.IEngine;
import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.map.GrassField;
import agh.ics.oop.map.IWorldMap;
import agh.ics.oop.map.RectangularMap;
import agh.ics.oop.map.element.Animal;
import agh.ics.oop.map.element.MoveDirection;

import java.util.Arrays;
import java.util.List;

public class World {

    static void run(String[] movesStr) {
        List<MoveDirection> moves = OptionsParser.parse(movesStr);
        IWorldMap map = new GrassField(0);
        List<Vector2d> positions = Arrays.asList(new Vector2d(2,2), new Vector2d(3,4));
        IEngine engine = new SimulationEngine(map, positions, moves);
        engine.run();
    }

    public static void main(String[] args) {
        System.out.println("system wystartował");

        try {
            run(args);
        } catch (IllegalArgumentException e) {
            System.out.println("niepoprawny argument, "+e.getMessage());
        }

        System.out.println("system zakończył działanie");
    }
}
