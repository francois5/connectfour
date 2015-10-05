package ctrl;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author localwsp
 */
public interface GraphicControler {

    void paintOn(GraphicsContext gc);

    Double getX();

    Double getY();

    void moveTo(Double x, Double y, Scene scene);

    boolean isOn(Double x, Double y);
    
    void sceenWidthResize(Number oldWidth, Number newWidth);
    
    void sceenHeightResize(Number oldHeight, Number newHeight);
}
