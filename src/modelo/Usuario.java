/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author xds
 */
public class Usuario {
    private int idUsuario;
    private String Nombre;
    private String Apellido;
    private String Rol;
    private String Contrasena;

    public Usuario(int idUsuario, String Nombre, String Apellido, String Rol, String Contrasena) {
        this.idUsuario = idUsuario;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Rol = Rol;
        this.Contrasena = Contrasena;
    }

    public Usuario(String Nombre, String Contrasena) {
        this.Nombre = Nombre;
        this.Contrasena = Contrasena;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }
    
    public void iniciarSesion(Connection connection){
        try{
            PreparedStatement instruction = connection.prepareCall("SELECT * FROM usuarios WHERE "
                    + "nombre=? and "
                    + "contrasena=?");
            instruction.setString(1, Nombre);
            instruction.setString(2, Contrasena);
            ResultSet rs = instruction.executeQuery();
            
            if(rs.next()){
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Inicio de seiosn correcta");
            mensaje.setContentText("Has iniciado sesion como: "+Nombre);
            mensaje.setHeaderText("Resultado:");
            mensaje.show();

            }else{
                JOptionPane.showMessageDialog(null, "Incorrect");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }
    
    public static void llenarTablaUsuario(Connection connection, ObservableList<Usuario> lista){
        try {
            Statement instruction = connection.createStatement();
            ResultSet resultado = instruction.executeQuery("select * from usuarios");
            while(resultado.next()){
                lista.add(new Usuario(
                        resultado.getInt("idUser"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getString("rol"),
                        resultado.getString("contrasena")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public int guardarUsuario(Connection connection){
        try {
            PreparedStatement instruction = connection.prepareCall("INSERT INTO usuarios ("
                    + "idUser, "
                    + "nombre, "
                    + "apellido, "
                    + "rol, "
                    + "contrasena) "
                    + "Values(?,?,?,?,?)");
            instruction.setInt(1, idUsuario);
            instruction.setString(2, Nombre);
            instruction.setString(3, Apellido);
            instruction.setString(4, Rol);
            instruction.setString(5, Contrasena);
            return instruction.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int actualizarUsuario(Connection connection){
        try{
            PreparedStatement instruction = connection.prepareCall("UPDATE usuarios SET "
                    + "idUser =?, "
                    + "nombre =?, "
                    + "apellido =?, "
                    + "rol =?, "
                    + "contrasena =? "
                    + "WHERE idUser = ?");
                    
            instruction.setInt(1, idUsuario);
            instruction.setString(2, Nombre);
            instruction.setString(3, Apellido);
            instruction.setString(4, Rol);
            instruction.setString(5, Contrasena);
            instruction.setInt(6, idUsuario);
            return instruction.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int eliminarUsuario(Connection connection){
        try{
            PreparedStatement instruction = connection.prepareCall("DELETE FROM usuarios WHERE idUser = ?");
            instruction.setInt(1, idUsuario);
            return instruction.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
}
  
