/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author seb
 */
public class FooterPane extends Pane {
    private Scene scene;
    private Rectangle leftLeg;
    private Rectangle rightLeg;
    private Rotate leftRotate;
    private Rotate rightRotate;
    
    public FooterPane(Scene scene) {
        this.scene = scene;
        buildLegs();
        setResizable();
        legsTransform();
        animateLeftLeg();
        animateRightLeg();
    }
    
    private void buildLegs() {
        leftLeg = new Rectangle();
        rightLeg = new Rectangle();
        
        leftLeg.getStyleClass().add("leg");
        rightLeg.getStyleClass().add("leg");
        
        this.getChildren().addAll(leftLeg, rightLeg);
    }
    
    private void legsTransform() {
        // Pivot : startAngle à 0 degrés, position sur coin sup gauche de leftLeg
        leftRotate = new Rotate(0, leftLeg.getX(), leftLeg.getY());
        // Pivot: startAngle à 0 degrés, position sur coin sup droit de rightLeg
        rightRotate = new Rotate(0, rightLeg.getX() + rightLeg.getWidth(), 
                rightLeg.getY());
        
        leftLeg.getTransforms().add(leftRotate);
        rightLeg.getTransforms().add(rightRotate);
    }
    
    private void animateLeftLeg() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
            // target value pour 0 ms
            new KeyFrame(
                Duration.ZERO, new KeyValue(leftRotate.angleProperty(), 180)
            ),
            // target value pour 1000 ms    
            new KeyFrame(
                new Duration(1000), new KeyValue(leftRotate.angleProperty(), 0)
            )
        );
        
        timeline.play();
    }
    
    private void animateRightLeg() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
            // target value pour 0 ms
            new KeyFrame(
                Duration.ZERO, new KeyValue(rightRotate.angleProperty(), 180)
            ),
            // target value pour 1000 ms
            new KeyFrame(
                new Duration(1000), new KeyValue(rightRotate.angleProperty(), 0)
            )
        );
        
        timeline.play();
    }
    
    private void setResizable() {
        leftLeg.widthProperty().bind(scene.widthProperty().divide(12));
        leftLeg.heightProperty().bind(scene.heightProperty().divide(5));
        leftLeg.xProperty().bind(scene.widthProperty().divide(6));
        
        rightLeg.widthProperty().bind(scene.widthProperty().divide(12));
        rightLeg.heightProperty().bind(scene.heightProperty().divide(5));
        rightLeg.xProperty().bind(scene.widthProperty().divide(1.335));
    }
}
