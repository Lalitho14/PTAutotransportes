//Autor: Jesus Eduardo Hernandez Bravo
//Git: https://github.com/Lalitho14/PTAutotransportes/

package Implementacion;

public class Flete {
  private int idFlete;
  private double costo;
  private double descuento;
  private double total;
  private double volumen;
  private double peso;
  private boolean redondo;
  private double distancia;
  private String fecha;
  private String pago;
  private Cliente cliente;
  private Ciudad origen;
  private Ciudad destino;


  public Flete(){}

  public Flete(int idFlete, double costo, boolean redondo, String fecha, String pago, Cliente cliente, Ciudad origen, Ciudad destino) {
    this.idFlete = idFlete;
    this.costo = costo;
    this.redondo = redondo;
    this.fecha = fecha;
    this.pago = pago;
    this.cliente = cliente;
    this.origen = origen;
    this.destino = destino;
  }

  public int getIdFlete() {
    return idFlete;
  }

  public void setIdFlete(int idFlete) {
    this.idFlete = idFlete;
  }

  public double getCosto() {
    return costo;
  }

  public void setCosto(double costo) {
    this.costo = costo;
  }

  public boolean isRedondo() {
    return redondo;
  }

  public void setRedondo(boolean redondo) {
    this.redondo = redondo;
  }

  public double getDistancia() {
    return distancia;
  }

  public void setDistancia(double distancia) {
    this.distancia = distancia;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public String getPago() {
    return pago;
  }

  public void setPago(String pago) {
    this.pago = pago;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Ciudad getOrigen() {
    return origen;
  }

  public void setOrigen(Ciudad origen) {
    this.origen = origen;
  }

  public Ciudad getDestino() {
    return destino;
  }

  public void setDestino(Ciudad destino) {
    this.destino = destino;
  }

  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public void setTotalDesc() {

    if(peso > 40){
      costo += costo*0.20;
    }

    if(redondo){
      descuento=costo*.10;
      total = costo - descuento;
    }
    else{
      total = costo;
    }
  }

  public double getDescuento() {
    return descuento;
  }

  public void setDescuento(double descuento) {
    this.descuento = descuento;
  }

  public double getVolumen() {
    return volumen;
  }

  public void setVolumen(double volumen) {
    this.volumen = volumen;
  }

  public double getPeso() {
    return peso;
  }

  public void setPeso(double peso) {
    this.peso = peso;
  }
}
