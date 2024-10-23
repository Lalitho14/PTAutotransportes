//Autor: Jesus Eduardo Hernandez Bravo
//Git: https://github.com/Lalitho14

package Main;
import GUI.DBSetup;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
  private Connection connection;//Conector a base de datos
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
      JOptionPane.showMessageDialog(null, "Conexion exitosa");
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error al conectarse a la base: "+e.getMessage());
      return  null;
    }
    return connection;
  }
}
