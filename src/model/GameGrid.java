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
    private List<Shape> grid = new ArrayList<>();
    
    public GameGrid(Scene scene) {
        initGrid(scene);
    }
    
    public void initGrid(Scene scene) {
        // bottom line
        grid.add(new Rectangle(scene.getWidth(), 5));
        grid.get(grid.size() - 1).setTranslateY(scene.getHeight() - 30);
        
        // collumns
        for (int i = 0; i < 8; ++i) {
            grid.add(new Rectangle(5, (scene.getHeight()-25) / 1.4));
            grid.get(grid.size() - 1).setTranslateX(((scene.getWidth() / 7 ) * i)-(i-1));
            grid.get(grid.size() - 1).setTranslateY((scene.getHeight()-25) / 3.6);
        }
        this.getChildren().addAll(grid);
    }
    
    public List<Shape> getGrid() {
        return grid;
    }
}
