import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Random;

public class Test extends Application {
    public void start(Stage primaryStage) throws InterruptedException {

        Random rand = new Random();
        Circle[] circleList = new Circle[20];
        circleList[0] = new Circle();

        Group root = new Group(circleList);
        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("Twenty Circles");
        primaryStage.setScene(scene);
        primaryStage.show();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (int count = 0; count < circleList.length; count++) {
                    // 声明定义圆的圆心坐标与半径，这里的数字意义是限制所画的圆不超过scene的边界//
                    float x = 360 * rand.nextFloat() + 20;// 取值区间为[20, 380)//
                    float y = 160 * rand.nextFloat() + 20;// 取值区间为[20, 180)//
                    float Radius = 10 * rand.nextFloat() + 10;// 取值区间为[10, 20)//
                    Circle circle = new Circle(x, y, Radius);
                    circleList[count] = circle;
                    // 将每个圆的颜色初始化为黑色//
                    circleList[count].setFill(Color.BLACK);
                    if (count != 0) {
                        for (int i = 0; i < count; i++) {
                            // 遍历计算圆心距//
                            double distance = Math.sqrt(Math.pow(circleList[count].getCenterX() - circleList[i].getCenterX(), 2)
                                    + Math.pow(circleList[count].getCenterY() - circleList[i].getCenterY(), 2));
                            // 计算半径之和//
                            double RadiusSum = circleList[count].getRadius() + circleList[i].getRadius();
                            // 比较并填充颜色//
                            if (distance <= RadiusSum) {
                                // 0.3为题目所要求的alpha值透明度//
                                circleList[count].setFill(Color.rgb(0, 0, 255, 0.3));
                                circleList[i].setFill(Color.rgb(0, 0, 255, 0.3));
                            }
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
