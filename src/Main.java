import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;


import java.awt.*;
import java.awt.geom.AffineTransform;

public class Main extends Application {
    private static ResizableCanvas canvas;
    private Map map;

    public int width;
    public int height;

    public static ResizableCanvas getCanvas(){
        return canvas;
    }

    /*public Main(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.scene = new Display.Scene(title, width, height);
    }*/

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane gamePane = new BorderPane();

        canvas = new ResizableCanvas(g -> draw(g), gamePane);
        canvas.setFocusTraversable(true);


        Button start = new Button("Start Game");
        start.setFont(Font.font("Kalam", 50));
        start.setTextFill(javafx.scene.paint.Paint.valueOf("White"));
        start.setStyle("-fx-border-color: transparent;" +
                " -fx-border-width: 0;\n" +
                "    -fx-background-radius: 0;\n" +
                "    -fx-background-color: transparent;"+
                "-fx-stroke: firebrick;"+
        "-fx-stroke-width: 2px;"
                );


        BorderPane menuPane = new BorderPane();

        Image image2 = new Image(getClass().getResourceAsStream("/dungeonAchtergrond.jpg"));

        BackgroundSize bSize = new BackgroundSize(1920, 1100, false, false, true, true);

        Background background2 = new Background(new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize));

        menuPane.setBackground(background2);

        menuPane.setPrefHeight(1080);
        menuPane.setMaxHeight(1080);
        menuPane.setPrefWidth(1920);
        menuPane.setMaxWidth(1920);
        menuPane.setCenter(start);


        stage.setScene(new Scene(menuPane));




        gamePane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        map = new Map("Dungeon2.json");


        start.setOnAction(event -> stage.setScene(new Scene(gamePane)));
        stage.setTitle("Game");
        stage.show();
        draw(g2d);

        new AnimationTimer() {
            long last = (long)-1.0;
            @Override
            public void handle(long now) {
                if(last == -1)
                    last = now;
                update((now - last) / (long)10000000.0);
                System.out.println((now - last) / (long)10000000.0);
                last = now;
                draw(g2d);
            }
        }.start();
    }

    private void update(long v) {
       Character.getInstance().update(v);
    }

    public void draw(FXGraphics2D graphics){
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int)canvas.getWidth(), (int)canvas.getHeight());

        map.drawMap(graphics, canvas);


        Character.getInstance().draw(graphics);
    }

}
