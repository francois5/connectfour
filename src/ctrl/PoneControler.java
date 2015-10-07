package ctrl;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import model.Pone;

/**
 *
 * @author localwsp
 */
public class PoneControler implements GraphicControler{
    private Pone pone;
    
    public PoneControler(Pone pone) {
        this.pone = pone;
    }
    
    public PoneControler() {
        this(new Pone());
    }

    @Override
    public void paintOn(GraphicsContext gc) {
        pone.paintOn(gc);
    }

    @Override
    public Double getX() {
        return pone.getX();
    }

    @Override
    public Double getY() {
        return pone.getY();
    }

    @Override
    public void moveTo(Double x, Double y, Scene scene) {
        pone.moveTo(x, y, scene);
    }

    @Override
    public boolean isOn(Double x, Double y) {
        return pone.isOn(x, y);
    }

}
