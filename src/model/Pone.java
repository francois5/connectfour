package model;

import java.util.List;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;
import vue.GamePane;

/**
 *
 * @author localwsp
 */
public class Pone {
    private Ellipse poneShape;
    private List<Shape> grid;
    private Pane parent;
    
    private Double xPercentage;
    private Double yPercentage;
    private boolean physicsEnable = false;
    private int speed;
    private int time;
    private int g = 9;
    
    public Pone(List<Shape> grid, Pane parent) {
        this.parent = parent;
        this.grid = grid;
        poneShape = new Ellipse();
        xPercentage = 0d;
        yPercentage = 0d;
        
        poneShape.radiusXProperty().bind(parent.widthProperty().divide(16));
        poneShape.radiusYProperty().bind(parent.heightProperty().divide(16));
        
        setDragListeners(poneShape);
        
        enablePhysics();
    }
    
    public Shape getPoneShape() {
        return poneShape;
    }
    
    public void setDragListeners(final Shape e) {
        final Delta dragDelta = new Delta();

        e.setOnMousePressed((MouseEvent mouseEvent) -> {
            disablePhysics();
            dragDelta.x = e.getTranslateX() - mouseEvent.getSceneX();
            dragDelta.y = e.getTranslateY() - mouseEvent.getSceneY();
            e.setCursor(Cursor.NONE);
        });
        e.setOnMouseReleased((MouseEvent mouseEvent) -> {
            enablePhysics();
            e.setCursor(Cursor.HAND);
        });
        e.setOnMouseDragged((MouseEvent mouseEvent) -> {
            e.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);
            e.setTranslateY(mouseEvent.getSceneY() + dragDelta.y);
            
            recalculatePercentages();
            
            this.poneShape.setFill(Color.BLACK);
            checkBounds(e, grid);
            checkBounds(e, ((GamePane)parent).getPones());
        });
    }
    
    // check collision
    private void checkBounds(Shape e, List<Shape> shapes) {
        for (Shape s : shapes) {
            if (s != e) {
                Shape path = Path.intersect(e, s);
                if (!path.getBoundsInParent().isEmpty()) {
                    e.setFill(Color.RED);
                    disablePhysics();
                }
            }
        }
    }

    public void notifySceneWidth(Double oldSceneWidth, Double newSceneWidth) {
        poneShape.setTranslateX(newSceneWidth*xPercentage);
    }

    public void notifySceneHeight(Double oldSceneHeight, Double newSceneHeight) {
        poneShape.setTranslateY(newSceneHeight*yPercentage);
    }
    
    private void recalculatePercentages() {
        this.xPercentage = poneShape.getTranslateX()/parent.getWidth();
        this.yPercentage = poneShape.getTranslateY()/parent.getHeight();
    }

    public void update() {
        if(physicsEnable) {
            ++time;
            speed = gravitationalAcceleration(speed);
            this.poneShape.setTranslateY(this.poneShape.getTranslateY() + (speed/1000));
            recalculatePercentages();
            this.poneShape.setFill(Color.BLACK);
            checkBounds(this.poneShape, this.grid);
            checkBounds(this.poneShape, ((GamePane)parent).getPones());
            if(!physicsEnable)
                playCollisionSound();
        }
    }

    private void enablePhysics() {
        speed = 0;
        time = 0;
        physicsEnable = true;
    }
    private void disablePhysics() {
        physicsEnable = false;
    }

    private int gravitationalAcceleration(int speed) {
        return ((g)*(time) + speed);
    }

    private void playCollisionSound() {
        ((GamePane)parent).getInstru().note_on(time);
    }
    
    class Delta {
        double x, y;
    }
}
