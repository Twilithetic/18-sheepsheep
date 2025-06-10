package threeByThree.Util;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import threeByThree.Waters.Grass;
import threeByThree.Waters.Milk;
import threeByThree.Waters.Water;
import threeByThree.Util.Util.*;
import threeByThree.Waters.WaterFactory;

public class BasketSorter {

    private static final ListProperty<ListProperty<Water>> allKindWaterSorted = new SimpleListProperty<>(FXCollections.observableArrayList());
    static {
        for (int i = 0; i < WaterFactory.getWaterImages().size(); i++) {
            allKindWaterSorted.add(waterListPropertyFactory());
        }
        WaterFactory.waterImagesProperty().addListener(new ListChangeListener<Image>() {
            @Override
            public void onChanged(Change<? extends Image> c) {
                for (int i = 0; i < c.getList().size(); i++) {
                    allKindWaterSorted.add(waterListPropertyFactory());
                }
            }
        });
    }
//    private static final ListProperty<Water> milks = waterListPropertyFactory();
//    private static final ListProperty<Water> grasses = waterListPropertyFactory();


    public static void sort(Water waterAdded){
//        System.out.println(allKindWaterSorted.get().size());
        // water是被添加的
//        System.out.println("Aa");
        if (WaterFactory.getWaterImages().contains(waterAdded.getImage())){
            int index_Image = WaterFactory.getWaterImages().indexOf(waterAdded.getImage());
            allKindWaterSorted.get(index_Image).add(waterAdded);
        }
//        System.out.println(waterAdded.getClass());
//        if (waterAdded.getImage()){
//            milks.add(waterAdded);
//        } else if (waterAdded instanceof Grass){
//            grasses.add(waterAdded);
//        }
    }

    private static ListProperty<Water> waterListPropertyFactory(){
        return  new SimpleListProperty<Water>(FXCollections.observableArrayList()) {
            {
                this.addListener((ListChangeListener<Water>) c -> {
//                    // milks计数
//                    counter = c.getList().size();

                    // 增加的Water处理
                    while (c.next()) {
                        // 新增加的Water
                        Water waterAdded = c.getAddedSubList().get(0);
                        // 确保basket里至少有一个milk时
                        if (c.getList().size() - 2 >= 0 && (c.getList().size() - 1) % 3 != 0) {
                            Water farWater = c.getList().get(c.getList().size() - 2);
                            if (InitUtil.getBasket().getChildren().contains(farWater)) {
                                InitUtil.getBasket().getChildren().add(InitUtil.getBasket().getChildren().indexOf(farWater), waterAdded);
                            }
                        } else {
                            InitUtil.getBasket().getChildren().add(waterAdded);
                        }
                    }
                    // 确保basket有三个相应water时就消掉
                    if (c.getList().size() % 3 == 0) {
                        Util.basketEliminate(c.getList());
                    }
                });
            }
        };
    }
}
