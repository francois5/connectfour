package puissance4;

import javafx.animation.AnimationTimer;
import vue.GamePane;

/**
 *
 * @author Francois
 */
public class MyGameLoop extends AnimationTimer {
    
    private GamePane gamePane;

    MyGameLoop(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    @Override
    public void handle(long now) {
        gamePane.update();
    }
    
}
