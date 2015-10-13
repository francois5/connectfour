package vue;

import model.Pone;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
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

    public void init(Scene scene) {
        // buttom line
        grid.add(new Rectangle(scene.getWidth(), 5));
        grid.get(grid.size() - 1).setTranslateY(scene.getHeight() - 30);
        
        // collumns
        for (int i = 0; i < 8; ++i) {
            grid.add(new Rectangle(5, (scene.getHeight()-25) / 1.4));
            grid.get(grid.size() - 1).setTranslateX(((scene.getWidth() / 7 ) * i)-(i-1));
            grid.get(grid.size() - 1).setTranslateY((scene.getHeight()-25) / 3.6);
        }
        this.getChildren().addAll(grid);
        
        // pones
        for(int i = 0; i < 42; ++i)
            pones.add(new Pone(grid, this));
        for (Pone p : pones)
            this.getChildren().addAll(p.getPoneShape());
        
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
    protected void layoutChildren() { }

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
