package vue;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Shape;
import model.GameGrid;
import model.Instru;
import ctrl.GameCtrl;
import model.Pone;
import model.PoneStock;
import puissance4.GameStage;

/**
 *
 * @author localwsp
 */
public class GamePane extends BorderPane {
    private GameCtrl play;
    private GameGrid gameGrid = new GameGrid(600,600, this);
    private PoneStock rightPoneStock;
    private PoneStock leftPoneStock;
    private GameStage gameStage;
    
    private final Instru instru = new Instru();
    
    public GamePane(GameCtrl play) {
        super();
        this.play = play;
        rightPoneStock = new PoneStock(play, gameGrid, this, false);
        leftPoneStock = new PoneStock(play, gameGrid, this, true); 
    }
    
    public GameGrid getGameGrid() {
        return gameGrid;
    }

    public void init(GameStage gameStage, Double width, Double height) {
        this.gameStage = gameStage;
        gameGrid.init(width, height);
        this.setCenter(gameGrid);
        this.setRight(rightPoneStock);
        rightPoneStock.init(width, height);
        this.setLeft(leftPoneStock);
        leftPoneStock.init(width, height);
        this.play.init(gameStage, rightPoneStock, leftPoneStock);
        
        gameGrid.initFrontImage();
    }
    
    @Override
    protected void layoutChildren() { }

    // return true if pones are not moving
    public boolean update() {
        rightPoneStock.update();
        leftPoneStock.update();
        if(leftPoneStock.allPoneHome() && leftPoneStock.allPoneHome()) {
            this.closeGrid();
            gameStage.startNewPart();
        }
        if(leftPoneStock.isMoving() || leftPoneStock.isMoving())
            return false;
        return true;
    }

    public List<Shape> getPoneShapes() {
        List<Shape> l = new ArrayList<Shape>();
        l.addAll(rightPoneStock.getPoneShapes());
        l.addAll(leftPoneStock.getPoneShapes());
        return l;
    }
    
    public List<Pone> getPones() {
        List<Pone> l = new ArrayList<Pone>();
        l.addAll(rightPoneStock.getPones());
        l.addAll(leftPoneStock.getPones());
        return l;
    }

    public Instru getInstru() {
        return instru;
    }

    public void openGrid() {
        this.gameGrid.open();
    }

    public void closeGrid() {
        this.gameGrid.close();
    }

    public void cleanGameGrid() {
        this.leftPoneStock.cleanGameGrid();
        this.rightPoneStock.cleanGameGrid();
    }
}
