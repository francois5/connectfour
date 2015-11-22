package model;

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
    private List<Shape> grid;
    private Pane parent;
    
    private Double xPercentage;
    private Double yPercentage;
    private Double homeXPercentage;
    private Double homeYPercentage;
    private boolean physicsEnable = false;
    private int speed;
    private int time;
    private int g = 9;
    
    public Pone(List<Shape> grid,  Pane parent, Double homeXPercentage , Double homeYPercentage) {
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
        poneShape.radiusYProperty().bind(parent.heightProperty().divide(24));
        
        setDragListeners(poneShape);
    }
    
    public void init(Double width, Double height) {
        this.poneShape.setTranslateX(width*homeXPercentage);
        this.poneShape.setTranslateY(height*homeYPercentage);
        this.xPercentage = poneShape.getTranslateX()/width;
        this.yPercentage = poneShape.getTranslateY()/height;
        //this.poneShape.setFill(Color.BLACK);
        //this.poneShape.toBack();
    }
    
    public Shape getPoneShape() {
        return poneShape;
    }
    
    public void setDragListeners(final Shape curShape) {
        final Delta dragDelta = new Delta();

        curShape.setOnMousePressed((MouseEvent mouseEvent) -> {
            disablePhysics();
            // Pourquoi ça ne fonctionne pas 
            // si on ne prend pas e.getTranslateX/Y ?
            dragDelta.x = curShape.getTranslateX() - mouseEvent.getSceneX();
            dragDelta.y = curShape.getTranslateY() - mouseEvent.getSceneY();
            curShape.setCursor(Cursor.NONE);
        });
        curShape.setOnMouseReleased((MouseEvent mouseEvent) -> {
            if(validMove()) enablePhysics();
            else            goHome();
            
            curShape.setCursor(Cursor.HAND);
            checkBounds(curShape, grid);
            checkBounds(curShape, ((GamePane)parent).getPones());
        });
        curShape.setOnMouseDragged((MouseEvent mouseEvent) -> {
            curShape.setTranslateX(mouseEvent.getSceneX() + dragDelta.x);
            curShape.setTranslateY(mouseEvent.getSceneY() + dragDelta.y);
            
            recalculatePercentages();
            
            
        });
    }
    
    // check collision
    private void checkBounds(Shape e, List<Shape> shapes) {
        for (Shape s : shapes) {
            if (s != e) {
                Shape path = Path.intersect(e, s);
                if (!path.getBoundsInParent().isEmpty()) {
                    disablePhysics();
                    // Pour replacer le Pone juste au-dessus de la Shape
                    // en collision
                    double otherShapePosY = s.getBoundsInLocal().getMinY();
                    Ellipse ellipse = (Ellipse) e;
                    ellipse.setTranslateY(otherShapePosY - ellipse.getRadiusX());
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
            //this.poneShape.setFill(Color.BLACK);
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
    
    private void goHome() {
        this.poneShape.setTranslateX(parent.getWidth()*homeXPercentage);
        this.poneShape.setTranslateY(parent.getHeight()*homeYPercentage);
        this.recalculatePercentages();
        //this.poneShape.setFill(Color.BLACK);
    }

    private boolean validMove() {
        // to do
        return true;
    }
    
    class Delta {
        double x, y;
    }
}
