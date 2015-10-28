/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author seb
 */
public class FooterPane extends Pane {
    private Scene scene;
    private Rectangle leftLeg;
    private Rectangle rightLeg;
    
    public FooterPane(Scene scene) {
        this.scene = scene;
        buildLegs();
        setResizable();
    }
    
    private void buildLegs() {
        leftLeg = new Rectangle();
        rightLeg = new Rectangle();
        
        leftLeg.getStyleClass().add("leg");
        rightLeg.getStyleClass().add("leg");
        
        this.getChildren().addAll(leftLeg, rightLeg);
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
