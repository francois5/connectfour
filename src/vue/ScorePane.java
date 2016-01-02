/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import model.Play;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author seb
 */
// ScorePane est un Observer de GamePane
// Normalement un Observer observe une classe métier
// Mais dans notre cas les données métier sont dans les classes
// GamePane, GameGrid, Pone et PoneStock !
public class ScorePane extends FlowPane implements Observer{
    private Observable observable;
    private Image image = new Image("author.png");
    private ImageView imageView = new ImageView(image);
    private Label lbRed = new Label("Red");
    private Label lbYel= new Label("Yel");
    
    public ScorePane(Observable observable) {
        observable.addObserver(this);
        this.observable = observable;
        this.getChildren().add(lbRed);
        this.getChildren().add(imageView);
        this.getChildren().add(lbYel);
        colorLabels();
        center();
        colorBackground();
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Play) {
            Play play = (Play) o;
            lbRed.setText(play.getNbHit(Play.RED_PLAYER));
            lbYel.setText(play.getNbHit(Play.YELLOW_PLAYER));
        }
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
