package vue;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
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
    private final Menu difficulty;
    private final Menu styleMenu;
    private final MenuItem newGame;
    private final MenuItem exit;
    private final MenuItem basicTheme;
    private final MenuItem darkTheme;
    private final ToggleGroup tGroup;
    private final RadioMenuItem easy;
    private final RadioMenuItem normal;
    private final RadioMenuItem difficult;
    private final CheckMenuItem on;
    
    private Scene scene;
    private GamePane gamePane;
    private GameStage gameStage;
    private Sound sound = SoundFactory.getSound();
    
    public GameMenu(GamePane gamePane, Scene scene, 
            GameStage gameStage) {
        this.scene = scene;
        this.gamePane = gamePane;
        this.gameStage = gameStage;
        
        // barre de menu
        menuBar = new MenuBar();
        
        menuBar.prefWidthProperty().bind(scene.widthProperty());
        
        // menus
        gameMenu = new Menu("Game");
        soundMenu = new Menu("Sound");
        difficulty = new Menu("Difficulty");
        styleMenu = new Menu("Appearance");
        
        // Ajout des menus dans la barre
        menuBar.getMenus().add(gameMenu);
        menuBar.getMenus().add(soundMenu);
        menuBar.getMenus().add(styleMenu);
        
        // Ajout de la barre de menu dans le Pane
        super.getChildren().add(menuBar);
        
        // création des menu items
        newGame = new MenuItem("New Game");
        exit = new MenuItem("Exit");
        basicTheme = new MenuItem("Basic");
        darkTheme = new MenuItem("Dark");
        
        // Création d'un toggle pour choix unique de la difficulty
        tGroup = new ToggleGroup();
        
        easy = new RadioMenuItem("Easy");
        easy.setToggleGroup(tGroup);
        normal = new RadioMenuItem("Normal");
        normal.setToggleGroup(tGroup);
        normal.setSelected(true);
        difficult = new RadioMenuItem("Difficult");
        difficult.setToggleGroup(tGroup);
        on = new CheckMenuItem("On");
        on.setSelected(true);
        
        difficulty.getItems().addAll(easy, normal, difficult);
        gameMenu.getItems().addAll(newGame, difficulty, 
                new SeparatorMenuItem(), exit);
        soundMenu.getItems().addAll(on);
        styleMenu.getItems().addAll(darkTheme, basicTheme);
        
        
        cssStyle("/basictheme.css");
        addKeyListeners();
        
    }
    
    private void addKeyListeners() {
        exit.setOnAction(e -> {
            Platform.exit();
        });
        
        newGame.setOnAction(e -> {
            this.gameStage.newGame();
        });
        
        basicTheme.setOnAction(e -> {
            cssStyle("/basictheme.css");
        });
        
        darkTheme.setOnAction(e ->{
            cssStyle("/darktheme.css");
        });
        
        on.setOnAction(e -> {
            if(sound.getIsOn()) {
                sound.setOff();
            }
            else {
                sound.setOn();
            }
        });
    }
    
    private void cssStyle (String theme) {
        // Note - CSS file has to be in src dir
        String css = GameMenu.class.getResource(theme).toExternalForm();
                
        scene.getStylesheets().clear();
        scene.getStylesheets().add(css);
    }
}
