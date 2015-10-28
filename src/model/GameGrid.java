/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author seb
 */
public class GameGrid extends Pane {
    public static int NBCOLUMNS = 8;
    
    private List<GridElement> grid = new ArrayList<GridElement>();
    private Pane parent;
    
    public GameGrid(double width, double height, Pane parent) {
        this.parent = parent;
    }
    
    private void setSizeListeners() {
        parent.widthProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneWidth, Number newSceneWidth) -> {
                    notifySceneWidth((double)newSceneWidth);
                });

        parent.heightProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneHeight, Number newSceneHeight) -> {
                    notifySceneHeight((double)newSceneHeight);
                });
    }
    
    public void init(Double width, Double height) {
        this.grid = buildGameGrid(width, height);
        /*for(int i = 0; i < grid.size(); ++i) {
            this.getChildren().add(grid.get(i).getGridElementShape());
        }*/
        setSizeListeners();
    }
    
    private List<GridElement> buildGameGrid(Double width, Double height) {
        for(int i = 0; i < NBCOLUMNS; ++i) {
            grid.add(new GridElement(parent, width, height, true, i));
        }
        grid.add(new GridElement(parent, width, height, false, 0));
        return grid;
    }
    
    public List<Shape> getGrid() {
        List<Shape> list = new ArrayList<>();
        for(GridElement gE : grid) {
            list.add(gE.getGridElementShape());
        }
        return list;
    }
    
    
    public void notifySceneWidth(Double newSceneWidth) {
        for(GridElement gElement : grid) {
            gElement.notifySceneWidth(newSceneWidth);
        }
    }

    public void notifySceneHeight(Double newSceneHeight) {
        for(GridElement gElement : grid) {
            gElement.notifySceneHeight(newSceneHeight);
        }
    }
}
