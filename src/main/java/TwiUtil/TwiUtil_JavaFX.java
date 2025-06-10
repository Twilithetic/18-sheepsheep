package TwiUtil;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class TwiUtil_JavaFX {

    public static void arrangement(AnchorPane anchorPane, double interval){
        // 原点添加：i % 5 时的那个线上，添加一个监听widthProperty
        ListProperty<Node> gymBags = new SimpleListProperty<Node>(FXCollections.observableArrayList()){
            {this.addListener((ListChangeListener<Node>) c -> {
                while (c.next()){
                    anchorPane.getChildren().addAll(c.getAddedSubList());
                }
            });}
        };

        // 水平线：startX = 0, startY = i * interval, endX = anchorPane.heightProperty(), endY = i * interval
        // about horizon
        ListProperty<Line> horizonLines = new SimpleListProperty<Line>(FXCollections.observableArrayList()){
            {this.addListener((ListChangeListener<Line>) c -> {
                while (c.next()){
                    // 添加新的farHorizonLine
                    anchorPane.getChildren().addAll(c.getAddedSubList());
                    // 每五行添加原点标识
                    if( (c.getList().size() - 1) % 5 == 0 && c.getList().size() != 1){
                        Line var = c.getAddedSubList().get(c.getAddedSize() - 1);
                        // 可增加
                        ObjectProperty<Circle> farHorizonCircle = new SimpleObjectProperty<Circle>(new Circle(0,0,1)){
                            {
                                this.addListener((observable, oldValue, newValue) -> gymBags.add(newValue));
                            }
                        };
                        ObjectProperty<Text> farHorizonText = new SimpleObjectProperty<Text>(new Text()){
                            {
                                this.addListener((observable, oldValue, newValue) -> gymBags.add(newValue));
                            }
                        };
                        anchorPane.widthProperty().addListener((observable, oldValue, newValue) -> {
                            double width = newValue.doubleValue();
                            while(farHorizonCircle.get().getCenterX() + 5 * interval < width){
                                double centerX = farHorizonCircle.get().getCenterX() + 5 * interval;
                                double centerY = var.getStartY();


                                farHorizonText.set(new Text(centerX, centerY + interval / 2,String.format("(%s,%s)", (int)centerX,(int)centerY)){
                                    {
                                        this.setFill(Color.GREY);
                                    }
                                });
                                farHorizonCircle.set(new Circle(centerX, centerY,interval / 4, Color.RED));
                            }
                        });
                    };
                }
            });}
        };
        ObjectProperty<Line> farHorizonLine = new SimpleObjectProperty<Line>(new Line()){
            {this.addListener((observable, oldValue, newValue) -> {
                horizonLines.add(newValue);
            });}
        };
        anchorPane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double height = newValue.doubleValue();
                while (farHorizonLine.get().getStartY() + interval < height){
                    farHorizonLine.set(new Line(){
                        {
                            // X
                            this.setStartX(0);
                            this.endXProperty().bind(anchorPane.widthProperty());
                            // Y
                            this.setStartY(horizonLines.size() * interval);
                            this.setEndY(horizonLines.size() * interval);
                        }
                    });
                }
            }
        });

        // 垂直线：startX = i * interval, startY = 0, endX = i * interval, endY = anchorPane.widthProperty()
        // about vertica
        ListProperty<Line> verticaLines = new SimpleListProperty<Line>(FXCollections.observableArrayList()){
            {this.addListener((ListChangeListener<Line>) c -> {
                while (c.next()){
                    // 添加新的farHorizonLine
                    anchorPane.getChildren().addAll(c.getAddedSubList());
                    // 每五行添加原点标识
                    if( (c.getList().size() - 1) % 5 == 0 && c.getList().size() != 1){
                        Line var = c.getAddedSubList().get(c.getAddedSize() - 1);
                        // 可增加
                        ObjectProperty<Circle> farVerticaCircle = new SimpleObjectProperty<Circle>(new Circle(0,0,1)){
                            {
                                this.addListener((observable, oldValue, newValue) -> gymBags.add(newValue));
                            }
                        };
                        ObjectProperty<Text> farVerticaText = new SimpleObjectProperty<Text>(new Text()){
                            {
                                this.addListener((observable, oldValue, newValue) -> gymBags.add(newValue));
                            }
                        };
                        anchorPane.heightProperty().addListener((observable, oldValue, newValue) -> {
                            double height = newValue.doubleValue();
                            while(farVerticaCircle.get().getCenterY() + 5 * interval < height){
                                double centerY = farVerticaCircle.get().getCenterY() + 5 * interval;
                                double centerX = var.getStartX();


                                farVerticaText.set(new Text(centerX, centerY + interval / 2,String.format("(%s,%s)", (int)centerX,(int)centerY)){
                                    {
                                        this.setFill(Color.GREY);
                                    }
                                });
                                farVerticaCircle.set(new Circle(centerX, centerY,interval / 4, Color.RED));
                            }
                        });
                    };
                }
            });}
        };
        ObjectProperty<Line> farVerticaLine = new SimpleObjectProperty<Line>(new Line()){
            {this.addListener((observable, oldValue, newValue) -> {
                verticaLines.add(newValue);
            });}
        };
        anchorPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double width = newValue.doubleValue();
                while (farVerticaLine.get().getStartX() + interval < width){
                    farVerticaLine.set(new Line(){
                        {
                            // X
                            this.setStartX(verticaLines.size() * interval);
                            this.setEndX(verticaLines.size() * interval);
                            // Y
                            this.setStartY(0);
                            this.endYProperty().bind(anchorPane.heightProperty());
                        }
                    });
                }
            }
        });
    }

    private static void markLines(AnchorPane arrangeAnchorPane,Line var){
        // ...
    }



}
