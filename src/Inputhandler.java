import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.awt.geom.Point2D;
import java.util.HashSet;

public class Inputhandler {

    // Set of currently heldDownKeys keys
    private HashSet<KeyCode> heldDownKeys;
    private Point2D.Double mousePosition;

    private static Inputhandler instance;

    public static Inputhandler getInstance() {
        if (instance == null){
            instance = new Inputhandler(Main.getCanvas());
        }
        return instance;
    }

    private Inputhandler(Canvas canvas) {
        this.heldDownKeys = new HashSet<>();
        this.mousePosition = new Point2D.Double(0, 0);

        try {
            canvas.setOnKeyPressed(this::keyPressed);
            canvas.setOnKeyReleased(this::keyReleased);
            canvas.setOnMouseMoved(this::mouseMoved);
            canvas.setOnMouseClicked(this::mousButtonClicked);
        } catch (NullPointerException nullPointer) {
            System.out.println("                                 ERROR\n" +
                    "#########################################################################################\n" +
                    "When initialising the Input reader the Canvas from the BaseGameView returned Null\n" +
                    "This could be because you haven't initialised the BaseGameView before the BaseGameInput\n" +
                    "#########################################################################################\n");

            nullPointer.printStackTrace();

            System.out.println("\n\nNow Closing Application");
            System.exit(-1);
        }
    }


    public HashSet<KeyCode> getKeys() {
        return this.heldDownKeys;
    }


    public Point2D.Double getMousePos() {
        return this.mousePosition;
    }

    private synchronized void keyPressed(KeyEvent e) {
        this.heldDownKeys.add(e.getCode());
    }

    private synchronized void keyReleased(KeyEvent e) {
        this.heldDownKeys.remove(e.getCode());
    }

    private synchronized void mouseMoved(MouseEvent e) {
        this.mousePosition = new Point2D.Double(e.getX(), e.getY());
    }

    private synchronized void mousButtonClicked(MouseEvent e) {
    }

    public HashSet<KeyCode> getHeldDownKeys() {
        return this.heldDownKeys;
    }

}
