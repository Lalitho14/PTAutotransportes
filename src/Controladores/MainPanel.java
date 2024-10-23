//Autor: Jesus Eduardo Hernandez Bravo
//Git: https://github.com/Lalitho14/PTAutotransportes/

package Controladores;

import Consultas.ConsultasJ;
import Implementacion.Ciudad;
import Implementacion.Cliente;
import Implementacion.Flete;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class MainPanel {

  @FXML
  private ChoiceBox<Cliente> cliente_Opcion;

  @FXML
  private ChoiceBox<Ciudad> destino_Opcion;

  @FXML
  private TextField direccion_txt;

  @FXML
  private TextField costo_txt;

  @FXML
  private TextField distancia_txt;

  @FXML
  private ChoiceBox<Object> listaFiltros;

  @FXML
  private TextField nom_raz_soc_txt;

  @FXML
  private ChoiceBox<Ciudad> origen_Opcion;

  @FXML
  private ChoiceBox<String> pago_opcion;

  @FXML
  private TextField peso_txt;

  @FXML
  private RadioButton redondo_check;

  @FXML
  private TextField rfc_txt;

  @FXML
  private TableView<Flete> table_data;

  @FXML
  private TextField volumen_txt;

  @FXML
  private CheckBox clientes_checkbox;

  @FXML
  private CheckBox destino_checkbox;

  @FXML
  private CheckBox origen_checkbox;

  @FXML
  private CheckBox todo_checkbox;

  private Connection conexion;
  private ConsultasJ consultasJ;

  public void SetConnection(Connection conexion) {
    this.conexion = conexion;
    consultasJ = new ConsultasJ();
    MostarTodo();
    SetupPanelFlete();
  }

  public void MostarTodo(){
    ArrayList<Flete> fletes = consultasJ.MostrarFletes(this.conexion);
    CrearTablaFletes(fletes);
  }

  private void SetupPanelFlete(){
    pago_opcion.getItems().add("Pagado");
    pago_opcion.getItems().add("Pendiente");

    ArrayList<Ciudad> ciudades = consultasJ.MostrarCiudad(this.conexion);
    origen_Opcion.getItems().addAll(ciudades);
    destino_Opcion.getItems().addAll(ciudades);

    ArrayList<Cliente> clientes = consultasJ.MostrarClientes(this.conexion);
    cliente_Opcion.getItems().addAll(clientes);
  }

  @FXML
  void FiltrarClientes(ActionEvent event) {
    if(clientes_checkbox.selectedProperty().getValue()){
      origen_checkbox.setSelected(false);
      todo_checkbox.setSelected(false);
      destino_checkbox.setSelected(false);
      listaFiltros.getItems().clear();
      listaFiltros.setDisable(false);

      ArrayList<Cliente> c = consultasJ.MostrarClientes(conexion);
      listaFiltros.getItems().addAll(c);

      listaFiltros.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if(newValue != null){
          FiltrarPor();
        }
      });
    }
    else{
      listaFiltros.getItems().clear();
      listaFiltros.setDisable(true);
      table_data.getItems().clear();
      table_data.getColumns().clear();
      table_data.refresh();
      MostarTodo();
    }
  }

  @FXML
  void FiltrarDestino(ActionEvent event) {
    if(destino_checkbox.selectedProperty().getValue()){
      clientes_checkbox.setSelected(false);
      origen_checkbox.setSelected(false);
      todo_checkbox.setSelected(false);
      listaFiltros.getItems().clear();
      listaFiltros.setDisable(false);

      ArrayList<Ciudad> c = consultasJ.MostrarCiudad(conexion);
      listaFiltros.getItems().addAll(c);

      listaFiltros.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if(newValue != null){
          FiltrarPor();
        }
      });

    }else {
      listaFiltros.getItems().clear();
      listaFiltros.setDisable(true);
      table_data.getItems().clear();
      table_data.getColumns().clear();
      table_data.refresh();
      MostarTodo();
    }
  }

  @FXML
  void FiltrarOrigen(ActionEvent event) {
    if(origen_checkbox.selectedProperty().getValue()){
      clientes_checkbox.setSelected(false);
      todo_checkbox.setSelected(false);
      destino_checkbox.setSelected(false);
      listaFiltros.getItems().clear();
      listaFiltros.setDisable(false);

      ArrayList<Ciudad> c = consultasJ.MostrarCiudad(conexion);
      listaFiltros.getItems().addAll(c);

      listaFiltros.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if(newValue != null){
          FiltrarPor();
        }
      });

    }else {
      listaFiltros.getItems().clear();
      listaFiltros.setDisable(true);
      table_data.getItems().clear();
      table_data.getColumns().clear();
      table_data.refresh();
      MostarTodo();
    }
  }

  void FiltrarPor() {
    table_data.getItems().clear();
    table_data.getColumns().clear();
    table_data.refresh();

    if(clientes_checkbox.selectedProperty().getValue()){
      Cliente c = (Cliente)listaFiltros.getValue();
      ArrayList<Flete> fletes = consultasJ.MostrarFletesCliente(conexion, c);
      CrearTablaFletes(fletes);
    }

    if(origen_checkbox.selectedProperty().getValue()){
      Ciudad ciudad = (Ciudad)listaFiltros.getValue();
      ArrayList<Flete> fletes = consultasJ.MostrarFletesOrigen(conexion, ciudad);
      CrearTablaFletes(fletes);
    }

    if(destino_checkbox.selectedProperty().getValue()){
      Ciudad ciudad = (Ciudad)listaFiltros.getValue();
      ArrayList<Flete> fletes= consultasJ.MostrarFletesDestino(conexion, ciudad);
      CrearTablaFletes(fletes);
    }
  }

  private void CrearTablaFletes(ArrayList<Flete> fletes) {
    TableColumn<Flete, Integer> idCol = new TableColumn<>("ID");
    idCol.setCellValueFactory(new PropertyValueFactory<>("idFlete"));

    TableColumn<Flete, Double> costoCol = new TableColumn<>("Total");
    costoCol.setCellValueFactory(new PropertyValueFactory<>("total"));

    TableColumn<Flete, String> origenCol = new TableColumn<>("Origen");
    origenCol.setCellValueFactory(cd-> new SimpleStringProperty(cd.getValue().getOrigen().getNombre()));

    TableColumn<Flete, String> destinoCol = new TableColumn<>("Destino");
    destinoCol.setCellValueFactory(cd-> new SimpleStringProperty(cd.getValue().getDestino().getNombre()));

    TableColumn<Flete, String> pagoCol = new TableColumn<>("Pago");
    pagoCol.setCellValueFactory(new PropertyValueFactory<>("pago"));

    TableColumn<Flete, String> fechaCol = new TableColumn<>("Fecha");
    fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));

    table_data.getColumns().add(idCol);
    table_data.getColumns().add(costoCol);
    table_data.getColumns().add(origenCol);
    table_data.getColumns().add(destinoCol);
    table_data.getColumns().add(pagoCol);
    table_data.getColumns().add(fechaCol);
    table_data.getItems().addAll(fletes);

    table_data.setRowFactory(fleteTableView -> {
      TableRow<Flete> row = new TableRow<>();
      row.setOnMouseClicked(mouseEvent -> {
        if(!row.isEmpty()){
          Flete f = row.getItem();
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/VistaReporte.fxml"));
          try {
            Parent root = loader.load();
            VistaReporte vistaReporte = loader.getController();
            vistaReporte.SetValores(f);
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setWidth(1500);
            stage.setHeight(600);
            stage.setResizable(true);
            stage.setScene(scene);
            stage.show();
          } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error : "+e.getMessage(),"Autotransportes",JOptionPane.ERROR_MESSAGE);
          }
        }
      });
      return row;
    });
  }

  @FXML
  void FiltrarTodos(ActionEvent event) {
    table_data.getItems().clear();
    table_data.getColumns().clear();
    table_data.refresh();
    origen_checkbox.setSelected(false);
    destino_checkbox.setSelected(false);
    clientes_checkbox.setSelected(false);
    listaFiltros.getItems().clear();
    listaFiltros.setDisable(true);
    MostarTodo();
  }

  @FXML
  void IngresarNuevoCliente(ActionEvent event) {
    Cliente cliente = new Cliente();
    cliente.setRfc(rfc_txt.getText());
    cliente.setNombre_raz_soc(nom_raz_soc_txt.getText());
    cliente.setDireccion(direccion_txt.getText());

    consultasJ.InsertarNuevoCliente(conexion, cliente);
    cliente_Opcion.getItems().clear();
    ArrayList<Cliente> clientes = consultasJ.MostrarClientes(this.conexion);
    cliente_Opcion.getItems().addAll(clientes);

    rfc_txt.setText("");
    nom_raz_soc_txt.setText("");
    direccion_txt.setText("");
  }

  @FXML
  void IngresarNuevoFlete(ActionEvent event) {
    Flete flete =new Flete();
    try {
      flete.setRedondo(redondo_check.isSelected());
      flete.setVolumen(Double.parseDouble(volumen_txt.getText()));
      flete.setPeso(Double.parseDouble(peso_txt.getText()));
      flete.setDistancia(Double.parseDouble(distancia_txt.getText()));
      flete.setCosto(Double.parseDouble(costo_txt.getText()));
      flete.setOrigen(origen_Opcion.getValue());
      flete.setDestino(destino_Opcion.getValue());
      Cliente cliente = new Cliente();
      cliente.setId(cliente_Opcion.getValue().getId());
      flete.setCliente(cliente);
      flete.setPago(pago_opcion.getValue());
      flete.setTotalDesc();

      consultasJ.InsertarNuevoFlete(conexion, flete);
      redondo_check.setSelected(false);
      volumen_txt.setText("");
      peso_txt.setText("");
      distancia_txt.setText("");
      costo_txt.setText("");
      table_data.getItems().clear();
      table_data.getColumns().clear();
      table_data.refresh();
      MostarTodo();
    }
    catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Error : "+e.getMessage(),"Autotransportes",JOptionPane.ERROR_MESSAGE);
    }
  }

}