/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.ConexionBD;
import modelo.Inventario;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author xds
 */
public class InventarioController implements Initializable {

    @FXML
    private Button btnInv;
    @FXML
    private Button btnProd;
    @FXML
    private Button btnUsu;
    @FXML
    private Button btnVent;
    @FXML
    private TextField txtCodigo;
    @FXML
    private Button btnAceptar;
    @FXML
    private TableView<Inventario> tbInventario;
    @FXML
    private TableColumn<Inventario, Number> colCodigo;
    @FXML
    private TableColumn<Inventario, String> colMarca;
    @FXML
    private TableColumn<Inventario, Float> colPrecioC;
    @FXML
    private TableColumn<Inventario, String> colPrecioV;
    @FXML
    private TableColumn<Inventario, String> colStock;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnSalir;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button btnActualizar;
    @FXML
    private TextField txtPrecioC;
    
    private ObservableList<Inventario> listaInventario;
    private ConexionBD conexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new ConexionBD();
        conexion.establecerConexion();
        
        listaInventario = FXCollections.observableArrayList();
        Inventario.llenarTablaInventario(conexion.getConnection(), listaInventario);
        tbInventario.setItems(listaInventario);
        
        colCodigo.setCellValueFactory(new PropertyValueFactory<Inventario,Number>("codigoProducto"));
        colMarca.setCellValueFactory(new PropertyValueFactory<Inventario,String>("Marca"));
        colPrecioV.setCellValueFactory(new PropertyValueFactory<Inventario,String>("PrecioVenta"));
        colPrecioC.setCellValueFactory(new PropertyValueFactory<Inventario,Float>("precioCompra"));
        colStock.setCellValueFactory(new PropertyValueFactory<Inventario,String>("stock"));
    }    
    
    @FXML
    private void Venta(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vista/VistaVentas.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Inventario(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vista/vistaInventario.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Producto(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/vista/VistaProducto.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Usuario(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/vista/VistaUsuario.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ActualizarInventario(ActionEvent event) {
    }

    @FXML
    private void EliminarInventario(ActionEvent event) {
    }

    @FXML
    private void Salir(ActionEvent event) {
    }
}
