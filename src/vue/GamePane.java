package vue;

import model.Pone;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.Instru;

/**
 *
 * @author localwsp
 */
public class GamePane extends Pane {

    private List<Shape> grid = new ArrayList<>();
    private List<Pone> pones = new ArrayList<>();
    private final Instru instru = new Instru();
    
    public GamePane() {
        super();
    }

    public void init(List<Shape> grid, List<Pone> pones) {
        this.grid = grid;
        this.pones = pones;
        this.widthProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneWidth, Number newSceneWidth) -> {
                    for (Pone p : pones) {
                        p.notifySceneWidth((Double) oldSceneWidth, (Double) newSceneWidth);
                    }
                });

        this.heightProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneHeight, Number newSceneHeight) -> {
                    for (Pone p : pones) {
                        p.notifySceneHeight((Double) oldSceneHeight, (Double) newSceneHeight);
                    }
                });
        
    }
    
    @Override
    protected void layoutChildren() {
        
        
        
        
    }

    public void update() {
        for(Pone p : pones) {
            p.update();
        }
    }

    public List<Shape> getPones() {
        List<Shape> shapes = new ArrayList<>();
        for(Pone p : pones)
            shapes.add(p.getPoneShape());
        return shapes;
    }

    public Instru getInstru() {
        return instru;
    }

}
