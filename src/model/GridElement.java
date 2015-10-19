/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    private boolean vertical;
    private Double parentWidth, parentHeight;
    private int nbCol;
    
    public GridElement(Pane parent, Double parentWidth, Double parentHeight, boolean vertical, int nbCol) {
        this.parent = parent;
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
        this.vertical = vertical;
        this.nbCol = nbCol;
        buildShape();
        repos();
        this.parent.getChildren().add(gridElementShape);
    }
    
    public void notifySceneWidth(Double newSceneWidth) {
        this.parentWidth = newSceneWidth;
        repos();
    }

    public void notifySceneHeight(Double newSceneHeight) {
        this.parentHeight = newSceneHeight;
        repos();
    }
    
    private void buildShape() {
        if(!vertical)
            this.gridElementShape = new Rectangle((parentWidth-(parentWidth*(2/6))), ((5d/800d)*parentWidth));
        else
            gridElementShape = new Rectangle(((5d/800d)*parentWidth), parentHeight*0.8f);
    }
    
    private void repos() {
        if(!vertical) {
            gridElementShape.setWidth((parentWidth-(parentWidth*(2d/6d))));
            gridElementShape.setTranslateY(parentHeight - ((5d/800d)*parentWidth));
            gridElementShape.setTranslateX(parentWidth*(1d/6d));
        }
        else {
            gridElementShape.setWidth(((5d/800d)*parentWidth));
            gridElementShape.setHeight(parentHeight*0.8f);
            gridElementShape.setTranslateX(((((parentWidth-(parentWidth*(2d/6d))) / 7d ) 
                    * nbCol)-(nbCol-1)) + (parentWidth*(1d/6d)));
            gridElementShape.setTranslateY(parentHeight / 3.6);
        }
    }
    
    public Shape getGridElementShape() {
        return gridElementShape;
    }
}
