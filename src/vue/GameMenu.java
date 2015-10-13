package vue;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import model.Instru;

/**
 *
 * @author seb
 */
public class GameMenu extends Pane {
    private final MenuBar menuBar;
    private final Menu gameMenu;
    private final Menu soundMenu;
    private final Menu difficulty;
    private final MenuItem newGame;
    private final MenuItem exit;
    private final ToggleGroup tGroup;
    private final RadioMenuItem easy;
    private final RadioMenuItem normal;
    private final RadioMenuItem difficult;
    private final CheckMenuItem on;
    private Instru instru;
    
    public GameMenu(Instru instru) {
        // barre de menu
        menuBar = new MenuBar();
        
        // menus
        gameMenu = new Menu("Game");
        soundMenu = new Menu("Sound");
        difficulty = new Menu("Difficulty");
        
        // Ajout des menus dans la barre
        menuBar.getMenus().add(gameMenu);
        menuBar.getMenus().add(soundMenu);
        
        // Ajout de la barre de menu dans le Pane
        super.getChildren().add(menuBar);
        
        // création des menu items
        newGame = new MenuItem("New Game");
        exit = new MenuItem("Exit");
        
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
        
        this.instru = instru;
        addKeyListeners();
        
    }
    
    private void addKeyListeners() {
        exit.setOnAction(e -> {
            Platform.exit();
        });
        
        menuBar.setOnMousePressed(e -> {
            instru.note_on(62);
        });
    }
}
