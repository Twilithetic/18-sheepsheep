package threeByThree.Waters;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import threeByThree.Util.InitUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class WaterFactory {


    private static final Random random = new Random();
    private static final ListProperty<Image> waterImages = new SimpleListProperty<Image>(FXCollections.observableArrayList()){
        {
            this.add(new Image("bell.png",50,100,true,false,false));
            this.add(new Image("bonfire.png",50,100,true,false,false));
            this.add(new Image("corn.png",50,100,true,false,false));
            this.add(new Image("folk.png",50,100,true,false,false));
            this.add(new Image("glove.png",50,100,true,false,false));
            this.add(new Image("grass.png",50,100,true,false,false));
            this.add(new Image("milk.png",50,100,true,false,false));
            this.add(new Image("nineTrunedIntestine.png",1000,50,true,false,false));
            this.add(new Image("stump.png",50,100,true,false,false));
            this.add(new Image("women.png",50,100,true,false,false));
        }
    };

    //init

    public static ObservableList<Water> initWaters(){
        int interval = 35;
        int mount = 500;
        // rands
        int randX;int randY;
        Image randImage;

        ObservableList<Water> waters = FXCollections.observableArrayList();
        for (int i = 0; i < mount; i++) {
            // rand
            randImage = waterImages.get(random.nextInt(waterImages.size()));
            randX = random.nextInt((int) InitUtil.getPoolPanel().getPrefWidth() ) / interval * interval;
            randY = random.nextInt((int) InitUtil.getPoolPanel().getPrefHeight() ) / interval * interval;
//            System.out.println(randY);
            Water water = getWater(randImage,new Point2D(randX,randY));
            water.checkLayerInPool(waters);
            waters.add(water);
        }
        return waters;
    }

    // waters
        // 指定的
    public static Water getWater(Image image, Point2D point2D){
        return new Water(image,point2D) {
        };
    }
        //批量的 第一个重要
    public static ObservableList<Water> getRandomWaters(int interval,int mount){
        // rands
        int randX;int randY;
        Image randImage;

        ObservableList<Water> waters = FXCollections.observableArrayList();
        for (int i = 0; i < mount; i++) {
            randImage= waterImages.get(random.nextInt(waterImages.size()));
            randX= random.nextInt((int) InitUtil.getPoolPanel().getWidth() - (int) randImage.getWidth()) / interval * interval;
            randY= random.nextInt((int) InitUtil.getPoolPanel().getHeight() - (int) randImage.getHeight()) / interval * interval;
            Water water = getWater(randImage,new Point2D(randX,randY));
            water.checkLayerInPool(waters);
            waters.add(water);
        }
        return waters;
    }
    public static Water getRandomWater(){
        return getRandomWaters(12,1).get(0);
    }
//    public static Water getRandomWater(int interval){
////        System.out.println(( (int) InitUtil.getPoolPanel().getPrefWidth() / interval ) * interval);
//
//        return getWater(waterImages.get(random.nextInt(waterImages.size())),
//                new Point2D(randX,randY));
//    }

    // adder
    public static void addImage(Image imageAdded){
        waterImages.add(imageAdded);
    }

    // getter
    public static ObservableList<Image> getWaterImages() {
        return waterImages.get();
    }

    public static ListProperty<Image> waterImagesProperty() {
        return waterImages;
    }
    public static Random getRandom() {
        return random;
    }

}
