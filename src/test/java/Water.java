import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import threeByThree.Util.BasketSorter;
import threeByThree.Util.Util;
import threeByThree.Waters.WaterFactory;

import java.util.concurrent.Callable;

public abstract class Water extends ImageView {
    // 被覆盖的数量
    // todo 一个下层水被两个上层覆盖时 原因：下层的layer只能绑定一个上层
    //
    private int countCovered = 0;
    // 层数
    private final IntegerProperty layer = new SimpleIntegerProperty(0){
        {
            IntegerProperty aa = this;
            Water.this.effectProperty().bind(Bindings.createObjectBinding(new Callable<ColorAdjust>() {
                @Override
                public ColorAdjust call() throws Exception {
                    ColorAdjust dark = new ColorAdjust();
                    if (aa.get() == 1.0){return dark;}

                    dark.setBrightness( aa.get() / (1.0 - aa.get()));
                    return dark;
                }
            },aa));
            this.addListener((observable, oldValue, newValue) -> {
                if (newValue.doubleValue() == 0){
                    aa.unbind();
                }
            });
        }

    };
    // 构造器
    public Water() {
    }
    public Water(Image image) {
        this(image,new Point2D(0,0));

    }
    public Water(Image image, Point2D point2D){
        super(image);
        this.setX(point2D.getX());this.setY(point2D.getY());
        // todo 可以不写吗
//        this.setFitWidth(50);this.setFitHeight(50);

//todo        initSpecial(image);
        initMouseClicked();
    }
    // init
    private void initMouseClicked(){
        this.setOnMouseClicked(event -> {
            travel();
        });
    }
    private void initSpecial(Image image){
        if (image == WaterFactory.waterImagesProperty().get(7) && WaterFactory.getRandom().nextBoolean()){
//            System.out.println(image);
            this.setRotate(90);
        }
    }
    // 各种功能
    public void travel(){
        if (layer.get() == 0) {
            // layer
            this.layer.set(1);
            // special
//todo            special();
            //
//            Util.removeFormPool(this);
//            BasketSorter.sort(this);
        }
    }
    public boolean intersect(Water other){
        return this.getLayoutBounds().intersects(other.getLayoutBounds());
    }
    public void checkLayerInPool(ObservableList<Water> waters){
        if (!waters.isEmpty()){
            waters.forEach(water -> {
                if (this.intersect(water) && water.getLayer() == 0){
                    water.addLayerWater(this);
                }
            });
        }
    }
    private void special(){
        if (getImage() == WaterFactory.waterImagesProperty().get(7)){
            setRotate(0);
        }
    }
    private void addLayerWater(Water up){
        // todo 被覆盖
        this.layerProperty().bind(up.layerProperty().subtract(1));
    }

    // getter and setter
        // layer
    public int getLayer() {
        return layer.get();
    }

    public IntegerProperty layerProperty() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer.set(layer);
    }




}
