package puissance4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.Instru;
import model.Pone;
import vue.GameMenu;
import vue.GamePane;


/**
 *
 * @author 2311sedoore
 */
public class Main extends Application {
    private BorderPane root = new BorderPane();
    private GamePane gamePane = new GamePane();
    private final Instru instru = new Instru();
    private final GameMenu gameMenu = new GameMenu(instru);

    @Override
    public void start(Stage primaryStage) throws IOException {
        
        // todo: put pone stocks on the sides
        List<Shape> grid = new ArrayList<>();
        List<Pone> pones = new ArrayList<>();
        root.setCenter(gamePane);
        root.setTop(gameMenu);
        Scene scene = new Scene(root, 800, 800);
        // buttom line
        grid.add(new Rectangle(scene.getWidth(), 5));
        grid.get(grid.size() - 1).setTranslateY(scene.getHeight() - 5);
        
        // collumns
        for (int i = 0; i < 8; ++i) {
            grid.add(new Rectangle(5, scene.getHeight() / 1.4));
            grid.get(grid.size() - 1).setTranslateX(((scene.getWidth() / 7 ) * i)-(i-1));
            grid.get(grid.size() - 1).setTranslateY(scene.getHeight() / 3.6);
        }
        gamePane.getChildren().addAll(grid);
        
        // pones
        for(int i = 0; i < 42; ++i)
            pones.add(new Pone(grid, gamePane));
        for (Pone p : pones)
            gamePane.getChildren().addAll(p.getPoneShape());
        
        MyGameLoop loop = new MyGameLoop(gamePane);
        loop.start();
        
        gamePane.init(grid, pones);
        
        
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
