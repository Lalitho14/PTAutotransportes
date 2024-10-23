//Autor: Jesus Eduardo Hernandez Bravo
//Git: https://github.com/Lalitho14/PTAutotransportes/

package Implementacion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
  private Connection connection = null;//Conector a base de datos
  private String usr;//Usuario a base de datos
  private String pwd;//Contraseña a base de datos
  private String db;//Base de datos a usar
  private String ip;//Direccion IP a base de datos
  private String port;//Puerto a base de datos

  //Constructor
  public ConexionBD(String usr, String pwd, String db, String ip, String port) {
    this.usr = usr;
    this.pwd = pwd;
    this.db = db;
    this.ip = ip;
    this.port = port;
  }

  public Connection Conectar(){
    String direccion = "jdbc:mysql://"+ip+":"+port+"/"+db;
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      this.connection = DriverManager.getConnection(direccion,usr,pwd);
      JOptionPane.showMessageDialog(null,"Conexion", "Conexion exitosa", JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error al conectarse a la base: "+e.getMessage(), "Conexion", JOptionPane.ERROR_MESSAGE);
      this.connection = null;
    }
    return connection;
  }
}
