package program;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author RIO RISQI
 */
public class ControllerDaftarProdukDetail implements Initializable {

    @FXML
    private Label namaLabel;
    @FXML
    private Label hargaLabel;
    @FXML
    private Label kategoriLabel;
    @FXML
    private Label tglLabel;
    @FXML
    private TextArea descLabel;
    
    //Stage dialogStage;
    ModelData modelData;

    ObservableList<ModelData> data = FXCollections.observableArrayList();

    @FXML
    private void closeDetail(ActionEvent event)
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void setModelData(ModelData modelData) {
        this.modelData = modelData;
        
        namaLabel.setText(modelData.getNama());
        hargaLabel.setText("Rp" + Integer.toString(modelData.getHarga()));
        kategoriLabel.setText(modelData.getKategori());
        tglLabel.setText(modelData.getTglMulai());
        descLabel.setText(modelData.getDesc());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }
    
}
