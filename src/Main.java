import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Main extends Application {
    private ResizableCanvas canvas;
    private Map map;
    private Display.Scene scene;

    public int width;
    public int height;

    public Main(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.scene = new Display.Scene(title, width, height);
    }

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        map = new Map("Dungeon2.json");


        stage.setScene(new Scene(mainPane));
        stage.setTitle("Game");
        stage.show();
        draw(g2d);
    }

    public void draw(FXGraphics2D graphics){
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int)canvas.getWidth(), (int)canvas.getHeight());

        map.drawMap(graphics, canvas);

    }
}
