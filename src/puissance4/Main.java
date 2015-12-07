package puissance4;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Sound;
import vue.HomeStage;


/**
 *
 * @author 2311sedoore
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Sound sound = new Sound();
        //sound.play();
        HomeStage homeStage = new HomeStage();
        //GameStage gameStage = new GameStage();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
