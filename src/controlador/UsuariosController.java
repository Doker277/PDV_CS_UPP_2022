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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.ConexionBD;
import modelo.Producto;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author xds
 */
public class UsuariosController implements Initializable {

    @FXML
    private Button btnInv;
    @FXML
    private Button btnProd;
    @FXML
    private Button btnUsu;
    @FXML
    private Button btnVent;
    @FXML
    private TableView<Usuario> tbUsuarios;
    @FXML
    private TableColumn<Usuario, Number> colId;
    @FXML
    private TableColumn<Usuario, String> colNombre;
    @FXML
    private TableColumn<Usuario, String> colApellido;
    @FXML
    private TableColumn<Usuario, String> colRol;
    @FXML
    private TableColumn<Usuario, String> colContrasena;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField txtIdUsuario;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtRol;
    @FXML
    private TextField txtApellido;
    @FXML
    private PasswordField txtContrasena;
    
    private ObservableList<Usuario> listaUsuario;
    //Conexion Base de Datos
    private ConexionBD conexion;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button btnAgregar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new ConexionBD();
        conexion.establecerConexion();
        
        listaUsuario = FXCollections.observableArrayList();
        Usuario.llenarTablaUsuario(conexion.getConnection(), listaUsuario);
        tbUsuarios.setItems(listaUsuario);
        
        colId.setCellValueFactory(new PropertyValueFactory<Usuario,Number>("idUsuario"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Usuario,String>("apellido"));
        colRol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("rol"));
        colContrasena.setCellValueFactory(new PropertyValueFactory<Usuario,String>("contrasena"));
        gestionarEventos();
        conexion.cerrarConexion();
    }    
    
    public void gestionarEventos(){
        tbUsuarios.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Usuario>() {
                    @Override
                    public void changed(ObservableValue<? extends Usuario> observable, Usuario oldValue, Usuario newValue) {
                        if(newValue!=null){
                        txtIdUsuario.setText(String.valueOf(newValue.getIdUsuario()));
                        txtNombre.setText(newValue.getNombre());
                        txtRol.setText(newValue.getRol());
                        txtApellido.setText(newValue.getApellido());
                        txtContrasena.setText(newValue.getContrasena());
                        
                        btnActualizar.setDisable(false);
                        btnEliminar.setDisable(false);
                        }
                    }
                });
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
    public void guardarUsuarios(){
        Usuario u = new Usuario(Integer.valueOf(txtIdUsuario.getText()),
                txtNombre.getText(),
                txtApellido.getText(),
                txtRol.getText(),
                txtContrasena.getText());
        conexion.establecerConexion();
        int resultado = u.guardarUsuario(conexion.getConnection());
        conexion.cerrarConexion();
        
        if(resultado == 1){
            listaUsuario.add(u);
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Registro agregado");
            mensaje.setContentText("El registro ha sido agregado correctamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
        }
    
    }   
    @FXML
    public void actualizarUsuarios(){
        Usuario u = new Usuario(Integer.valueOf(txtIdUsuario.getText()),
                txtNombre.getText(),
                txtApellido.getText(),
                txtRol.getText(),
                txtContrasena.getText());
        conexion.establecerConexion();
        int resultado = u.actualizarUsuario(conexion.getConnection());
        conexion.cerrarConexion();
        
        if(resultado == 1){
            listaUsuario.set(tbUsuarios.getSelectionModel().getSelectedIndex(),u);
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Registro actualizado");
            mensaje.setContentText("El registro ha sido actualizado correctamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
        }
    }
      @FXML
    private void eliminarUsuarios(){
        conexion.establecerConexion();
        int resultado = tbUsuarios.getSelectionModel().getSelectedItem().eliminarUsuario(conexion.getConnection());
        conexion.cerrarConexion();
        if(resultado == 1){
            listaUsuario.remove(tbUsuarios.getSelectionModel().getSelectedIndex());
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Registro eliminado");
            mensaje.setContentText("El registro ha sido eliminado correctamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
        }
    }
    
}
