package program;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
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
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RIO RISQI
 */
public class ControllerLoginForm implements Initializable {

    @FXML
    private TextField usernameLogin;
    
    @FXML
    private PasswordField passwordLogin;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{
        Parent loginFormParent = FXMLLoader.load(getClass().getResource("SceneAwal.fxml"));
        Scene loginFormScreen = new Scene(loginFormParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginFormScreen);
        window.show();
    }
    
    @FXML
    private void handleButtonActionDaftar(ActionEvent event) throws IOException{
        Parent loginFormParent = FXMLLoader.load(getClass().getResource("SceneSignUp.fxml"));
        Scene loginFormScreen = new Scene(loginFormParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginFormScreen);
        window.show();
    }
    
    @FXML
    private void LOGIN(ActionEvent event) throws IOException{
        Login login = new Login();
        Daftar reg = new Daftar();
        
        XStream xstream = new XStream(new StaxDriver());
        
        FileInputStream file = null;
        try{
            //nama file yang akan dibuka (termasuk folder bila perlu
            file = new FileInputStream("dataDaftar.xml");
            
            int isi;
            char c;
            String str = "";
            
            //isi file xml dikembalikan menjadi string
            while((isi = file.read()) != -1){
                c = (char) isi;
                str = str + c;
            }
            
            //string isi file dikembalikan
            reg = (Daftar) xstream.fromXML(str);
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
        finally{
            if (file != null){
                try{
                    file.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        System.out.println(reg);
        
        try{
            if ("".equals(usernameLogin.getText()) || "".equals(passwordLogin.getText())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Perhatian!");
                alert.setContentText("Silahkan isi username dan password anda");
                alert.showAndWait();
            } else if (reg.getUsernameA().equals(usernameLogin.getText()) == false){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Perhatian!");
                alert.setContentText("Username atau Password Salah");
                alert.showAndWait();
            } else if (reg.getPassA().equals(passwordLogin.getText()) == false){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Perhatian!");
                alert.setContentText("Username atau Password Salah");
                alert.showAndWait();
            } else {
                login.setUserLog(usernameLogin.getText());
                login.setPassLog(passwordLogin.getText());
                
                Parent loginFormParent = FXMLLoader.load(getClass().getResource("SceneHome.fxml"));
                Scene loginFormScreen = new Scene(loginFormParent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(loginFormScreen);
                window.show();
            }
        }
        catch(RuntimeException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Perhatian!");
            alert.setContentText("Username atau password tidak ditemukan");
            alert.showAndWait();
            
            Parent loginFormParent = FXMLLoader.load(getClass().getResource("SceneLoginForm.fxml"));
            Scene loginFormScreen = new Scene(loginFormParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginFormScreen);
            window.show();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
