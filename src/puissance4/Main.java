package puissance4;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Game;
import model.Instru;
import vue.GameMenu;
import vue.GamePane;


/**
 *
 * @author 2311sedoore
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Game game = new Game();
        new GameStage(game);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
