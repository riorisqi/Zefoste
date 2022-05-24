package program;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RIO RISQI
 */
public class ControllerDaftarProdukEdit implements Initializable {

    @FXML
    private DatePicker newTgl;
    String tglSelesai;
    @FXML
    private TextArea newDesc;
    String desc;

    ModelData modelData;
    
    ObservableList<ModelData> data = FXCollections.observableArrayList();
    
    int index;
    
    public void setIndex(int index)
    {
        this.index = index;
    }
    
    @FXML
    private void editApply(ActionEvent event)
    {   
        data.set(index, modelData);
        tglSelesai = newTgl.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        desc = newDesc.getText();

        editData(modelData);
        
        saveEdit(data);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Data telah diubah");
        alert.showAndWait();
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void editData(ModelData modelData)
    {
        this.modelData = modelData;
        
        modelData.setTglMulai(tglSelesai);
        modelData.setDesc(desc);
    }
    
    private void saveEdit(ObservableList<ModelData> data)
    {
        XStream xstream = new XStream(new StaxDriver());
        ModelDataWrapper dataWarp = new ModelDataWrapper();
        dataWarp.setDatas(data);
        String xml = xstream.toXML(dataWarp);
        
        FileOutputStream f = null;
        try{
            f = new FileOutputStream("Data.xml");
            byte[] bytes = xml.getBytes("UTF-8");
            f.write(bytes);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally{
            if(f != null){
                try{
                    f.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        XStream xstream = new XStream(new StaxDriver());
        FileInputStream f =null;
        
        try {
            f = new FileInputStream("Data.xml");
            
            int isi;
            char c;
            String xml = "";
            
            while((isi = f.read() )!=-1){
                c = (char) isi;
                xml = xml + c;
            }
            
           ModelDataWrapper dataWrap = new ModelDataWrapper();
           dataWrap = (ModelDataWrapper) xstream.fromXML(xml);
           
           data.clear();
           data.addAll(dataWrap.getDatas());
        }
        
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        finally{
            if(f!=null){
                try{
                    f.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }    
    
}
