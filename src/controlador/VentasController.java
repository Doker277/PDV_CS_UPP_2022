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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.ConexionBD;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author xds
 */
public class VentasController implements Initializable {

    @FXML
    private Button btnInv;
    @FXML
    private Button btnProd;
    @FXML
    private Button btnUsu;
    @FXML
    private Button btnVent;
    @FXML
    private Button btnAceptar;
    
    @FXML private TableView<Producto> tbProductos;
    @FXML private TableColumn<Producto, Number> colCodigoB;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, String> colMarca;
    @FXML private TableColumn<Producto, String> colPrecio;
    @FXML private TableColumn<Producto, String> colCantidad;
    
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnCancelar;
    
    //Colecciones
    private ObservableList<Producto> listaProducto;
    private ObservableList<Producto> filtroProductos;
    //Conexion Base de Datos
    private ConexionBD conexion;
    @FXML
    private TextField txtTotal;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField txtNombrefiltro;
    @FXML
    private Button btnDuplicar;
    @FXML
    private Button txtCobrar;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new ConexionBD();
        conexion.establecerConexion();
        
        listaProducto = FXCollections.observableArrayList();
        filtroProductos = FXCollections.observableArrayList();
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

    @FXML
    private void BuscarP(KeyEvent event) {
        
        String filtroNombre = this.txtNombrefiltro.getText();
        
        if(filtroNombre.isEmpty()){
            this.tbProductos.setItems(listaProducto);
        }else{
            this.filtroProductos.clear();
            
            for (Producto p:this.listaProducto) {
                if(p.getNombre().toLowerCase().contains(filtroNombre)){
                    this.filtroProductos.add(p);
                }
            }
            this.tbProductos.setItems(filtroProductos);
        }
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

   public void gestionarEventos(){
        tbProductos.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Producto>() {
                    @Override
                    public void changed(ObservableValue<? extends Producto> observable, Producto oldValue, Producto newValue) {
                        if(newValue!=null){
                        txtTotal.setText(String.valueOf(newValue.getStock()));
                       
                        btnAceptar.setDisable(true);
                        btnEliminar.setDisable(false);
                        btnDuplicar.setDisable(false);
                        }
                    }
                });
    }

    @FXML
    private void Cobrar(ActionEvent event) {
        
    }
}
