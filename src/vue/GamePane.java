package vue;

import model.GameGrid;
import model.Pone;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import model.Instru;
import model.PoneStock;

/**
 *
 * @author localwsp
 */
public class GamePane extends Pane {
    private GameGrid gameGrid = new GameGrid(600,600, this);
    private PoneStock poneStock = new PoneStock(gameGrid, this);
    private final Instru instru = new Instru();
    
    public GamePane() {
        super();
    }

    public void init() {
        // Ajoute la grille sur le gamePane
        this.getChildren().add(gameGrid);
        
        // pones
        for (Pone p : poneStock.getStock())
            this.getChildren().add(p.getPoneShape());
        
        this.widthProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneWidth, Number newSceneWidth) -> {
                    for (Pone p : poneStock.getStock()) {
                        p.notifySceneWidth((Double) oldSceneWidth, (Double) newSceneWidth);
                    }
                });

        this.heightProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneHeight, Number newSceneHeight) -> {
                    for (Pone p : poneStock.getStock()) {
                        p.notifySceneHeight((Double) oldSceneHeight, (Double) newSceneHeight);
                    }
                });
        
    }
    
    @Override
    protected void layoutChildren() { }

    public void update() {
        for(Pone p : poneStock.getStock()) {
            p.update();
        }
    }

    public List<Shape> getPones() {
        List<Shape> shapes = new ArrayList<>();
        for(Pone p : poneStock.getStock())
            shapes.add(p.getPoneShape());
        return shapes;
    }

    public Instru getInstru() {
        return instru;
    }

}
