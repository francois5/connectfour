/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puissance4;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Instru;
import vue.GameMenu;
import vue.GamePane;

/**
 *
 * @author seb
 */
public class GameStage extends Stage {
    private final BorderPane root = new BorderPane();
    private final GamePane gamePane = new GamePane();
    private final Instru instru = new Instru();
    private final GameMenu gameMenu = new GameMenu(instru);
    
    public GameStage() {
        root.setCenter(gamePane);
        root.setTop(gameMenu);
        // todo: put pone stocks on the sides
        
        Scene scene = new Scene(root, 800, 800);
        
        MyGameLoop loop = new MyGameLoop(gamePane);
        loop.start();
        
        gamePane.init();
        
        this.setTitle("Puissance 4 - Game");
        this.setScene(scene);
        this.show();
    }
}
