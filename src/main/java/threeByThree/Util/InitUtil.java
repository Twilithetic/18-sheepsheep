package threeByThree.Util;

import TwiUtil.TwiUtil_JavaFX;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import threeByThree.Waters.Grass;
import threeByThree.Waters.Milk;
import threeByThree.Waters.Water;
import threeByThree.Waters.WaterFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class InitUtil {
    //
    private static final HBox basket = new HBox();
    private static final AnchorPane poolPanel = new AnchorPane();
    private static final HBox buttonBar = new HBox();
    static {
        initButtonBar();
        initPoolPanel();
        initBasket();
    }

    // init
    public static void initPoolPanel(){
        // poolPanel
        poolPanel.setPrefWidth(800);
        poolPanel.setPrefHeight(600);
        poolPanel.setStyle("-fx-background-color: cornsilk;");
        // 测试坐标系
//        TwiUtil_JavaFX.arrangement(poolPanel,25);
        // 孩子
        poolPanel.getChildren().addAll(WaterFactory.initWaters());


    }
    public static void initBasket(){
        basket.setLayoutX(125);
        basket.setLayoutY(375);
        basket.setPrefHeight(100);
        basket.setStyle("-fx-background-color: #2e3a1f;");
        basket.getChildren().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> c) {
                while (c.next()){

                }
            }
        });
    }
    public static void initButtonBar(){
        DoubleProperty waterMount = new SimpleDoubleProperty(500.0);
        buttonBar.getChildren().addAll(new Button("自动"){
            {
                this.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
//                        System.out.println("aa");
//                        Random random = new Random();
//                        Water water;
//                        do {
//                            int index = random.nextInt(poolPanel.getChildren().size());
//                            water = (Water) poolPanel.getChildren().get(index);
//                            if (water.getLayer() == 0) {
////                                System.out.println("aa");
//                                water.travel();
//                            }
//
//                        } while (water.getLayer() == 0);
                        new Task<Integer>(){
                            @Override
                            protected Integer call() throws Exception {
                                System.out.println("aa");
                                Random random = new Random();
                                Water water;
                                do {
                                    int index = random.nextInt(500);
                                    water = (Water) poolPanel.getChildren().get(index);
                                    if (water.getLayer() == 0) {
                                        System.out.println("aa");
                                        water.travel();
                                    }

                                } while (water.getLayer() == 0);
                                return null;
                            }
                        }.run();
                    }
                });

            }
        }, new Button("重开"){
            {
                this.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
//                        poolPanel.get
                        poolPanel.getChildren().clear();
                        poolPanel.getChildren().addAll(WaterFactory.getRandomWaters(10, waterMount.intValue()));

                    }
                });
            }
        },new Label("下次的数量："){
            {
                this.setGraphic(new Slider(){
                    {
                        this.setShowTickLabels(true);
                        this.setPrefWidth(300);
                        this.setMax(2500);
                        this.valueProperty().bindBidirectional(waterMount);
//                        this.setShowTickMarks(true);
                    }
                });
                this.setContentDisplay(ContentDisplay.RIGHT);
            }
        });
    }

    public static void initMainStage(Stage primaryStage){

        // vBox在root内嵌50的位置
        VBox vBox = new VBox();
        vBox.getChildren().addAll(buttonBar,poolPanel,basket);
        // root和scene设置
        AnchorPane.setTopAnchor(vBox,50.0);
        AnchorPane.setRightAnchor(vBox,50.0);
        AnchorPane.setLeftAnchor(vBox,50.0);
        AnchorPane.setBottomAnchor(vBox,50.0);
        AnchorPane root = new AnchorPane();
        root.getChildren().add(vBox);
        primaryStage.setScene(new Scene(root));
        primaryStage.setAlwaysOnTop(true);
    }

    // getter
    public static AnchorPane getPoolPanel() {
        return poolPanel;
    }
    public static HBox getBasket(){
        return basket;
    }


}
