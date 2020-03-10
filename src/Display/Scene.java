package Display;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Scene {

    private Stage stage;
    private Canvas canvas;

    private String title;
    private int width; //pixels
    private int height; //pixels

    public Scene(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createScene();
    }

    private void createScene(){
        this.stage = new Stage();
        this.stage.setTitle(this.title);
        this.stage.setHeight(this.height);
        this.stage.setWidth(this.width);
        this.stage.setResizable(false);
        this.stage.centerOnScreen();

        this.canvas = new Canvas();
        this.canvas.prefWidth(this.width);
        this.canvas.prefHeight(this.height);
        this.canvas.maxHeight(this.height);
        this.canvas.maxWidth(this.width);

        BorderPane mainpane = new BorderPane();
        mainpane.setCenter(this.canvas);
        this.stage.setScene(new javafx.scene.Scene(mainpane));

    }
}
