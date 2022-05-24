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
public class ControllerLihatProdukDetail implements Initializable {

    @FXML
    private Label namaL;
    @FXML
    private Label hargaL;
    @FXML
    private Label kategoriL;
    @FXML
    private Label tglL;
    @FXML
    private TextArea descL;
    
    //Stage dialogStage;
    ModelData dataModel2;

    ObservableList<ModelData> data1 = FXCollections.observableArrayList();

    @FXML
    private void closeDetail(ActionEvent event)
    {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void setModelData(ModelData modelData1) {
        this.dataModel2 = modelData1;
        
        namaL.setText(modelData1.getNama());
        hargaL.setText("Rp" + Integer.toString(modelData1.getHarga()));
        kategoriL.setText(modelData1.getKategori());
        tglL.setText(modelData1.getTglMulai());
        descL.setText(modelData1.getDesc());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
