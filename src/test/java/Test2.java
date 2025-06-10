import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

public class Test2 {
    public static void main(String[] args) {
        ObjectProperty<String> stringObjectProperty = new SimpleObjectProperty<String>("aa"){
            {this.addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    System.out.println(newValue);
                }
            });}
        };
        stringObjectProperty.set("cc");
        SimpleListProperty<String> listProperty =new SimpleListProperty<>(FXCollections.observableArrayList());
        listProperty.add("aaa");
    }
}
