package vue.drawings;

import ctrl.GameCtrl;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import vue.widgets.GamePane;

/**
 *
 * @author localwsp
 */
public class PoneStock extends Pane {
    public static int NBPONE = 21;
    private ObservableList<Pone> stock;
    private GameGrid gameGrid;
    private GamePane gamePane;
    private GameCtrl play;
    private boolean leftSide;
    private boolean autoMoveRequested = false;
    private int autoMoveRequestedCol;
    private int autoMoveRequestedCountDown;
    
    public PoneStock(GameCtrl play, GameGrid gameGrid, GamePane gamePane, boolean leftSide) {
        this.play = play;
        this.gameGrid = gameGrid;
        this.gamePane = gamePane;
        this.leftSide = leftSide;
    }
    
    private void setSizeListeners() {
        gamePane.widthProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneWidth, Number newSceneWidth) -> {
                    for (Pone p : stock) {
                        p.notifySceneWidth((Double) oldSceneWidth, (Double) newSceneWidth);
                    }
                });

        gamePane.heightProperty().addListener((ObservableValue<? extends Number> observableValue,
                Number oldSceneHeight, Number newSceneHeight) -> {
                    for (Pone p : stock) {
                        p.notifySceneHeight((Double) oldSceneHeight, (Double) newSceneHeight);
                    }
                });
    }
    
    public void init(Double width, Double height) {
        this.stock = fillPoneStock(width, height);
        
        for (Pone p : stock) {
            this.getChildren().add(p.getPoneShape());
            p.init(width, height);
        }
        
        setSizeListeners();
    }
    
    private ObservableList fillPoneStock(Double width, Double height) {
        Double poneRadiusX = width/24;
        Double poneStockTranslationX;
        if(leftSide) poneStockTranslationX = (Double)0d;
        else         poneStockTranslationX = width-(poneRadiusX*4);
        
        ObservableList<Pone> pones = FXCollections.observableArrayList();
        // left pone collumn
        for(int i = 0; i < NBPONE/2; ++i) {
            Pone p = new Pone(play, gameGrid, gamePane, 
                    (Double)((poneStockTranslationX+poneRadiusX)/width) , 
                    (Double)(((poneRadiusX)+((poneRadiusX*2)*i))/height) );
            colorPone(p);
            pones.add(p);
        }
        // right pone collumn
        for(int i = 0; i <= NBPONE/2; ++i) {
            Pone p = new Pone(play, gameGrid, gamePane, 
                    (Double)((poneStockTranslationX+(poneRadiusX*3))/width) , 
                    (Double)((poneRadiusX+((poneRadiusX*2)*i))/height) );
            colorPone(p);
            pones.add(p);
        }
        return pones;
    }
    
    public void colorPone(Pone p) {
        if(leftSide) {
            p.getPoneShape().getStyleClass().add("right-stock");
        } else {
            p.getPoneShape().getStyleClass().add("left-stock");
        }
    }
    
    // Retourne la forme du premier pion
    public Shape getPoneShape() {
        return stock.get(0).getPoneShape();
    }
    
    public List<Shape> getPoneShapes() {
        List<Shape> shapes = new ArrayList<>();
        for(Pone p : stock)
            shapes.add(p.getPoneShape());
        return shapes;
    }
    
    public List<Pone> getPones() {
        List<Pone> pones = new ArrayList<>();
        for(Pone p : stock)
            pones.add(p);
        return pones;
    }
    
    // Retourne le stock de pions
    public ObservableList<Pone> getStock() {
        return stock;
    }

    public void update() {
        for(Pone p : stock) {
            p.update();
        }
        if(autoMoveRequested)
            autoMove(autoMoveRequestedCol);
    }

    public void cleanGameGrid() {
        for(Pone pone : stock) {
            pone.cleanGameGrid();
            pone.enableForGame();
        }
    }
    
    public boolean allPoneHome() {
        for(Pone pone : stock)
            if(!pone.isHome())
                return false;
        return true;
    }
    
    public void enable() {
        for(Pone p : stock)
            p.enable();
    }

    public void disable() {
        for(Pone p : stock)
            p.disable();
    }
    
    public void autoMove(int column) {
        if(!autoMoveRequested) {
            autoMoveRequested = true;
            autoMoveRequestedCol = column;
            autoMoveRequestedCountDown = 50;
        }
        else if (autoMoveRequestedCountDown > 0) {
            --autoMoveRequestedCountDown;
        }
        else {
            autoMoveRequested = false;
            internalAutoMove(column);
        }
    }
    
    private void internalAutoMove(int column) {
        for(int i = 0; i < stock.size(); ++i) {
            if(!stock.get(i).isDisableForGame()) {
                stock.get(i).autoMove(column);
                break;
            }
        }
    }

    public boolean isMoving() {
        for(Pone p : stock)
            if(p.isMoving())
                return true;
        return false;
    }

}
