package vue.drawings;

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
    private boolean vertical;
    private Double parentWidth, parentHeight;
    private int nbCol;
    private boolean attached = false;
    
    public GridElement(Pane parent, Double parentWidth, Double parentHeight, boolean vertical, int nbCol) {
        this.parent = parent;
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
        this.vertical = vertical;
        this.nbCol = nbCol;
        buildShape();
        repos();
        attach();
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
            this.gridElementShape = new Rectangle((parentWidth-(parentWidth*(2/6))), 0.01d);
        else
            gridElementShape = new Rectangle(0.01d, parentHeight*0.8f);
    }
    
    private void repos() {
        if(!vertical) {
            gridElementShape.setWidth((parentWidth-(parentWidth*(2d/6d))));
            gridElementShape.setTranslateY(parentHeight - ((5d/800d)*parentWidth));
            gridElementShape.setTranslateX(parentWidth*(1d/6d));
        }
        else {
            gridElementShape.setHeight(parentHeight*0.8f);
            gridElementShape.setTranslateX(((((parentWidth-(parentWidth*(2d/6d))) / 7d ) 
                    * nbCol)) + (parentWidth*(1d/6d)));
            gridElementShape.setTranslateY(parentHeight / 3.6);
        }
    }
    
    public Shape getGridElementShape() {
        return gridElementShape;
    }
    
    public void detach() {
        if(attached) {
            this.parent.getChildren().remove(gridElementShape);
            attached = false;
        }
    }
    
    public void attach() {
        if(!attached) {
            this.parent.getChildren().add(gridElementShape);
            attached = true;
        }
    }

    public boolean isAttached() {
        return attached;
    }
    
}
