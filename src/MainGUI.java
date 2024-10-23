//Autor: Jesus Eduardo Hernandez Bravo
//Git: https://github.com/Lalitho14/PTAutotransportes/

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
    stage.setScene(new Scene(root, 600, 400));
    stage.setWidth(600);
    stage.setHeight(400);
    stage.setMinWidth(600);
    stage.setMinHeight(400);
    stage.resizableProperty().setValue(Boolean.FALSE);
    stage.show();
  }
}
