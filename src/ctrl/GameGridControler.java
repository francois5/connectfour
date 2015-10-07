/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import model.GameGrid;

/**
 *
 * @author seb
 */
public class GameGridControler implements GraphicControler{
    private GameGrid gameGrid;
    
    public GameGridControler(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    @Override
    public void paintOn(GraphicsContext gc) {
        gameGrid.paintOn(gc);
    }

    @Override
    public Double getX() {
        return gameGrid.getX();
    }

    @Override
    public Double getY() {
        return gameGrid.getY();
    }

    @Override
    public void moveTo(Double x, Double y, Scene scene) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isOn(Double x, Double y) {
        return gameGrid.isOn(x, y);
    }
}
