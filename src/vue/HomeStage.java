/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author 2311sedoore
 */
public class HomeStage extends Stage {
    private StackPane stackPane;
    private int width = 600;
    private int height = 480;
    private Scene scene;
    
    
    public HomeStage() {
        this.stackPane = new StackPane();
        scene = new Scene(stackPane, 600,480);
        this.setTitle("Home");
        this.setScene(scene);
        this.show();
    }
}
