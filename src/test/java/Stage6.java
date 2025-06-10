import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Stage6 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(new AnchorPane()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
