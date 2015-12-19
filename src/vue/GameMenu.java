package vue;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import model.Sound;
import model.SoundFactory;
import puissance4.GameStage;

/**
 *
 * @author seb
 */
public class GameMenu extends Pane {
    private final MenuBar menuBar;
    private final Menu gameMenu;
    private final Menu soundMenu;
    private final Menu styleMenu;
    private final MenuItem game;
    private final MenuItem exit;
    private final MenuItem basicTheme;
    private final MenuItem darkTheme;
    private final CheckMenuItem on;
    
    private Scene scene;
    private GameStage gameStage;
    private Sound sound = SoundFactory.getSound();
    private HomeStage homeStage;
    
    public GameMenu(Scene scene, 
            GameStage gameStage) {
        this.scene = scene;
        this.gameStage = gameStage;
        this.homeStage = new HomeStage(gameStage, this);
        
        // barre de menu
        menuBar = new MenuBar();
        
        menuBar.prefWidthProperty().bind(scene.widthProperty());
        
        // menus
        gameMenu = new Menu("Menu");
        soundMenu = new Menu("Sound");
        //difficulty = new Menu("Difficulty");
        styleMenu = new Menu("Appearance");
        
        // Ajout des menus dans la barre
        menuBar.getMenus().add(gameMenu);
        menuBar.getMenus().add(soundMenu);
        menuBar.getMenus().add(styleMenu);
        
        // Ajout de la barre de menu dans le Pane
        super.getChildren().add(menuBar);
        
        // création des menu items
        game = new MenuItem("Game");
        exit = new MenuItem("Exit");
        basicTheme = new MenuItem("Basic");
        darkTheme = new MenuItem("Dark");
        
        
        // Création d'un toggle pour choix unique de la difficulty
        // tGroup = new ToggleGroup();
        
        //easy = new RadioMenuItem("Easy");
        //easy.setToggleGroup(tGroup);
        //normal = new RadioMenuItem("Normal");
        //normal.setToggleGroup(tGroup);
        //normal.setSelected(true);
        //difficult = new RadioMenuItem("Difficult");
        //difficult.setToggleGroup(tGroup);
        on = new CheckMenuItem("On");
        on.setSelected(true);
        
        //difficulty.getItems().addAll(easy, normal, difficult);
        gameMenu.getItems().addAll(game, new SeparatorMenuItem(), exit);
        
        
        soundMenu.getItems().addAll(on);
        styleMenu.getItems().addAll(darkTheme, basicTheme);
        
        
        gameMenuCss("/gamemenu.css");
        addKeyListeners();
        
    }
    
    private void addKeyListeners() {
        exit.setOnAction(e -> {
            Platform.exit();
        });
        
        basicTheme.setOnAction(e -> {
            gameMenuCss("/basictheme.css");
        });
        
        darkTheme.setOnAction(e ->{
            gameMenuCss("/darktheme.css");
        });
        
        on.setOnAction(e -> {
            if(sound.getIsOn()) {
                sound.setOff();
            }
            else {
                sound.setOn();
            }
        });
        
        game.setOnAction(e -> {
            // 2 étapes pour bloquer les autres fenêtre
            // 1 : initOwner () reçoit la fenêtre à bloquer
            homeStage.initOwner(gameStage);
            // 2 : initModality à WINDOW_MODAL
            homeStage.initModality(Modality.WINDOW_MODAL);
            darken();
            homeStage.display();
            
        });
    }
    
    public void gameMenuCss(String theme) {
        // Note - CSS file has to be in src dir
        String css = GameMenu.class.getResource(theme).toExternalForm();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(css);
    }
    
    private void darken() {
        gameMenuCss("/darken.css");
    }
}
