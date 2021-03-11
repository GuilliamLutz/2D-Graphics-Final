package GameObjects;

import javafx.geometry.Point2D;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;

import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class GameObject {

    protected BufferedImage image;
    protected Point2D offset;
    private double scale;

    public GameObject(String imageFile, Point2D offset, double scale) {
        this.offset = offset;
        this.scale = scale;
        try {
            image = ImageIO.read(getClass().getResource(imageFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(FXGraphics2D g2d) {
        if (image == null) {
            return;
        }

        AffineTransform tx = new AffineTransform();
//        tx.translate(body.getTransform().getTranslationX() * 100, body.getTransform().getTranslationY() * 100);
//        tx.rotate(body.getTransform().getRotation());
        tx.scale(-scale, -scale);
        tx.translate(this.offset.getX(), this.offset.getY());

        tx.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        g2d.drawImage(image, tx, null);

    }
}
