package vue.widgets;

import ctrl.GameCtrl;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import model.GameGrid;

/**
 *
 * @author seb
 */
// ScorePane est un Observer de GamePane
// Normalement un Observer observe une classe métier
// Mais dans notre cas les données métier sont dans les classes
// GamePane, GameGrid, Pone et PoneStock !
public class ScorePane extends FlowPane implements Observer {
    private GameCtrl gameCtrl;
    private Image image = new Image("/author.png");
    private ImageView imageView = new ImageView(image);
    private AnimLabel lbRed = new AnimLabel("Red");
    private AnimLabel lbYel= new AnimLabel("Yel");
    
    public ScorePane(GameCtrl gameCtrl) {
        gameCtrl.getGameGrid().addObserver(this);
        this.gameCtrl = gameCtrl;
        addLabels();
        colorLabels();
        center();
        colorBackground();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof GameGrid) {
            GameGrid gameGrid = (GameGrid) o;
            
            if(gameCtrl.getCurrentPlayer()== 1) {
                lbRed.setText(gameCtrl.getNbHit(GameCtrl.RED_PLAYER));
                lbRed.getBlinkThenFade().play();
            }
            else if(gameCtrl.getCurrentPlayer()== 2) {
                lbYel.setText(gameCtrl.getNbHit(GameCtrl.YELLOW_PLAYER));
                lbYel.getBlinkThenFade().play();
            }
        }
    }
    
    private void addLabels() {
        this.getChildren().add(lbRed);
        this.getChildren().add(imageView);
        this.getChildren().add(lbYel);
        
    }
    
    public void colorLabels() {
        lbRed.getStyleClass().add("scorePane-lbRed");
        lbYel.getStyleClass().add("scorePane-lbYel");
    }
    
    public void color() {
        this.getStyleClass().add("scorePane");
    }
    
    public void center() {
        this.setAlignment(Pos.CENTER);
    }
    
    public void colorBackground() {
        this.setStyle("-fx-background-color: #336699;");
    }
}
