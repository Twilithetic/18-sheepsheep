import TwiUtil.TwiUtil_JavaFX;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import threeByThree.Util.Util;
import threeByThree.Waters.Grass;
import threeByThree.Waters.Milk;


public class Stage3 extends Application {
    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Grass grass = new Grass(new Point2D(110,110),null,null);
        Milk milk = new Milk(new Point2D(120,120), null,null);
        Label grassL = new Label(){
            {
                this.setLayoutX(310);
                this.setLayoutY(110);
                this.setGraphic(grass);
            }
        };
        primaryStage.setScene(new Scene(new AnchorPane(){
            {
                this.getChildren().addAll(grass,milk);
            }
        }));

        primaryStage.show();
        // imageView 竟然没有长度
        System.out.println(grassL.getBoundsInParent());
        System.out.println(grass.getBoundsInParent());
        System.out.println(grass.getBoundsInLocal());
        System.out.println(grassL.getLayoutBounds().contains(120,120));
//        System.out.println(Util.intersectBounds(grass.getLayoutBounds(), milk.getLayoutBounds()));

    }
}
