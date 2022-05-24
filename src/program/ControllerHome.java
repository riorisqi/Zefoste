package program;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RIO RISQI
 */
public class ControllerHome implements Initializable 
{

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    @FXML
    private void moveToTambahProduk(ActionEvent event) throws Exception
    {
        Parent sewaParent = FXMLLoader.load(getClass().getResource("SceneTambahProduk.fxml"));
        Scene sewaScene = new Scene(sewaParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Penambahan Data Produk");
        stage.setScene(sewaScene);
        stage.show();
    }

    @FXML
    private void moveToDaftarProduk(ActionEvent event) throws Exception
    {
        Parent pelangganParent = FXMLLoader.load(getClass().getResource("SceneDaftarProduk.fxml"));
        Scene pelangganScene = new Scene(pelangganParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Daftar Produk");
        stage.setScene(pelangganScene);
        stage.show();
    }
    
    @FXML
    private void moveToChart(ActionEvent event)
    {
        try{
            Parent chartParent = FXMLLoader.load(getClass().getResource("SceneChart.fxml"));
            Scene chartScene = new Scene(chartParent);

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Grafik Toko");
            stage.setScene(chartScene);
            stage.show();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Terjadi Kesalahan");
            alert.setContentText("Menu tidak dapat dibuka.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void exitAction(ActionEvent event)
    {
        System.exit(1);
    }
    
}
