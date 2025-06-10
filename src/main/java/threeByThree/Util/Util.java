package threeByThree.Util;

import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import threeByThree.Waters.Water;

import java.util.List;

public class Util {
    public static void basketEliminate(ObservableList<? extends Water> waters){
        InitUtil.getBasket().getChildren().removeAll(waters);
    }

    public static void removeFormPool(Water water){
        if (InitUtil.getPoolPanel().getChildren().contains(water) ) {
            InitUtil.getPoolPanel().getChildren().remove(water);
        }
    }
    public static boolean intersectBounds(Bounds bounds, Bounds bounds2){
        // todo 问题大
        Point2D leftTop = new Point2D(bounds2.getMinX(),bounds2.getMinY());
        Point2D rightTop = new Point2D(bounds2.getMaxX(),bounds2.getMinY());
        Point2D rightBottom = new Point2D(bounds2.getMaxX(),bounds2.getMaxY());
        Point2D leftBottom = new Point2D(bounds2.getMinX(),bounds2.getMaxY());
        return bounds.contains(leftTop) ||
                bounds.contains(rightTop) ||
                bounds.contains(rightBottom) ||
                bounds.contains(leftBottom);
    }
}
