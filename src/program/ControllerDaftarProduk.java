package program;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RIO RISQI
 */
public class ControllerDaftarProduk implements Initializable 
{
    @FXML
    public TableView<ModelData> table;
    @FXML
    private TableColumn<ModelData, String> colNama;
    @FXML
    private TableColumn<ModelData, String> colHarga;
    @FXML
    private TableColumn<ModelData, String> colTglK;
    @FXML
    private TableColumn<ModelData, String> colDesc;
    
    ModelData modelData;
    
    public ObservableList<ModelData> data = FXCollections.observableArrayList();
    
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
    private void openDetailDialog(ActionEvent event)
    {
        try{
            modelData = table.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ControllerDaftarProduk.class.getResource("SceneDaftarProdukDetail.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Scene scene = new Scene(page);

            Stage stage = new Stage();
            stage.setTitle("Detail Produk");
            stage.setScene(scene);

            ControllerDaftarProdukDetail controller = loader.getController();
            controller.setModelData(modelData);

            stage.show();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Tidak ada data yang dipilih.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void openEditDialog(ActionEvent event)throws Exception
    {
        try{
            ModelData model = table.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ControllerDaftarProduk.class.getResource("SceneDaftarProdukEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Scene scene = new Scene(page);

            Stage stage = new Stage();
            stage.setTitle("Edit Produk");

            ControllerDaftarProdukEdit controller = loader.getController();

            controller.editData(model);
            controller.setIndex(table.getSelectionModel().getSelectedIndex());

            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Tidak ada data yang dipilih.");

            alert.showAndWait();
        }
        
    }
    
    @FXML
    private void hapusAction()
    {
        int index = table.getSelectionModel().getSelectedIndex();
        if(index >= 0){
            table.getItems().remove(index);
        }
        saveData(data);
    }
    
    public void editData(ModelData modelData, String tglMulai, String desc)
    {   
        this.modelData = table.getSelectionModel().getSelectedItem();
        //this.modelData = modelData;
        modelData.setTglMulai(tglMulai);
        modelData.setDesc(desc);
        saveData(data);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadData();
        colNama.setCellValueFactory(new PropertyValueFactory<ModelData, String>("nama"));
        colHarga.setCellValueFactory(new PropertyValueFactory<ModelData, String>("harga"));
        colTglK.setCellValueFactory(new PropertyValueFactory<ModelData, String>("tglMulai"));
        colDesc.setCellValueFactory(new PropertyValueFactory<ModelData, String>("desc"));
        table.setItems(data);
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
    
}
