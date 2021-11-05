package agh.ics.oop.gui;

import agh.ics.oop.map.element.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {

    private VBox box;

    public GuiElementBox(IMapElement element) throws FileNotFoundException {
        Image image = new Image(new FileInputStream("src/main/resources/" + element.getTexturePath()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);

        Label label = new Label(element.getName());
        label.setTextAlignment(TextAlignment.CENTER);

        this.box = new VBox(imageView, label);
        this.box.setAlignment(Pos.CENTER);
    }

    public VBox getBox() {
        return box;
    }
}
