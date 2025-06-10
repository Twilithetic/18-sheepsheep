import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import threeByThree.Util.Util;
import threeByThree.Waters.Grass;
import threeByThree.Waters.Milk;

public class Stage4 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Grass grass = new Grass(new Point2D(110,110),null,null);
        Milk milk = new Milk(new Point2D(120,79), null,null);
//        grass.setEffect(getEffect());
//        grass.effectProperty().set(new ColorAdjust());
        System.out.println(grass.prefHeight(-1));
//        grass.layerProperty().
        grass.layerProperty().bind(milk.layerProperty().subtract(1));

        primaryStage.setScene(new Scene(new AnchorPane(){
            {
                this.getChildren().addAll(grass,milk);
            }
        }));
        primaryStage.show();
        System.out.println(grass.getX());
        System.out.println(grass.getFitHeight());
        System.out.println("contin: " +grass.getLayoutBounds().contains(120,120));
        System.out.println("inter: " + grass.getLayoutBounds().intersects(milk.getLayoutBounds()));
        System.out.println("myinter: "+ Util.intersectBounds(grass.getLayoutBounds(), milk.getLayoutBounds()));
//        System.out.println(Util.intersectBounds(grass.getLayoutBounds().contains(120,120));
    }
    private Effect getEffect(){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5);
        return colorAdjust;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
