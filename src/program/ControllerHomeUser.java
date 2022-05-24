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
public class ControllerHomeUser implements Initializable {

    @FXML
    private void moveToLihatProduk(ActionEvent event) throws Exception
    {
        Parent sewaParent = FXMLLoader.load(getClass().getResource("SceneLihatProduk.fxml"));
        Scene sewaScene = new Scene(sewaParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Beranda");
        stage.setScene(sewaScene);
        stage.show();
    }

    @FXML
    private void moveToDaftarKategori(ActionEvent event) throws Exception
    {
        
    }
    
    @FXML
    private void moveToSearch(ActionEvent event)
    {
        
    }
    
    @FXML
    private void exitAction(ActionEvent event)
    {
        System.exit(1);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
