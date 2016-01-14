package ctrl;

import javafx.animation.AnimationTimer;
import vue.widgets.GamePane;

/**
 *
 * @author Francois
 */
public class MyGameLoop extends AnimationTimer {
    
    private GamePane gamePane;
    private GameCtrl gameCtrl;

    public MyGameLoop(GamePane gamePane, GameCtrl gameCtrl) {
        this.gamePane = gamePane;
        this.gameCtrl = gameCtrl;
    }

    @Override
    public void handle(long now) {
        gamePane.update();
        gameCtrl.update();
    }
    
}
