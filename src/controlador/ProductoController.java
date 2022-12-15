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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.ConexionBD;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author xds
 */
public class ProductoController implements Initializable {

    //UI
    @FXML private Button btnUsu;
    @FXML private Button btnVent;
    @FXML private Button btnInv;
    @FXML private Button btnProd;
    
    //Tabla Producto
    @FXML private TableView<Producto> tbProductos;
    @FXML private TableColumn<Producto, Number> colCodigoB;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, String> colMarca;
    @FXML private TableColumn<Producto, String> colPrecio;
    @FXML private TableColumn<Producto, String> colCantidad;
    
    //Textos
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtMarca;
    @FXML private TextField txtPrecio;
    
    //Botones
    @FXML private Button btnEliminar;
    @FXML private Button btnAceptar;
    @FXML private Button btnActualizar;
    @FXML private Button btnRefrescar;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    //Colecciones
    private ObservableList<Producto> listaProducto;
    //Conexion Base de Datos
    private ConexionBD conexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new ConexionBD();
        conexion.establecerConexion();
        
        listaProducto = FXCollections.observableArrayList();
        Producto.llenarTablaProducto(conexion.getConnection(), listaProducto);
        tbProductos.setItems(listaProducto);
        
        colCodigoB.setCellValueFactory(new PropertyValueFactory<Producto,Number>("codigoProducto"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Producto,String>("Nombre"));
        colMarca.setCellValueFactory(new PropertyValueFactory<Producto,String>("Marca"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<Producto,String>("Stock"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<Producto,String>("Precio"));
        gestionarEventos();
        conexion.cerrarConexion();
    }    
    
    public void gestionarEventos(){
        tbProductos.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Producto>() {
                    @Override
                    public void changed(ObservableValue<? extends Producto> observable, Producto oldValue, Producto newValue) {
                        if(newValue!=null){
                        txtCodigo.setText(String.valueOf(newValue.getCodigoProducto()));
                        txtNombre.setText(newValue.getNombre());
                        txtMarca.setText(newValue.getMarca());
                        txtCantidad.setText(String.valueOf(newValue.getStock()));
                        txtPrecio.setText(String.valueOf(newValue.getPrecio()));
                        
                        btnAceptar.setDisable(true);
                        btnActualizar.setDisable(false);
                        btnEliminar.setDisable(false);
                        }
                    }
                });
    }
    
    @FXML
    public void guardarProductos(){
        Producto p = new Producto(Integer.valueOf(txtCodigo.getText()),
                txtNombre.getText(),
                txtMarca.getText(),
                Integer.valueOf(txtCantidad.getText()),
                Float.valueOf(txtPrecio.getText()));
        conexion.establecerConexion();
        int resultado = p.guardarProducto(conexion.getConnection());
        conexion.cerrarConexion();
        if(resultado == 1){
            listaProducto.add(p);
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Registro agregado");
            mensaje.setContentText("El registro ha sido agregado correctamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
        }
    
    }   
    @FXML
    public void actualizarProductos(){
        Producto p = new Producto(Integer.valueOf(txtCodigo.getText()),
                txtNombre.getText(),
                txtMarca.getText(),
                Integer.valueOf(txtCantidad.getText()),
                Float.valueOf(txtPrecio.getText()));
        conexion.establecerConexion();
        int resultado = p.actualizarProducto(conexion.getConnection());
        conexion.cerrarConexion();
        
        if(resultado == 1){
            listaProducto.set(tbProductos.getSelectionModel().getSelectedIndex(),p);
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Registro actualizado");
            mensaje.setContentText("El registro ha sido actualizado correctamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
        }
    }
      @FXML
    private void eliminarProductos(){
        conexion.establecerConexion();
        int resultado = tbProductos.getSelectionModel().getSelectedItem().eliminarProducto(conexion.getConnection());
        conexion.cerrarConexion();
        if(resultado == 1){
            listaProducto.remove(tbProductos.getSelectionModel().getSelectedIndex());
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Registro eliminado");
            mensaje.setContentText("El registro ha sido eliminado correctamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
        }
    }
    
    @FXML
    public void LimpiarTextos(){
        txtCodigo.setText(null);
        txtNombre.setText(null);
        txtMarca.setText(null);
        txtCantidad.setText(null);
        txtPrecio.setText(null);
        
        btnAceptar.setDisable(false);
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
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

    @FXML
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
    
    @FXML
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
    
}
