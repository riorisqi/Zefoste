package program;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RIO RISQI
 */
public class ControllerAwal implements Initializable {

    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{
        Parent loginFormParent = FXMLLoader.load(getClass().getResource("SceneLoginForm.fxml"));
        Scene loginFormScreen = new Scene(loginFormParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginFormScreen);
        window.show();
    }
    
    @FXML
    private void handleButtonActionGo(ActionEvent event) throws IOException{
        Parent loginFormParent = FXMLLoader.load(getClass().getResource("SceneHomeUser.fxml"));
        Scene loginFormScreen = new Scene(loginFormParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginFormScreen);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
