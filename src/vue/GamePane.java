package vue;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import model.GameGrid;
import model.Instru;
import model.Pone;
import model.PoneStock;

/**
 *
 * @author localwsp
 */
public class GamePane extends Pane {
    private GameGrid gameGrid = new GameGrid(600,600, this);
    private PoneStock rightPoneStock = new PoneStock(gameGrid, this, false);
    private PoneStock leftPoneStock = new PoneStock(gameGrid, this, true);
    
    private final Instru instru = new Instru();
    
    public GamePane() {
        super();
    }

    public void init(Double width, Double height) {
        // Ajoute la grille sur le gamePane
        gameGrid.init(width, height);
        //this.setCenter(gameGrid);
        this.getChildren().add(gameGrid);
        //this.setRight(rightPoneStock);
        //leftPoneStock.widthProperty().add(100);
        //this.setLeft(leftPoneStock);
        
        this.getChildren().add(rightPoneStock);
        rightPoneStock.init(width, height);
        
        this.getChildren().add(leftPoneStock);
        leftPoneStock.init(width, height);
        gameGrid.initFrontImage();
    }
    
    @Override
    protected void layoutChildren() { }

    public void update() {
        rightPoneStock.update();
        leftPoneStock.update();
    }

    public List<Shape> getPones() {
        List<Shape> l = new ArrayList<Shape>();
        l.addAll(rightPoneStock.getPoneShapes());
        l.addAll(leftPoneStock.getPoneShapes());
        return l;
    }

    public Instru getInstru() {
        return instru;
    }

}
