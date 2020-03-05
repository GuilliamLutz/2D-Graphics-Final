import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Map {

    private int width;
    private int height;

    private int tileHeight;
    private int tileWidth;

    private ArrayList<Layer> layers = new ArrayList<>();

    private ArrayList<BufferedImage> tiles = new ArrayList<>();

    public Map(String fileName) {
        JsonReader reader = null;
        reader = Json.createReader(getClass().getResourceAsStream(fileName));
        JsonObject root = reader.readObject();

        this.width = root.getInt("width");
        this.height = root.getInt("height");
        this.tileWidth = root.getInt("tilewidth");
        this.tileHeight = root.getInt("tileheight");

        for (JsonValue value :root.getJsonArray("layers")) {
            JsonObject object = (JsonObject)value;

            ArrayList<Integer> dataVal = new ArrayList<>();
            for (JsonValue val : object.getJsonArray("data")){
                dataVal.add(Integer.parseInt(val.toString()));
            }

            int height = object.getInt("height");
            int width = object.getInt("width");
            int id = object.getInt("id");
            int opacity = object.getInt("opacity");
            int x = object.getInt("x");
            int y = object.getInt("y");

            String name = object.getString("name");
            String type = object.getString("type");

            Boolean visible = object.getBoolean("visible");


            Layer layer = new Layer(dataVal, height, width, id, name, opacity, type, visible, x, y);

            this.layers.add(layer);
        }

        for (JsonValue value :root.getJsonArray("tilesets")){
            JsonObject object = (JsonObject)value;

            try {
                BufferedImage image = ImageIO.read(getClass().getResourceAsStream(object.getString("source")));



                Tileset set = new Tileset(
                        object.getString("name"),
                        object.getInt("columns"),
                        object.getInt("firstgid"),
                        object.getInt("height"),
                        object.getInt("width"),
                        object.getInt("tilecount"),
                        object.getInt("tileheight"),
                        object.getInt("tilewidth"),
                        image);


                    for(int y = 0; y < set.getImageHeight() / tileHeight; y += 1){
                        for(int x = 0; x < set.getImageWidth()/ tileWidth; x += 1){
                        BufferedImage subimage = image.getSubimage(x * tileWidth,y * tileHeight,tileWidth, tileHeight);
                        int index = (set.getFirstGid() + (y * (set.getImageWidth()/tileWidth)) + x);
                        tiles.add(index, subimage);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    public void drawMap(FXGraphics2D graphics, ResizableCanvas canvas){
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int)canvas.getWidth(), (int)canvas.getHeight());
//        int i = 0;
//        for(Layer layer: layers){
//            for(int y = 0; y < layer.getHeight(); y += 1) {
//                for (int x = 0; x < layer.getWidth(); x += 1) {
//                    graphics.drawImage(tiles.get(i), x * tileWidth, y * tileHeight, null);
//                    if(i <= tiles.size()) i++;
//                }
//            }
//
//        }

        graphics.drawImage(tiles.get(64), 0, 0, null);

    }
}
