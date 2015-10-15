package vue;

import model.GameGrid;
import model.Pone;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.Instru;

/**
 *
 * @author localwsp
 */
public class GamePane extends Pane {

    private List<Pone> pones = new ArrayList<>();
    private final Instru instru = new Instru();
    
    public GamePane() {
        super();
    }

    public void init(Scene scene) {
        // Ajoute la grille sur le gamePane
        GameGrid gameGrid = new GameGrid(600,600);
        this.getChildren().add(gameGrid);
        
        // pones
        for(int i = 0; i < 42; ++i)
            pones.add(new Pone(gameGrid.getGrid(), this));
        for (Pone p : pones)
            this.getChildren().addAll(p.getPoneShape());
        
        this.widthProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneWidth, Number newSceneWidth) -> {
                    for (Pone p : pones) {
                        p.notifySceneWidth((Double) oldSceneWidth, (Double) newSceneWidth);
                    }
                });

        this.heightProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneHeight, Number newSceneHeight) -> {
                    for (Pone p : pones) {
                        p.notifySceneHeight((Double) oldSceneHeight, (Double) newSceneHeight);
                    }
                });
        
    }
    
    @Override
    protected void layoutChildren() { }

    public void update() {
        for(Pone p : pones) {
            p.update();
        }
    }

    public List<Shape> getPones() {
        List<Shape> shapes = new ArrayList<>();
        for(Pone p : pones)
            shapes.add(p.getPoneShape());
        return shapes;
    }

    public Instru getInstru() {
        return instru;
    }

}
