package program;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RIO RISQI
 */
public class ControllerTambahProduk implements Initializable 
{

    @FXML
    private TextField namaField;
    @FXML
    private TextField hargaField;
    
    @FXML
    private ChoiceBox kategoriChoice;
    
    @FXML
    private DatePicker mulaiDP;
    private String tglMulai;
    
    @FXML
    private TextArea descField;
    
    ObservableList<ModelData> data = FXCollections.observableArrayList();
    
    ModelCount modelCount;
    ObservableList<ModelCount> count = FXCollections.observableArrayList();
    
    @FXML
    private void moveToHome(ActionEvent event) throws Exception
    {
        Parent homeParent = FXMLLoader.load(getClass().getResource("SceneHome.fxml"));
        Scene homeScene = new Scene(homeParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(homeScene);
        stage.show();
    }

    @FXML
    private void setMulaiDP(ActionEvent event)
    {
        tglMulai = mulaiDP.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    
    @FXML
    private void saveDataAction(ActionEvent event) 
    {   
        data.add(new ModelData(namaField.getText(), Integer.parseInt(hargaField.getText()), 
                               (String) kategoriChoice.getValue(),
                               tglMulai, descField.getText()));
        countKategori();
        saveData(data);
        saveCount(count);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Data berhasil ditambahkan dan telah disimpan.");

        alert.showAndWait();

        try{
            Parent homeParent = FXMLLoader.load(getClass().getResource("SceneHome.fxml"));
            Scene homeScene = new Scene(homeParent);

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

            stage.setScene(homeScene);
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void saveData(ObservableList<ModelData> data)
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
    
    public void loadData()
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
    
    public void saveCount(ObservableList<ModelCount> count)
    {
        XStream xstream = new XStream(new StaxDriver());
        ModelCountWrapper countWarp = new ModelCountWrapper();
        countWarp.setCount(count);
        String xml = xstream.toXML(countWarp);
        
        FileOutputStream f = null;
        try{
            f = new FileOutputStream("Count.xml");
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
    
    public void loadCount()
    {
        XStream xstream = new XStream(new StaxDriver());
        FileInputStream f = null;
        
        try {
            f = new FileInputStream("Count.xml");
            
            int isi;
            char c;
            String xml = "";
            
            while((isi = f.read() )!=-1){
                c = (char) isi;
                xml = xml + c;
            }
            
           ModelCountWrapper countWrap = new ModelCountWrapper();
           countWrap = (ModelCountWrapper) xstream.fromXML(xml);
           
           count.clear();
           count.addAll(countWrap.getCount());
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
    
    private void choiceKategori()
    {
        kategoriChoice.getItems().addAll("Minuman",
                                      "Roti",
                                      "Kue",
                                      "Mie");
    }
    
    private void countKategori()
    {
        if(kategoriChoice.getValue() == "Minuman"){
            count.get(0).setCountA(count.get(0).getCountA() + 1);
        }else if(kategoriChoice.getValue() == "Roti"){
            count.get(0).setCountB(count.get(0).getCountB() + 1);
        }else if(kategoriChoice.getValue() == "Kue"){
            count.get(0).setCountC(count.get(0).getCountC() + 1);
        }else if(kategoriChoice.getValue() == "Mie"){
            count.get(0).setCountD(count.get(0).getCountD() + 1);
        }
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadData();
        loadCount();
        kategoriChoice.getItems().add("pilih kategori....");
        kategoriChoice.setValue("pilih kategori....");
        choiceKategori();
    }    
}
