/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import vue.GameMenu;
import vue.GamePane;

/**
 *
 * @author seb
 */
public class GameStage extends Stage {
    private final BorderPane root = new BorderPane();
    private final GamePane gamePane = new GamePane();
    private final GameMenu gameMenu;
    private final Scene scene = new Scene(root, 900, 800);
    
    
    public GameStage() {
        this.setTitle("Puissance 4 - Game");
        this.setMaxWidth(1200);
        this.setMinHeight(200);
        gameMenu = new GameMenu(gamePane, scene, this);
        
        root.setTop(gameMenu);
        //root.setCenter(gamePane);
        //root.setBottom(footerPane);
        
        
        
        MyGameLoop loop = new MyGameLoop(gamePane);
        loop.start();
        
        gamePane.init(scene.getWidth(), scene.getHeight());
        
        // Change l'icône
        this.getIcons().add(new Image("icon.png"));
        
        this.setScene(scene);
        this.show();
    }
    
    public void setCenterRoot(Pane pane) {
        this.root.setCenter(pane);
    }
    
    public void setBottomRoot(Pane pane) {
        this.root.setBottom(pane);
    }
}
