package Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainGUI extends Application {
  public static void main(String[] args) {
    launch(args);
  }
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GUI/DataBaseSetUp.fxml")));
    stage.setTitle("Autotransportes");
    stage.setScene(new Scene(root, 800, 600));
    stage.setWidth(800);
    stage.setHeight(600);
    stage.setMinWidth(800);
    stage.setMinHeight(600);
    stage.show();
  }
}
