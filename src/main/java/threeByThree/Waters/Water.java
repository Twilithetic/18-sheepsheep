package threeByThree.Waters;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import lombok.extern.slf4j.Slf4j;
import threeByThree.Util.BasketSorter;
import threeByThree.Util.Util;

import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

@Slf4j
public abstract class Water extends ImageView {
    // layer 会在addLowerWater()里减少 在travel()里提升 =零时才可点击
    private final IntegerProperty layer = new SimpleIntegerProperty(0);
    // 通知哪些被压着的Water
    private final ObservableList<Water> lowWaters = FXCollections.observableArrayList();
    {
        setOnMouseClicked(event -> travel());
        this.effectProperty().bind(Bindings.createObjectBinding(() -> {
            ColorAdjust dark = new ColorAdjust();
            if (layer.get() == 1.0){return dark;}

            dark.setBrightness( layer.get() / (1.0 - layer.get()));
            return dark;
        }, layer));

//        this.dis
    }
     // 构造器
    public Water() {
    }
    public Water(Image image) {
        this(image,new javafx.geometry.Point2D(0,0));

    }
    public Water(Image image, Point2D point2D){
        super(image);
        this.setX(point2D.getX());this.setY(point2D.getY());
    }

    // 点击时触发的方法
    public void travel(){
        log.info(""+layer.get());
        if (isCanClick()) {
            lowWaters.forEach(water -> water.addLayer(1));
            Util.removeFormPool(this);
            BasketSorter.sort(this);
        }
    }

    // 确定Water的位置
    public void checkLayerInPool(ObservableList<Water> waters){

        if (!waters.isEmpty()){
            waters.forEach(water -> {
                if (this.intersect(water)){//  && water.getLayer() == 0
                    addLowLayerWater(water);
                }
            });
        }
    }

    // 确定Water位置后的执行
    private void addLowLayerWater(Water lowWater){
        lowWaters.add(lowWater);
        lowWater.subLayer(1);
//        System.out.println(lowWater.getLayer());
    }
    // 是否两个有重叠，用在生成的时候
    public boolean intersect(Water other){
        return this.getLayoutBounds().intersects(other.getLayoutBounds());
    }


    // lessImportant
    public int getLayer() {
        return layer.get();
    }

    public boolean isCanClick(){
        return layer.get() == 0;
    }
    public IntegerProperty layerProperty() {
        return layer;
    }

    public void addLayer(int mount){
        this.layer.set(layer.get() + mount);
    }

    public void subLayer(int mount){
        this.layer.set(layer.get() - mount);
    }
    public void setLayer(int layer) {
        this.layer.set(layer);
    }

}
