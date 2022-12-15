package controlador;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Usuario 1
 */
public class main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
           try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/vista/VistaLogin.fxml"));
            Pane ventana = (Pane) loader.load();

            // Show the scene containing the root layout.
            primaryStage.setTitle("Punto de venta"); 
            Scene scene = new Scene(ventana);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
