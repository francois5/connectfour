package ctrl;

import javafx.animation.AnimationTimer;
import vue.widgets.GamePane;

/**
 *
 * @author Francois
 */
public class MyGameLoop extends AnimationTimer {
    
    private GamePane gamePane;

    public MyGameLoop(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    @Override
    public void handle(long now) {
        gamePane.update();
    }
    
}
