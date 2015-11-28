package model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
    private GameGrid grid;
    private Pane parent;
    private Sound sound = new Sound();
    
    private Double xPercentage;
    private Double yPercentage;
    private Double homeXPercentage;
    private Double homeYPercentage;
    private boolean physicsEnable = false;
    private int speed;
    private int time;
    private int g = 9;
    private boolean compensateCollisionEffect = false;
    private boolean stickToColumn = false;
    
    public Pone(GameGrid grid,  Pane parent, Double homeXPercentage , 
            Double homeYPercentage) {
        this.parent = parent;
        this.grid = grid;
        poneShape = new Ellipse();
        // Création d'une classe css pour poneShape
        //poneShape.getStyleClass().add("pone-shape");
        xPercentage = 0d;
        yPercentage = 0d;
        this.homeXPercentage = homeXPercentage;
        this.homeYPercentage = homeYPercentage;
        
        poneShape.radiusXProperty().bind(parent.widthProperty().divide(24));
        poneShape.radiusYProperty().bind(parent.heightProperty().divide(17));
        
        setDragListeners(poneShape);
    }
    
    public void init(Double width, Double height) {
        this.poneShape.setTranslateX(width*homeXPercentage);
        this.poneShape.setTranslateY(height*homeYPercentage);
        this.xPercentage = poneShape.getTranslateX()/width;
        this.yPercentage = poneShape.getTranslateY()/height;
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
            if(validMove()) enablePhysics();
            else            goHome();
            
            e.setCursor(Cursor.HAND);
        });
        e.setOnMouseDragged((MouseEvent mouseEvent) -> {
            e.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);
            e.setTranslateY(mouseEvent.getSceneY() + dragDelta.y);
            stickToColumns();
            recalculatePercentages();
            
            checkBounds(e, grid.getGrid(), false);
            checkBounds(e, ((GamePane)parent).getPones(), false);
        });
    }
    
    private void stickToColumns() {
        double location = locationOfStickyColumnInAttractionRange();
        if(location != 0) {
            stickToColumn = true;
            poneShape.setTranslateX(location);
        }
        else
            stickToColumn = false;
    }
    
    private double locationOfStickyColumnInAttractionRange() {
        double gridBegin = poneShape.radiusXProperty().get()*4;
        double gridEnd = parent.getWidth()-gridBegin;
        double columnWidth = (gridEnd-gridBegin)/7;
        double[] rectifications = new double[] {1.5,1,0.5,0,-0.5,-1,-1.5};
        multVect(rectifications, parent.getWidth()/300);
        double columnsAttractionRadius = 10*(parent.getWidth()/300);
        for(int i = 0; i < 7; ++i) {
            double columnLocation = (gridBegin+rectifications[i]+columnWidth/2)+i*columnWidth;
            if(poneShape.getTranslateX() >= columnLocation-columnsAttractionRadius 
                    && poneShape.getTranslateX() <= columnLocation+columnsAttractionRadius)
                return columnLocation;
        }
        return 0;
    }
    
    // check collision
    // enable à false avant une collision
    // enable à true après une collision
    private void checkBounds(Shape e, List<Shape> shapes, boolean enable) {
        boolean disabled = false;
        for (Shape s : shapes) {
            if (s != e) {
                Shape path = Path.intersect(e, s);
                if (!path.getBoundsInParent().isEmpty()) {
                    disablePhysics();
                    disabled = true;
                }
            }
        }
        // Si path.getBoundsInParent().isEmpty
        // Il n'y a pas eu de collision
        
        // Si enable est à true, la collision a déja eu lieu
        // Donc on réactive la physique
        if (!disabled && enable)
            enablePhysics();
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
        if(compensateCollisionEffect)
            compensateCollisionEffect();
        else if(physicsEnable) {
            ++time;
            speed = gravitationalAcceleration(speed);
            this.poneShape.setTranslateY(this.poneShape.getTranslateY() + (speed/1000));
            recalculatePercentages();
            checkBounds(this.poneShape, this.grid.getGrid(), false);
            // check les collisions entre poneShape et les stock de Pones
            checkBounds(this.poneShape, ((GamePane)parent).getPones(), false);
            if(!physicsEnable) {
                playCollisionSound();
                compensateCollisionEffect = true;
            }
        }
    }
    
    private void compensateCollisionEffect() {
        this.poneShape.setTranslateY(this.poneShape.getTranslateY() - 1);
        ArrayList<Shape> concatPoneGrid = new ArrayList<Shape>(this.grid.getGrid());
        concatPoneGrid.addAll(((GamePane) parent).getPones());
        checkBounds(this.poneShape, concatPoneGrid, true);
        if (physicsEnable) {
            compensateCollisionEffect = false;
            physicsEnable = false;
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
        //((GamePane)parent).getInstru().note_on(time);
        sound.play();
    }
    
    private void goHome() {
        this.poneShape.setTranslateX(parent.getWidth()*homeXPercentage);
        this.poneShape.setTranslateY(parent.getHeight()*homeYPercentage);
        this.recalculatePercentages();
    }

    private boolean validMove() {
        return stickToColumn;
    }
    
    public void multVect(double[] v, double x) {
        for (int i = 0; i < v.length; ++i)
            v[i] = v[i] * x;
    }
    
    class Delta {
        double x, y;
    }
}
