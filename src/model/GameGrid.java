package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author localwsp
 */
public class GameGrid implements Graphic {
    private DoubleProperty x = new SimpleDoubleProperty(); // Le coin supérieur gauche du carré englobant
    private DoubleProperty y = new SimpleDoubleProperty();
    private DoubleProperty xPercentage = new SimpleDoubleProperty();
    private DoubleProperty yPercentage = new SimpleDoubleProperty();
    private DoubleProperty width = new SimpleDoubleProperty();
    private DoubleProperty height = new SimpleDoubleProperty();
    
    public GameGrid(int x, int y, int width, int height) {
        this.x.set(x);
        this.y.set(y);
        this.width.set(width);
        this.height.set(height);
    }
    
    public GameGrid() {
        this(0,0,200,100);
    }
    
    @Override
    public Double getX() {
        return x.get();
    }

    @Override
    public Double getY() {
        return y.get();
    }

    @Override
    public Double getWidth() {
        return width.get();
    }

    @Override
    public Double getHeight() {
        return height.get();
    }

    @Override
    public void scale(Double factor) {
        width.set(width.get() * factor);
        height.set(height.get() * factor);
    }

    @Override
    public void move(Double directionX, Double directionY, Scene scene) {
        x.set(x.get() + directionX);
        y.set(y.get() + directionY);
        recalculatePercentages(scene);
    }
    
    private void recalculatePercentages(Scene scene) {
        
        this.xPercentage.set(x.get()/scene.getWidth());
        x.set(scene.getWidth()*xPercentage.get());
        
        this.yPercentage.set(y.get()/scene.getHeight());
        y.set(scene.getHeight()*yPercentage.get());
    }
    
    @Override
    public void moveTo(Double x, Double y, Scene scene) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean isOn(Double x, Double y) {
        Double rectX = this.x.get();
        Double rectY = this.y.get();
        Double rectWidth = width.get();
        Double rectHeight = height.get();
        return isOnRectangle(x, y, rectX, rectY, rectWidth, rectHeight);
    }
    
    private boolean isOnRectangle(Double x, Double y, Double rectX, Double rectY,
            Double rectWidth, Double rectHeight) {
        return x >= rectX && x <= rectWidth &&
               // Rappel: y devient négatif s'il augmente
               y <= rectY && y >= rectHeight;
    }

    @Override
    public void paintOn(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(gc.getCanvas().getWidth()*xPercentage.get(), 
                    gc.getCanvas().getHeight()*yPercentage.get(), 
                    width.get(), 
                    height.get());
        x.set(gc.getCanvas().getWidth()*xPercentage.get());
        y.set(gc.getCanvas().getHeight()*yPercentage.get());
    }
    
}
