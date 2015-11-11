package puissance4;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Game;


/**
 *
 * @author 2311sedoore
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        new Game();
        new GameStage();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
