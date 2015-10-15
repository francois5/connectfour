package model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Shape;
import vue.GamePane;

/**
 *
 * @author localwsp
 */
public class PoneStock {
    public static int NBPONE = 21;
    private ObservableList<Pone> stock;
    private GameGrid gameGrid;
    private GamePane gamePane;
    
    public PoneStock(GameGrid gameGrid, GamePane gamePane) {
        this.gameGrid = gameGrid;
        this.gamePane = gamePane;
        this.stock = fillPoneStock();
        
    }
    
    public void init(GamePane aThis) {
        gamePane.widthProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneWidth, Number newSceneWidth) -> {
                    for (Pone p : stock) {
                        p.notifySceneWidth((Double) oldSceneWidth, (Double) newSceneWidth);
                    }
                });

        gamePane.heightProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneHeight, Number newSceneHeight) -> {
                    for (Pone p : stock) {
                        p.notifySceneHeight((Double) oldSceneHeight, (Double) newSceneHeight);
                    }
                });
    }
    
    private ObservableList fillPoneStock() {
        ObservableList<Pone> pones = FXCollections.observableArrayList();
        for(int i = 0; i < NBPONE; ++i) {
            pones.add(new Pone(gameGrid.getGrid(), gamePane));
        }
        return pones;
    }
    
    // Retourne la forme du premier pion
    public Shape getPoneShape() {
        return stock.get(0).getPoneShape();
    }
    
    public List<Shape> getPoneShapes() {
        List<Shape> shapes = new ArrayList<>();
        for(Pone p : stock)
            shapes.add(p.getPoneShape());
        return shapes;
    }
    
    // Retourne le stock de pions
    public ObservableList<Pone> getStock() {
        return stock;
    }

    public void update() {
        for(Pone p : stock) {
            p.update();
        }
    }
}
