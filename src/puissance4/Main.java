package puissance4;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Part;
import model.Player;


/**
 *
 * @author 2311sedoore
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Part p = new Part(new Player("sebastien"), new Player("françois"));
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
