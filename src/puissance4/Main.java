package puissance4;

import vue.widgets.GameStage;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 *
 * @author 2311sedoore
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        //Sound sound = SoundFactory.getSound();
        //sound.play();
        GameStage g = new GameStage();
        g.newGame();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
