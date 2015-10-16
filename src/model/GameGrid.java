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
    private DoubleProperty width;
    private DoubleProperty height;
    private List<Shape> grid = new ArrayList<>();
    private Pane parent;
    
    public GameGrid(double width, double height, Pane parent) {
        this.parent = parent;
        this.width = new SimpleDoubleProperty(width);
        this.height = new SimpleDoubleProperty(height);
        initGrid();
        setResizable();
    }
    
    public void initGrid() {
        // bottom line
        grid.add(new Rectangle(width.get(), 5));
        grid.get(grid.size() - 1).setTranslateY(height.get() + 165);
        
        // collumns
        for (int i = 0; i < 8; ++i) {
            grid.add(new Rectangle(5, height.get()));
            grid.get(grid.size() - 1).setTranslateX(((width.get() / 7 ) * i)-(i-1));
            grid.get(grid.size() - 1).setTranslateY((height.get()) / 3.6);
        }
        // Ajoute les rectangles sur la grille
        this.getChildren().addAll(grid);
    }
    
    public List<Shape> getGrid() {
        return grid;
    }
    
    private void setResizable() {
        width.bind(parent.widthProperty().subtract(100));
        height.bind(parent.heightProperty().subtract(100));
    }
}
