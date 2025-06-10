import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import threeByThree.Waters.Grass;
import threeByThree.Waters.Milk;
import threeByThree.Waters.Water;

import java.util.Comparator;

public class Stage2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ObservableList<Water> waters = FXCollections.observableArrayList(new Grass(),new Milk(), new Grass());
        System.out.println(waters);
        primaryStage.setScene(new Scene(new AnchorPane(){
            {
                this.getChildren().add(new HBox(){
                    {
                        this.getChildren().addAll(waters);
                    }
                });
            }
        }));
        primaryStage.show();
        Thread.sleep(100);
//        waters.sort(new Comparator<Node>() {
//            @Override
//            public int compare(Node o1, Node o2) {
//                Water w1 = (Water) o1;
//                Water w2 = (Water) o2;
////                System.out.println(w1.getPriority() < w2.getPriority());
//                if (w1.getPriority() < w2.getPriority()){
//                    return 1;
//                }
//                else if (w1.getPriority() > w2.getPriority()){
//
//                    return -1;
//                }
//                else {
//                    return 0;
//                }
//            }
//        });
        System.out.println(waters);
        waters.add(new Grass());
        System.out.println(waters);
//        waters.sort(new Comparator<Node>() {
//            @Override
//            public int compare(Node o1, Node o2) {
//                Water w1 = (Water) o1;
//                Water w2 = (Water) o2;
////                System.out.println(w1.getPriority() < w2.getPriority());
//                if (w1.getPriority() < w2.getPriority()){
//                    return 1;
//                }
//                else if (w1.getPriority() > w2.getPriority()){
//
//                    return -1;
//                }
//                else {
//                    return 0;
//                }
//            }
//        });
        System.out.println(waters);

//        waters
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
