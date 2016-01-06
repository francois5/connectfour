package vue.drawings;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author seb
 */
public class GameGrid extends Pane {
    public static int NBCOLUMNS = 8;
    
    private Image image = new Image("grid.png");
    private ImageView imageView = new ImageView();
    private List<GridElement> grid = new ArrayList<GridElement>();
    Rectangle floor;
    private Pane parent;
    
    public GameGrid(double width, double height, Pane parent) {
        this.parent = parent;
    }
    
    public void initFrontImage() {
        image.widthProperty().divide(6).multiply(4);
        image.heightProperty().multiply(0.725f);
        imageView.setImage(image);
        reposFrontImage(800d,800d);
        getChildren().add(imageView);
        this.toFront();
        this.pickOnBoundsProperty().set(false);
    }
    
    private void reposFrontImage(Double width, Double height) {
        imageView.setTranslateX(width/6);
        imageView.setTranslateY(height/3.6);
        
        imageView.setFitHeight(height*0.725f);
        imageView.setFitWidth((width/6)*4);
    }
    
    private void setSizeListeners() {
        parent.widthProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneWidth, Number newSceneWidth) -> {
                    notifySceneWidth((double)newSceneWidth);
                    reposFrontImage((double)newSceneWidth, parent.getHeight());
                });

        parent.heightProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneHeight, Number newSceneHeight) -> {
                    notifySceneHeight((double)newSceneHeight);
                    reposFrontImage(parent.getWidth(), (double)newSceneHeight);
                });
    }
    
    public void init(Double width, Double height) {
        this.grid = rebuildGameGrid(width, height);
        floor = new Rectangle(width+(parent.getHeight()/3), 10d);
        floor.setTranslateY(height-35);//+(height/3.1));
        parent.getChildren().add(floor);
        setSizeListeners();
    }
    
    private List<GridElement> rebuildGameGrid(Double width, Double height) {
        for(int i = 0; i < NBCOLUMNS; ++i) {
            grid.add(new GridElement(parent, width, height, true, i));
        }
        grid.add(new GridElement(parent, width, height, false, 0));
        return grid;
    }
    
    public List<Shape> getGrid() {
        List<Shape> list = new ArrayList<>();
        for(GridElement gE : grid) {
            if(gE.isAttached())
                list.add(gE.getGridElementShape());
        }
        list.add(floor);
        
        return list;
    }
    
    
    public void open() {
        grid.get(grid.size()-1).detach();
    }
    
    public void close() {
        grid.get(grid.size()-1).attach();
    }
    
    public void notifySceneWidth(Double newSceneWidth) {
        for(GridElement gElement : grid) {
            gElement.notifySceneWidth(newSceneWidth);
        }
        repos(floor, newSceneWidth, null);
    }
    
    public void notifySceneHeight(Double newSceneHeight) {
        for(GridElement gElement : grid) {
            gElement.notifySceneHeight(newSceneHeight);
        }
        repos(floor, null, newSceneHeight);
    }
    
    public ImageView getImage() {
        return this.imageView;
    }

    private void repos(Rectangle floor, Double w, Double h) {
        if     (w != null) floor.setWidth(w);
        else if(h != null) floor.setTranslateY(h*1.25);
    }
}
