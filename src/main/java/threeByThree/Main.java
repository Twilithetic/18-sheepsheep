package threeByThree;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import threeByThree.Util.InitUtil;

//import static threeByThree.Data.GRASS;
//import static threeByThree.Data.MILK;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        InitUtil.initMainStage(primaryStage);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
