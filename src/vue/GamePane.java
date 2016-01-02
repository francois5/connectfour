package vue;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import model.GameGrid;
import model.Instru;
import model.Play;
import model.Pone;
import model.PoneStock;
import puissance4.GameStage;

/**
 *
 * @author localwsp
 */
public class GamePane extends BorderPane {
    private Play play;
    private GameGrid gameGrid = new GameGrid(600,600, this);
    private PoneStock rightPoneStock;
    private PoneStock leftPoneStock;
    private GameStage gameStage;
    
    private final Instru instru = new Instru();
    
    public GamePane(Play play) {
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

        gameGrid.initFrontImage();
    }
    
    @Override
    protected void layoutChildren() { }

    public void update() {
        rightPoneStock.update();
        leftPoneStock.update();
        if(leftPoneStock.allPoneHome() && leftPoneStock.allPoneHome()) {
            this.closeGrid();
            gameStage.startNewPart();
        }
    }

    public List<Shape> getPones() {
        List<Shape> l = new ArrayList<Shape>();
        l.addAll(rightPoneStock.getPoneShapes());
        l.addAll(leftPoneStock.getPoneShapes());
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
