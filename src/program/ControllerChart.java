package program;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.FileInputStream;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author RIO RISQI
 */
public class ControllerChart implements Initializable {

    @FXML
    private PieChart pieChart;
    
    ModelCount modelCount;
    ObservableList<ModelCount> count = FXCollections.observableArrayList();
    
    ObservableList<PieChart.Data> mobilData = FXCollections.observableArrayList();
    
    @FXML
    private void moveToHome(ActionEvent event) throws Exception
    {
        Parent homeParent = FXMLLoader.load(getClass().getResource("SceneHome.fxml"));
        Scene homeScene = new Scene(homeParent);
        
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(homeScene);
        stage.show();
    }
    
    public void addingKategori()
    {
        mobilData.add(new PieChart.Data("Minuman", count.get(0).getCountA()));
        mobilData.add(new PieChart.Data("Roti", count.get(0).getCountB()));
        mobilData.add(new PieChart.Data("Kue", count.get(0).getCountC()));
        mobilData.add(new PieChart.Data("Mie", count.get(0).getCountD()));
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadCount();
        addingKategori();
        pieChart.setData(mobilData);
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
    
}
