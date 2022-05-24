package program;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileOutputStream;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RIO RISQI
 */
public class ControllerLihatProdukEdit implements Initializable {

    @FXML
    private TextArea ulasDesc;
    
    @FXML
    private void handleButtonActionUnggah(ActionEvent event) throws IOException{
        //Pembuatan objek class daftar
        ulasanUser ul = new ulasanUser();
        
        XStream xstream = new XStream(new StaxDriver());
        
        ul.setUlasanUser1(ulasDesc.getText());
        
        String sxml = xstream.toXML(ul);
        if ("".equals(ulasDesc.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Perhatian!");
            alert.setContentText("Isian tidak boleh kosong, silahkan diisi");
            alert.showAndWait();
        } else {
            FileOutputStream file = null;
            try{
                //membuat nama file dan folder tempat menyimpan
                file = new FileOutputStream("ulasProdukUser.xml");
                
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
                alert.setContentText("Silahkan ulangi kembali");
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
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Ulasan Berhasil Diunggah");
        alert.setContentText("Terimakasih atas ulasannya");
        alert.showAndWait();

        Parent loginFormParent = FXMLLoader.load(getClass().getResource("SceneLihatProdukEdit.fxml"));
        Scene loginFormScreen = new Scene(loginFormParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginFormScreen);
        window.show();
    }
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
