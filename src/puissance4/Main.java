package puissance4;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Instru;
import vue.GameMenu;
import vue.GamePane;


/**
 *
 * @author 2311sedoore
 */
public class Main extends Application {
    private final BorderPane root = new BorderPane();
    private final GamePane gamePane = new GamePane();
    private final Instru instru = new Instru();
    private final GameMenu gameMenu = new GameMenu(instru);

    @Override
    public void start(Stage primaryStage) throws IOException {
        root.setCenter(gamePane);
        root.setTop(gameMenu);
        // todo: put pone stocks on the sides
        
        Scene scene = new Scene(root, 800, 800);
        
        MyGameLoop loop = new MyGameLoop(gamePane);
        loop.start();
        
        gamePane.init();

        primaryStage.setTitle("Puissance 4");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
