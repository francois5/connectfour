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
import model.Instru;
import vue.FooterPane;
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
    private final Scene scene = new Scene(root, 800, 800);;
    
    
    public GameStage() {
        this.setTitle("Puissance 4 - Game");
        
        gameMenu = new GameMenu(gamePane, scene, this);
        
        root.setTop(gameMenu);
        //root.setCenter(gamePane);
        //root.setBottom(footerPane);
        
        // Note - CSS file has to be in src dir
        String css = GameStage.class.getResource("/darktheme.css").toExternalForm();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(css);
        
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
