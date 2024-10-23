//Autor: Jesus Eduardo Hernandez Bravo
//Git: https://github.com/Lalitho14/PTAutotransportes/
package Implementacion;

public class Cliente {
  private int id;
  private String rfc;
  private String nombre_raz_soc;
  private String direccion;

  public Cliente(){}

  public Cliente(int id, String rfc, String nombre_raz_soc, String direccion) {
    this.id = id;
    this.rfc = rfc;
    this.nombre_raz_soc = nombre_raz_soc;
    this.direccion = direccion;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getRfc() {
    return rfc;
  }

  public void setRfc(String rfc) {
    this.rfc = rfc;
  }

  public String getNombre_raz_soc() {
    return nombre_raz_soc;
  }

  public void setNombre_raz_soc(String nombre_raz_soc) {
    this.nombre_raz_soc = nombre_raz_soc;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  @Override
  public String toString() {
    return this.nombre_raz_soc;
  }
}
