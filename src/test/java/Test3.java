import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Test3 {
    public static void main(String[] args) {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("aa");
        System.out.println(observableList);
        observableList.remove("bb");
//        observableList.remove(5);
    }
}
