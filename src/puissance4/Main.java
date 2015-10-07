package puissance4;

import ctrl.GameGridControler;
import ctrl.GraphicControler;
import ctrl.PoneControler;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.GameGrid;
import model.Pone;
import vue.GameMenu;
import vue.GamePane;


/**
 *
 * @author 2311sedoore
 */
public class Main extends Application {
    // Les éléments graphiques
    private List<GraphicControler> graphics = new ArrayList<GraphicControler>();
    private final BorderPane root = new BorderPane();
    private final Scene scene = new Scene(root, 1000, 500);
    private final GamePane gamePane = new GamePane(graphics, scene);
    private final GameMenu gameMenu = new GameMenu();
    
    
    @Override
    public void start(Stage primaryStage) {
        Pone p1 = new Pone();
        GameGrid gameGrid = new GameGrid();
        
        GraphicControler pc1 = new PoneControler(p1);
        GraphicControler pc2 = new GameGridControler(gameGrid);
        
        graphics.add(pc1);
        graphics.add(pc2);

        root.setCenter(gamePane);
        root.setTop(gameMenu);
        
        p1.xPercentageProperty().set(0.5);
        p1.yPercentageProperty().set(0.5);
        p1.xProperty().set(500);
        p1.yProperty().set(250);
        p1.widthProperty().bind(scene.widthProperty().divide(7));
        p1.heightProperty().bind(scene.heightProperty().divide(6));

        scene.widthProperty().addListener((ObservableValue<? extends Number> observableValue, 
                Number oldSceneWidth, Number newSceneWidth) -> {
            gamePane.sceneWidthResize(oldSceneWidth, newSceneWidth);
        });
        scene.heightProperty().addListener((ObservableValue<? extends Number> observableValue, 
                Number oldSceneHeight, Number newSceneHeight) -> {
            gamePane.sceneHeightResize(oldSceneHeight, newSceneHeight);
        });

        primaryStage.setTitle("Puissance 4");
        primaryStage.setScene(scene);
        primaryStage.show();
        gamePane.repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
