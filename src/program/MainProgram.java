package program;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author RIO RISQI (19523031)
 */
public class MainProgram extends Application 
{   
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("SceneAwal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Zefoste");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}
