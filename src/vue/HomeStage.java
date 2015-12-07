/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import puissance4.GameStage;

/**
 *
 * @author 2311sedoore
 */
public class HomeStage extends Stage {
    private final VBox vBox = new VBox();
    private static final int WIDTH = 270;
    private static final int HEIGHT = 180;
    private Scene scene = new Scene(vBox, WIDTH, HEIGHT);
    private final Button[] buttons = new Button[3];
    private static final String[] buttonNames = {"New Game", "Continue", "Quit"};
    private final GameStage gameStage = new GameStage();
    
    
    public HomeStage() {
        this.initStyle(StageStyle.UNDECORATED);
        this.setResizable(false);
        
        addButtons();
        designVbox();
        setActionListeners();
        this.setTitle("Home");
        this.setScene(scene);
        this.show();
    }
    
    private void addButtons() {
        for(int i = 0; i < buttons.length; ++i) {
            Button cur = buttons[i] = new Button(buttonNames[i]);
            cur.prefWidthProperty().bind(scene.widthProperty());
            cur.prefHeightProperty().bind(scene.heightProperty().divide(3));
            vBox.getChildren().add(cur);
        }
    }
    
    private void designVbox() {
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setSpacing(10);
    }
    
    private void setActionListeners() {
        
        buttons[0].setOnAction(e -> {
            this.gameStage.newGame();
            this.close();
        });
        
        buttons[2].setOnAction(e -> {
            Platform.exit();
        });
    }
}
