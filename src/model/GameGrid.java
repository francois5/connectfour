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
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author seb
 */
public class GameGrid extends Pane{
    public static int NBCOLUMNS = 8;
    
    private DoubleProperty width;
    private DoubleProperty height;
    private List<GridElement> grid;
    private Pane parent;
    
    public GameGrid(double width, double height, Pane parent) {
        this.parent = parent;
        this.width = new SimpleDoubleProperty(width);
        this.height = new SimpleDoubleProperty(height);
        this.grid = buildGameGrid();
        for(int i = 0; i < grid.size(); ++i) {
            this.getChildren().add(grid.get(i).getGridElementShape());
        }
    }
    
    private List<GridElement> buildGameGrid() {
        List<GridElement> gridElements = new ArrayList<>();
        for(int i = 0; i < NBCOLUMNS; ++i) {
            grid.add(new GridElement(parent));
        }
        return gridElements;
    }
    
    public List<Shape> getGrid() {
        List<Shape> list = new ArrayList<>();
        for(GridElement gE : grid) {
            list.add(gE.getGridElementShape());
        }
        return list;
    }
    
    
    public void notifySceneWidth(Double oldSceneWidth, Double newSceneWidth) {
        for(GridElement gElement : grid) {
            
        }
    }

    public void notifySceneHeight(Double oldSceneHeight, Double newSceneHeight) {
        
    }
}
