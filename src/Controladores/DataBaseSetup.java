//Autor: Jesus Eduardo Hernandez Bravo
//Git: https://github.com/Lalitho14/PTAutotransportes/

package Controladores;

import Implementacion.ConexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;

public class DataBaseSetup {

  @FXML
  private TextField bd_txt;

  @FXML
  private Button cancelar_btn;

  @FXML
  private Button conectar_btn;

  @FXML
  private TextField dirIp_txt;

  @FXML
  private TextField puerto_txt;

  @FXML
  private PasswordField pwd_txt;

  @FXML
  private TextField usr_txt;

  @FXML
  void CancelarActionButton(ActionEvent event) {
    System.exit(0);
  }

  @FXML
  void ConectarBaseDatos(ActionEvent event) throws IOException {
    Connection conexion = null;

    if(!Objects.equals(usr_txt.getText(), "")&& !Objects.equals(bd_txt.getText(),"") && !Objects.equals(dirIp_txt.getText(), "") && !Objects.equals(puerto_txt.getText(), "")) {
      ConexionBD bd = new ConexionBD(usr_txt.getText(),pwd_txt.getText(),bd_txt.getText(),dirIp_txt.getText(),puerto_txt.getText());
      conexion = bd.Conectar();
    }

    if(conexion != null) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/MainPanel.fxml"));
      Parent root = loader.load();
      MainPanel mainPanel = loader.getController();
      mainPanel.SetConnection(conexion);
      Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      Scene scene = new Scene(root);
      stage.setWidth(900);
      stage.setHeight(600);
      stage.setResizable(true);
      stage.setScene(scene);
      stage.show();
    }
  }

}