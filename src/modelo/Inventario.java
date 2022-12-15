/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
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
public class Inventario {
    private int IdI;
    private int codigoP;
    private String nombre;
    private String Marca;
    private float precioC;
    private float precioV;
    private int stock;


    public Inventario(int IdI, int codigoP, String Marca, float precioC, float precioV, int stock) {
       this.IdI = IdI; 
        this.codigoP = codigoP;
        this.Marca = Marca;
        this.precioC = precioC;
        this.precioV = precioV;
        this.stock = stock;
    }
    
    

    public int getIdI() {
        return IdI;
    }

    public void setIdI(int IdI) {
        this.IdI = IdI;
    }
    
    

    public int getCodigoP() {
        return codigoP;
    }

    public void setCodigoP(int codigoP) {
        this.codigoP = codigoP;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public float getPrecioC() {
        return precioC;
    }

    public void setPrecioC(float precioC) {
        this.precioC = precioC;
    }

    public float getPrecioV() {
        return precioV;
    }

    public void setPrecioV(float precioV) {
        this.precioV = precioV;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
     public static void llenarTablaInventario(Connection connection, ObservableList<Inventario> lista){
        try {
            Statement instruction = connection.createStatement();
            ResultSet resultado = instruction.executeQuery("SELECT * FROM vwinventario");
            while(resultado.next()){
                lista.add(new Inventario(
                        resultado.getInt("idInventario"),
                        resultado.getInt("codigoProducto"),
                        resultado.getString("marca"),
                        resultado.getFloat("precioCompra"),
                        resultado.getFloat("precioVenta"),
                        resultado.getInt("stock")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
}
