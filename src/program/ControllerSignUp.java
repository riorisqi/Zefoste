package program;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RIO RISQI
 */
public class ControllerSignUp implements Initializable {

    @FXML
    private TextField usernameDaftar;
    
    @FXML
    private TextField emailDaftar;
    
    @FXML
    private PasswordField passDaftar;
    
    @FXML
    private PasswordField passAgainDaftar;
    
    @FXML
    private void handleButtonActionDaftar(ActionEvent event) throws IOException{
        //Pembuatan objek class daftar
        Daftar reg = new Daftar();
        
        XStream xstream = new XStream(new StaxDriver());
        
        //Set nilai class daftar
        reg.setUsernameA(usernameDaftar.getText());
        reg.setEmailA(emailDaftar.getText());
        reg.setPassA(passDaftar.getText());
        reg.setPassAgA(passAgainDaftar.getText());
        
        //Proses simpan data daftar ke xml
        String sxml = xstream.toXML(reg);
        
        if ("".equals(usernameDaftar.getText()) || "".equals(emailDaftar.getText()) ||
                "".equals(passDaftar.getText()) || "".equals(passAgainDaftar.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Perhatian!");
            alert.setContentText("Form tidak boleh ada yang kosong");
            alert.showAndWait();
            
        } else if (passDaftar.getText().equals(passAgainDaftar.getText()) == false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Perhatian!");
            alert.setContentText("Password tidak sama, pastikan anda sudah mengisi"
                    + " kedua kolom password dengan sama");
            alert.showAndWait();
            
        } else {
            FileOutputStream file = null;
            try{
                //membuat nama file dan folder tempat menyimpan
                file = new FileOutputStream("dataDaftar.xml");
                
                /* mengubah karakter penyusun string xml sebagai
                // bytes (berbentuk nomor-nomor kode ASCII
                */
                byte[] bytes = sxml.getBytes("UTF-8");
                
                //menyimapn file dari bytes
                file.write(bytes);
            }
            catch (IOException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("perhatian");
                alert.setHeaderText("Terjadi kesalahan");
                alert.setContentText("Silahkan periksa kembali data yang diisi");
                alert.showAndWait();
            }
            finally{
                if (file!= null){
                    try{
                        file.close();
                    }
                    catch (IOException e){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Terjadi Kesalahan");
                        alert.setContentText("" + e.getMessage());
                        alert.showAndWait();
                    }
                }
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Data Berhasil Disimpan");
            alert.setContentText("Silahkan untuk Login");
            alert.showAndWait();
            
            Parent loginFormParent = FXMLLoader.load(getClass().getResource("SceneLoginForm.fxml"));
            Scene loginFormScreen = new Scene(loginFormParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginFormScreen);
            window.show();
        }
    }
    
    @FXML
    private void handleButtonActionBack(ActionEvent event) throws IOException{
            Parent loginFormParent = FXMLLoader.load(getClass().getResource("SceneAwal.fxml"));
            Scene loginFormScreen = new Scene(loginFormParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginFormScreen);
            window.show();
    }
    
    
    @FXML
    private void handleButtonActionLogin(ActionEvent event) throws IOException{
            Parent loginFormParent = FXMLLoader.load(getClass().getResource("SceneLoginForm.fxml"));
            Scene loginFormScreen = new Scene(loginFormParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginFormScreen);
            window.show();
    }
    
    @FXML
    private void handleButtonActionBatal(ActionEvent event) throws IOException{
            Boolean batal = false;
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Batal");
            alert.setHeaderText("Yakin Ingin Membatalkan?");
            alert.setContentText("Data yang sudah anda isi tidak akan tersimpan");
            
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                Parent loginFormParent = FXMLLoader.load(getClass().getResource("SceneLoginForm.fxml"));
                Scene loginFormScreen = new Scene(loginFormParent);
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(loginFormScreen);
                window.show();
            } else {
                batal = false;
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
