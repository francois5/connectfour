/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author seb
 */
public class GridElement {
    private Rectangle gridElementShape;
    // Le pane qui contient la GameGrid: GamePane
    private Pane parent;
    
    public GridElement(Pane parent) {
        this.parent = parent;
        
        gridElementShape.widthProperty().bind(parent.widthProperty().divide(30));
        gridElementShape.heightProperty().bind(parent.heightProperty().subtract(100));
    }

    
}
