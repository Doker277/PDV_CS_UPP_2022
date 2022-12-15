/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author xds
 */
public class Producto {
    
    private int codigoProducto;
    private String Nombre;
    private String Marca;
    private int Stock;
    private float Precio;

    public Producto(int codigoProducto, String Nombre, String Marca, int Stock, float Precio) {
        this.codigoProducto = codigoProducto;
        this.Nombre = Nombre;
        this.Marca = Marca;
        this.Stock = Stock;
        this.Precio = Precio;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }
    
    public static void llenarTablaProducto(Connection connection, ObservableList<Producto> lista){
        try {
            Statement instruction = connection.createStatement();
            ResultSet resultado = instruction.executeQuery("select * from productos");
            while(resultado.next()){
                lista.add(new Producto(
                        resultado.getInt("codigoProducto"),
                        resultado.getString("Nombre"),
                        resultado.getString("Marca"),
                        resultado.getInt("Stock"),
                        resultado.getFloat("Precio")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    public int guardarProducto(Connection connection){
        try {
            PreparedStatement instruction = connection.prepareCall("INSERT INTO productos ("
                    + "codigoProducto, "
                    + "nombre, "
                    + "marca, "
                    + "precio, "
                    + "stock) "
                    + "Values(?,?,?,?,?)");
            instruction.setInt(1, codigoProducto);
            instruction.setString(2, Nombre);
            instruction.setString(3, Marca);
            instruction.setFloat(4, Precio);
            instruction.setInt(5, Stock);
            return instruction.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int actualizarProducto(Connection connection){
        try{
            PreparedStatement instruction = connection.prepareCall("UPDATE productos SET "
                    + "codigoProducto =?, "
                    + "nombre =?, "
                    + "marca=?, "
                    + "precio =?, "
                    + "stock=? "
                    + "WHERE codigoProducto = ?");
                    
            instruction.setInt(1, codigoProducto);
            instruction.setString(2, Nombre);
            instruction.setString(3, Marca);
            instruction.setInt(4, Stock);
            instruction.setFloat(5, Precio);
            instruction.setInt(6, codigoProducto);
            return instruction.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int eliminarProducto(Connection connection){
        try{
            PreparedStatement instruction = connection.prepareCall("DELETE FROM productos WHERE codigoProducto = ?");
            instruction.setInt(1, codigoProducto);
            return instruction.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public  void Buscar(Connection connection, ObservableList<Producto> lista){
        try {
            Statement instruction = connection.createStatement();
            ResultSet resultado = instruction.executeQuery("select * from productos where codigoProducto =" + codigoProducto);
            while(resultado.next()){
                lista.add(new Producto(
                        resultado.getInt("codigoProducto "),
                        resultado.getString("Nombre"),
                        resultado.getString("Marca"),
                        resultado.getInt("Stock"),
                        resultado.getFloat("Precio")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}

/*INSERT INTO ventas(codigoProducto, nombre, marca, precio, cantidad, vendedor)
SELECT productos.codigoProducto, productos.nombre, productos.marca, productos.precio, productos.stock, usuarios.nombre
FROM productos INNER JOIN usuarios*/