/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xds
 */
public class Venta {
    private int idVenta;
    private int codigoP;
    private String Nombre;
    private String Marca;
    private float precio;
    private int cantidad;
    private String usuario;
    private Date fecha;

    public Venta(int idVenta, int codigoP, String Nombre, String Marca, float precio, int cantidad, String usuario, Date fecha) {
        this.idVenta = idVenta;
        this.codigoP = codigoP;
        this.Nombre = Nombre;
        this.Marca = Marca;
        this.precio = precio;
        this.cantidad = cantidad;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getCodigoP() {
        return codigoP;
    }

    public void setCodigoP(int codigoP) {
        this.codigoP = codigoP;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int guardarVenta(Connection connection){
        try {
            PreparedStatement instruction = connection.prepareCall("INSERT INTO ventas ("
                    + "idVenta, "
                    + "codigoProducto, "
                    + "nombre, "
                    + "marca, "
                    + "precio, "
                    + "cantidad) "
                    + "vendedor) "
                    + "fecha) "
                    + "Values(?,?,?,?,?,?,?,?)");
            instruction.setInt(1, idVenta);
            instruction.setInt(2, codigoP);
            instruction.setString(3, Nombre);
            instruction.setString(4, Marca);
            instruction.setFloat(5, precio);
            instruction.setInt(6, cantidad);
            instruction.setString(7, usuario);
            instruction.setDate(8, fecha);
            return instruction.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }    
    
}
