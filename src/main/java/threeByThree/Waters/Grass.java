package threeByThree.Waters;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


public class Grass extends Water{
    private static final Image image = new Image("grass.png",50,50,true,false,true);
    public Grass(){
        super(image);
    }

    public Grass(Point2D point2D, AnchorPane poolPane, HBox basket) {
        super(Grass.image, point2D);
    }

}
