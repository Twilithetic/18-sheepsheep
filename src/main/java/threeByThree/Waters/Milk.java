package threeByThree.Waters;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class Milk extends Water{
    private static final Image image = new Image("milk.png",50,50,true,false,true);
    public Milk(){
        super(image);
    }



    public Milk(Point2D point2D, AnchorPane poolPane, HBox basket) {
        super(Milk.image, point2D);
    }


}
