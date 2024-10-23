//Autor: Jesus Eduardo Hernandez Bravo
//Git: https://github.com/Lalitho14/PTAutotransportes/

package Controladores;

import Implementacion.Flete;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VistaReporte {
  @FXML
  private Label cliente_txt;
  @FXML
  private Label costo_txt;
  @FXML
  private Label descuento_txt;
  @FXML
  private Label destino_txt;
  @FXML
  private Label distancia_txt;
  @FXML
  private Label fecha_txt;
  @FXML
  private Label origen_txt;
  @FXML
  private Label pagado_txt;
  @FXML
  private Label peso_txt;
  @FXML
  private Label redondo_txt;
  @FXML
  private Label titulo_txt;
  @FXML
  private Label total_txt;
  @FXML
  private Label volumen_txt;

  public void SetValores(Flete flete) {
    titulo_txt.setText("REPORTE FLETE : "+flete.getIdFlete());
    cliente_txt.setText("Cliente : "+flete.getCliente().getNombre_raz_soc());
    origen_txt.setText("Origen : "+flete.getOrigen());
    destino_txt.setText("Destino : "+flete.getDestino());
    distancia_txt.setText("Distancia : "+flete.getDistancia());
    volumen_txt.setText("Volumen : "+flete.getVolumen());
    peso_txt.setText("Peso (kg) : "+flete.getPeso());
    redondo_txt.setText("Redondo : "+ (flete.isRedondo()?"Si":"No"));
    fecha_txt.setText("Fecha : "+flete.getFecha());
    costo_txt.setText("Costo : $ "+flete.getCosto());
    descuento_txt.setText("Descuento : $ "+flete.getDescuento());
    total_txt.setText("Total : $ "+flete.getTotal());
    pagado_txt.setText("Estado : "+(flete.getPago().equals("Pagado")?"Pagado":"Pendiente"));
  }

}
