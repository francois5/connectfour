package vue;

import ctrl.GraphicControler;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author localwsp
 */
public class GamePane extends Pane {

    List<GraphicControler> graphics = new ArrayList<GraphicControler>();
    Double startX, startY;
    Double x0, y0;
    private Scene scene;
    GraphicControler selection;

    private final Canvas canvas = new Canvas();

    public GamePane(List<GraphicControler> graphics, Scene scene) {
        this.graphics = graphics;
        this.scene = scene;
        this.setOnMousePressed((MouseEvent e) -> {
            startX = e.getX();
            startY = e.getY();
            selection = null;
            for (GraphicControler ctrl : graphics) {
                if (ctrl.isOn(e.getX(), e.getY())) {
                    selection = ctrl;
                    x0 = selection.getX();
                    y0 = selection.getY();
                }
            }
        });

        this.setOnMouseReleased((MouseEvent e) -> {
            
        });

        this.setOnMouseDragged((MouseEvent e) -> {
            if (selection != null) {
                selection.moveTo(x0 + e.getX() - startX, y0 + e.getY() - startY, this.scene);
                repaint();
            }
        });

        canvas.setCursor(Cursor.CROSSHAIR);
        super.getChildren().add(canvas);
    }

    public GamePane() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void sceenWidthResize(Number oldWidth, Number newWidth) {
        for (GraphicControler ctrl : graphics) {
            ctrl.sceenWidthResize(oldWidth, newWidth);
        }
    }

    public void sceenHeightResize(Number oldHeight, Number newHeight) {
        for (GraphicControler ctrl : graphics) {
            ctrl.sceenHeightResize(oldHeight, newHeight);
        }
    }
    
    public void repaint() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.RED);
        for (GraphicControler ctrl : graphics) {
            ctrl.paintOn(gc);
        }
    }

    @Override
    protected void layoutChildren() {
        canvas.setWidth(getWidth());
        canvas.setHeight(getHeight());
        repaint();
    }
}
