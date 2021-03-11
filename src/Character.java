import GameObjects.GameObject;
import javafx.animation.KeyValue;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class Character extends GameObject {

    private static Character instance;

    public static Character getInstance() {
        if (instance == null){
            instance = new Character();
        }
        return instance;
    }

    private Character() {
        super("/warrior.png", new Point2D(300,300), -1);
        loadCorrectSprite();
    }

    public void loadCorrectSprite(){
        this.image = this.image.getSubimage(0,0,32,32);
    }

    public void update(long deltatimer){
        for (KeyCode key :Inputhandler.getInstance().getKeys()) {
            System.out.println(key);
            if ( key == KeyCode.W){
                this.offset = new Point2D(offset.getX(), offset.getY()- deltatimer);
                System.out.println(this.offset.getX()+ " " + this.offset.getY());
                System.out.println(deltatimer);
            }
            else if (key == KeyCode.S){
                this.offset = new Point2D(offset.getX(), offset.getY()+ deltatimer);
                System.out.println(this.offset.getX() + " " + this.offset.getY());
                System.out.println(deltatimer);
            }
            else if (key == KeyCode.D){
                this.offset = new Point2D(offset.getX() + deltatimer, offset.getY());
                System.out.println(this.offset.getX() + " " + this.offset.getY());
                System.out.println(deltatimer);
            }
            else if (key == KeyCode.A){
                this.offset = new Point2D(offset.getX() - deltatimer, offset.getY());
                System.out.println(this.offset.getX() + " " +  this.offset.getY());
                System.out.println(deltatimer);
            }
        }


    }
}
