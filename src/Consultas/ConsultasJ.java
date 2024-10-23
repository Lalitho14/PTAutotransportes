//Autor: Jesus Eduardo Hernandez Bravo
//Git: https://github.com/Lalitho14/PTAutotransportes/

package Consultas;

import Implementacion.Ciudad;
import Implementacion.Cliente;
import Implementacion.Flete;
import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ConsultasJ {
  Statement st;

  public ConsultasJ() {}

  public ArrayList<Cliente> MostrarClientes(Connection connection){
    ArrayList<Cliente> clientes = new ArrayList<>();
    String query = "SELECT * FROM Cliente ORDER BY Cliente.nombre_raz_soc;";

    try {
      st = connection.createStatement();
      ResultSet res = st.executeQuery(query);
      while(res.next()){
        Cliente c = new Cliente(res.getInt("idCliente"), res.getString("rfc"),res.getString("nombre_raz_soc"), res.getString("direccion") );
        clientes.add(c);
      }
      return clientes;
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null,"Error al mostrar registros : "+e.getMessage(),"Autotransportes", JOptionPane.ERROR_MESSAGE);
      return null;
    }
  }
  public ArrayList<Ciudad> MostrarCiudad(Connection connection){
    ArrayList<Ciudad> ciudades = new ArrayList<>();
    String query = "SELECT * FROM Ciudad ORDER BY Ciudad.nombre;";

    try{
      st = connection.createStatement();
      ResultSet res = st.executeQuery(query);
      while(res.next()){
        Ciudad c = new Ciudad(res.getInt("idCiudad"), res.getString("nombre"));
        ciudades.add(c);
      }
      return ciudades;
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null,"Error al mostrar registros : "+e.getMessage(),"Autotransportes",JOptionPane.ERROR_MESSAGE);
      return null;
    }
  }

  public ArrayList<Flete> MostrarFletes(Connection connection){
    String query = "SELECT * FROM Flete;";
    ArrayList<Flete> fletes = new ArrayList<>();
    return getFletes(connection, fletes, query);
  }
  public ArrayList<Flete> MostrarFletesCliente(Connection connection, Cliente cliente){
    ArrayList<Flete> fletes = new ArrayList<>();
    String query = "SELECT * FROM Flete WHERE Flete.cliente =  "+cliente.getId()+";";
    return getFletes(connection,fletes, query);
  }
  public ArrayList<Flete> MostrarFletesOrigen(Connection connection, Ciudad ciudad){
    ArrayList<Flete> fletes = new ArrayList<>();
    String query = "SELECT * FROM Flete WHERE Flete.origen = "+ciudad.getIdCiudad()+";";

    return getFletes(connection, fletes, query);
  }
  public ArrayList<Flete> MostrarFletesDestino(Connection connection, Ciudad ciudad){
    ArrayList<Flete> fletes = new ArrayList<>();
    String query = "SELECT * FROM Flete WHERE Flete.destino = "+ciudad.getIdCiudad()+";";

    return getFletes(connection, fletes, query);
  }
  private ArrayList<Flete> getFletes(Connection connection, ArrayList<Flete> fletes, String query) {
    try {
      st = connection.createStatement();
      ResultSet res = st.executeQuery(query);
      while(res.next()){
        Flete f = new Flete();

        Cliente c = getCliente(res.getInt("cliente"), connection);
        f.setCliente(c);
        f.setIdFlete(res.getInt("idFlete"));
        f.setTotal(res.getDouble("total"));
        f.setDescuento(res.getDouble("descuento"));
        f.setCosto(res.getDouble("costo"));
        f.setPeso(res.getDouble("peso"));
        f.setDistancia(res.getDouble("distancia"));
        f.setRedondo(res.getBoolean("redondo"));
        f.setVolumen(res.getDouble("volumen"));

        Ciudad ciudad = getCiudad(res.getInt("origen"), connection);
        f.setOrigen(ciudad);
        ciudad = getCiudad(res.getInt("destino"), connection);
        f.setDestino(ciudad);
        f.setPago(res.getString("pago"));
        f.setFecha(res.getString("fecha"));
        fletes.add(f);
      }

      return fletes;
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error al mostrar registros : "+e.getMessage(),"Autotransportes - FletesQ",JOptionPane.ERROR_MESSAGE);
      return null;
    }
  }

  public Cliente getCliente(int id, Connection connection){
    try{
      st = connection.createStatement();
      ResultSet res = st.executeQuery("SELECT * FROM Cliente WHERE idCliente = "+id+";");
      res.next();
      return new Cliente(res.getInt("idCliente"), res.getString("rfc"), res.getString("nombre_raz_soc"), res.getString("direccion"));
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error al mostrar registros  : "+e.getMessage(),"Autotransportes - BD Cliente",JOptionPane.ERROR_MESSAGE);
      return null;
    }
  }

  public Ciudad getCiudad(int id, Connection connection){
    try{
      st = connection.createStatement();
      ResultSet res = st.executeQuery("SELECT * FROM Ciudad WHERE idCiudad = "+id+";");
      res.next();
      return new Ciudad(res.getInt("idCiudad"), res.getString("nombre"));
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error al mostrar registros  : "+e.getMessage(),"Autotransportes - BD Ciudad",JOptionPane.ERROR_MESSAGE);
      return null;
    }
  }

  public void InsertarNuevoCliente(Connection connection, Cliente cliente){
    String query = "INSERT INTO Cliente(rfc, nombre_raz_soc, direccion) VALUES(?,?,?)";
    try{
      CallableStatement cst = connection.prepareCall(query);
      cst.setString(1, cliente.getRfc());
      cst.setString(2, cliente.getNombre_raz_soc());
      cst.setString(3, cliente.getDireccion());
      cst.execute();
      JOptionPane.showMessageDialog(null, "Se registro cliente con exito.","Autotransportes - BD Cliente",JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error al insertar registros  : "+e.getMessage(),"Autotransportes - BD Cliente",JOptionPane.ERROR_MESSAGE);
    }
  }

  public void InsertarNuevoFlete(Connection connection, Flete flete){
    String query = "INSERT INTO Flete(costo, descuento, total, volumen, peso, redondo, distancia, pago, origen, destino , cliente) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    try{
      CallableStatement cst = connection.prepareCall(query);
      cst.setDouble(1, flete.getCosto());
      cst.setDouble(2, flete.getDescuento());
      cst.setDouble(3, flete.getTotal());
      cst.setDouble(4, flete.getVolumen());
      cst.setDouble(5, flete.getPeso());
      cst.setInt(6, flete.isRedondo() ? 1 : 0);
      cst.setDouble(7, flete.getDistancia());
      cst.setString(8, flete.getPago());
      cst.setInt(9, flete.getOrigen().getIdCiudad());
      cst.setInt(10, flete.getDestino().getIdCiudad());
      cst.setInt(11, flete.getCliente().getId());
      cst.execute();
      JOptionPane.showMessageDialog(null, "Se registro flete con exito.","Autotransportes - BD Flete",JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Error al insertar registros  : "+e.getMessage(),"Autotransportes - BD Cliente",JOptionPane.ERROR_MESSAGE);
    }
  }
}
