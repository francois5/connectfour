package model;

import java.util.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author localwsp
 */
public class Pone extends Observable implements Graphic {

    private DoubleProperty x = new SimpleDoubleProperty(); // Le coin supérieur gauche du carré englobant
    private DoubleProperty y = new SimpleDoubleProperty();
    private DoubleProperty xPercentage = new SimpleDoubleProperty();
    private DoubleProperty yPercentage = new SimpleDoubleProperty();
    private DoubleProperty w = new SimpleDoubleProperty();
    private DoubleProperty h = new SimpleDoubleProperty();

    public Pone(int x, int y, int w, int h) {
        this.x.set(x);
        this.y.set(y);
        this.w.set(w);
        this.h.set(h);
    }

    public Pone() {
        this(0, 0, 50, 50);
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public DoubleProperty wProperty() {
        return w;
    }

    public DoubleProperty hProperty() {
        return h;
    }

    public DoubleProperty xPercentageProperty() {
        return xPercentage;
    }

    public DoubleProperty yPercentageProperty() {
        return yPercentage;
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
        return w.get();
    }

    @Override
    public Double getHeight() {
        return h.get();
    }

    @Override
    public void scale(Double factor) {
        w.set(w.get() * factor);
        h.set(h.get() * factor);
    }

    @Override
    public void move(Double directionX, Double directionY, Scene scene) {
        x.set(x.get()+ directionX);
        y.set(y.get() + directionY);
        recalculatePercentages(scene);
    }

    @Override
    public void moveTo(Double x, Double y, Scene scene) {
        this.x.set(x);
        this.y.set(y);
        recalculatePercentages(scene);
    }
    
    private void recalculatePercentages(Scene scene) {
        
        this.xPercentage.set(x.get()/scene.getWidth());
        x.set(scene.getWidth()*xPercentage.get());
        
        this.yPercentage.set(y.get()/scene.getHeight());
        y.set(scene.getHeight()*yPercentage.get());
    }

    @Override
    public boolean isOn(Double x, Double y) {
        // ellipse x and y radius
        Double rx = w.get()/2;
        Double ry = h.get()/2;
        // ellipse center
        Double h = this.x.get() + rx;
        Double k = this.y.get() + ry;
        
        return isOnEllipse(x, y, h, k, rx, ry);
    }
    
    private boolean isOnEllipse(Double x, Double y, Double h, Double k, Double rx, Double ry) {
        if(rx <= 0 || ry <= 0) 
            return false;
        // (x-h)²/rx² + (y-k)²/ry² <= 1
        return ((((x-h)*(x-h))/(rx*rx)) + (((y-k)*(y-k))/(ry*ry))) <= 1;
    }

    @Override
    public void paintOn(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(gc.getCanvas().getWidth()*xPercentage.get(), 
                gc.getCanvas().getHeight()*yPercentage.get(), w.get(), h.get());
        x.set(gc.getCanvas().getWidth()*xPercentage.get());
        y.set(gc.getCanvas().getHeight()*yPercentage.get());
    }

}
