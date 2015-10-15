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
import model.PoneStock;

/**
 *
 * @author localwsp
 */
public class GamePane extends Pane {
    private GameGrid gameGrid = new GameGrid(600,600);
    private PoneStock poneStock = new PoneStock(gameGrid, this);
    //private List<Pone> pones = new ArrayList<>();
    private final Instru instru = new Instru();
    
    public GamePane() {
        super();
    }

    public void init() {
        // Ajoute la grille sur le gamePane
        this.getChildren().add(gameGrid);
        
        poneStock.init(this);
        
        // pones
        for (Pone p : poneStock.getStock())
            this.getChildren().add(p.getPoneShape());
    }
    
    @Override
    protected void layoutChildren() { }
        
    public void update() {
        poneStock.update();
    }

    public List<Shape> getPones() {
        return poneStock.getPoneShapes();
    }

    public Instru getInstru() {
        return instru;
    }

}
