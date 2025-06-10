import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import lombok.extern.slf4j.Slf4j;
import threeByThree.Waters.Water;

import java.util.concurrent.Callable;

@Slf4j
public class Test7 {
    public static void main(String[] args) {
//        IntegerBinding integerBinding = Bindings.createIntegerBinding(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return 123;
//            }
//        });
        IntegerProperty integerProperty = new SimpleIntegerProperty(11);
        integerProperty.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                log.info("invalidated");
            }
        });
        integerProperty.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                log.info("changed");
            }
        });
        log.info("before");
        integerProperty.set(3);
        integerProperty.set(4);
//        integerBinding.bind();
        log.info(""+integerProperty.get());
        integerProperty.set(3);
        integerProperty.set(4);

    }
}
