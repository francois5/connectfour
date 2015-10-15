/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author seb
 */
public class GameGrid extends Pane{
    private double width;
    private double height;
    private List<Shape> grid = new ArrayList<>();
    
    public GameGrid(double width, double height) {
        this.width = width;
        this.height = height;
        initGrid();
    }
    
    public void initGrid() {
        // bottom line
        grid.add(new Rectangle(width, 5));
        grid.get(grid.size() - 1).setTranslateY(height + 165);
        
        // collumns
        for (int i = 0; i < 8; ++i) {
            grid.add(new Rectangle(5, height));
            grid.get(grid.size() - 1).setTranslateX(((width / 7 ) * i)-(i-1));
            grid.get(grid.size() - 1).setTranslateY((height) / 3.6);
        }
        // Ajoute les rectangles sur la grille
        this.getChildren().addAll(grid);
    }
    
    public List<Shape> getGrid() {
        return grid;
    }
}
