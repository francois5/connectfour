package vue.widgets;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import vue.drawings.GameGrid;
import ctrl.Sound;
import ctrl.SoundFactory;

/**
 *
 * @author seb
 */
public class GameMenu extends Pane {
    private final MenuBar menuBar;
    //private final Label gameMenuLabel;
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
    private GamePane gamePane;
    private Sound sound = SoundFactory.getSound();
    private HomeStage homeStage;
    private boolean isDarkTheme;
    
    public GameMenu(Scene scene, GameStage gameStage, GamePane gamePane) {
        this.scene = scene;
        this.gameStage = gameStage;
        this.gamePane = gamePane;
        
        // barre de menu
        menuBar = new MenuBar();
        
        menuBar.prefWidthProperty().bind(scene.widthProperty());
        
        // menus
        //this.gameMenuLabel = new Label("Menu");
        
        
        gameMenu = new Menu("_Menu");
        //gameMenu.setGraphic(gameMenuLabel);
        gameMenu.setMnemonicParsing(true);
        
        
        soundMenu = new Menu("_Sound");
        soundMenu.setMnemonicParsing(true);
        
        styleMenu = new Menu("_Appearance");
        styleMenu.setMnemonicParsing(true);
        
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
        
        on = new CheckMenuItem("On");
        on.setSelected(true);
        
        
        gameMenu.getItems().addAll(game, new SeparatorMenuItem(), exit);
        
        soundMenu.getItems().addAll(on);
        styleMenu.getItems().addAll(darkTheme, basicTheme);
        
        gameMenuCss("/gamemenu.css");
        addKeyListeners();
    }
    
    private void setIsDarkTheme(boolean bool) {
        this.isDarkTheme = bool;
    }
    
    public boolean isDarkTheme() {
        return isDarkTheme;
    }
    
    private void addKeyListeners() {
        /*exit.setOnAction(e -> {
            Platform.exit();
        });*/
        
        basicTheme.setOnAction(e -> {
            gameMenuCss("/basictheme.css");
        });
        
        darkTheme.setOnAction(e ->{
            gameMenuCss("/darktheme.css");
            setIsDarkTheme(true);
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
            displayMainMenu();
        });
    }
    
    public void displayMainMenu() {
        this.homeStage = new HomeStage(gameStage, this);
        // 2 étapes pour bloquer les autres fenêtre
        // 1 : initOwner () reçoit la fenêtre à bloquer
        homeStage.initOwner(gameStage);
        // 2 : initModality à WINDOW_MODAL
        homeStage.initModality(Modality.WINDOW_MODAL);

        darken();
        homeStage.display();
    }
    
    public void gameMenuCss(String theme) {
        // Note - CSS file has to be in src dir
        String css = GameMenu.class.getResource(theme).toExternalForm();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(css);
    }
    
    private void darken() {
        gameMenuCss("/darken.css");
        setGridImage("darkgrid.png");
    }
    
    public void setGridImage(String name) {
        GameGrid gameGrid = gamePane.getGameGrid();
        ImageView blueGrid = gameGrid.getImage();
        blueGrid.setImage(new Image(name));
    }
}
