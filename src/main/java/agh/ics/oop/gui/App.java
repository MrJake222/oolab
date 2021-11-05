package agh.ics.oop.gui;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Vector2d;
import agh.ics.oop.engine.IAnimalUpdateObserver;
import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.engine.ThreadedSimulationEngine;
import agh.ics.oop.map.*;
import agh.ics.oop.map.element.Animal;
import agh.ics.oop.map.element.Grass;
import agh.ics.oop.map.element.IMapElement;
import agh.ics.oop.map.element.MoveDirection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class App extends Application implements IPositionChangeObserver, IAnimalUpdateObserver {

    private AbstractWorldMap map;
    private ThreadedSimulationEngine engine;
    private GridPane grid;

    @Override
    public void init() throws Exception {
        List<String> args = getParameters().getRaw();

        List<MoveDirection> moves = OptionsParser.parse(args);
        List<Vector2d> positions = Arrays.asList(new Vector2d(2,2), new Vector2d(3,4));
        this.map = new GrassField(10);
        this.engine = new ThreadedSimulationEngine(map, positions, moves);
        this.engine.addObserverForAnimals(this);
        this.engine.addObserver(this);

        grid = new GridPane();
        grid.setPadding(new Insets(20));
        renderGrid();
    }

    private void renderGrid() {
        Vector2d l = map.getLowerLeft();
        Vector2d r = map.getUpperRight();
        System.out.println(l);
        System.out.println(r);

        int colIndex = 0;
        int rowIndex = r.y - l.y + 1; // height of map
        for (int y=l.y; y<=r.y; y++) {
            // draw y-coord
            grid.add(new Label("" + y), colIndex, rowIndex);
            colIndex++;

            // draw elements
            for (int x=l.x; x<=r.x; x++) {
                Vector2d pos = new Vector2d(x, y);
                if (map.isOccupied(pos)) {
                    try {
                        grid.add(new GuiElementBox(map.objectAt(pos)).getBox(), colIndex, rowIndex);
                    } catch (FileNotFoundException e) {
                        System.out.println("Nie znaleziono tekstury dla "+map.objectAt(pos)+". Pomijanie.");
                    }
                }
                colIndex++;
            }

            colIndex = 0;
            rowIndex--;
        }

        // draw (0,0)
        grid.add(new Label("y\\x"), colIndex, rowIndex);
        colIndex++;

        // draw x header
        for (int x=l.x; x<=r.x; x++) {
            grid.add(new Label("" + x), colIndex, rowIndex);
            colIndex++;
        }

        grid.getRowConstraints().add(new RowConstraints(30));
        grid.getColumnConstraints().add(new ColumnConstraints(30));
        for (int y=l.y; y<=r.y; y++) {
            grid.getRowConstraints().add(new RowConstraints(50));
        }
        for (int x=l.x; x<=r.x; x++) {
            grid.getColumnConstraints().add(new ColumnConstraints(50));
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        engine.start();

        Scene scene = new Scene(grid, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        engine.stop();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {

    }

    @Override
    public void animalUpdate() {
        System.out.println("pos changed");
        Platform.runLater(() -> {
            grid.getChildren().clear();
            renderGrid();
        });
    }
}
