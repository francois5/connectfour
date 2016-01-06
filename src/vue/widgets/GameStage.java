package vue.widgets;

import ctrl.MyGameLoop;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Part;
import ctrl.GameCtrl;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.WindowEvent;
import model.Player;

/**
 *
 * @author seb
 */
public class GameStage extends Stage {
    private final BorderPane root = new BorderPane();
    private final GamePane gamePane;
    private final GameMenu gameMenu;
    private GameCtrl play = new GameCtrl();
    private ScorePane scorePane = new ScorePane(play);
    private final Scene scene = new Scene(root, 900, 800);
    private Part currentPart;
    private final FooterPane footerPane;
    private boolean newPartToStart = true;
    private VBox vBox = new VBox();
    private String gameMode;
    
    public GameStage() {
        this.setTitle("Puissance 4 - Game");
        this.setMaxWidth(1200);
        this.setMinHeight(200);
        
        gamePane = new GamePane(play);
        gameMenu = new GameMenu(scene, this, gamePane);
        footerPane = new FooterPane(scene);
        
        vBox.getChildren().addAll(gameMenu, scorePane);
        root.setTop(vBox);
        //root.setCenter(gamePane);
        //root.setBottom(footerPane);
        
        MyGameLoop loop = new MyGameLoop(gamePane);
        loop.start();
        
        gamePane.init(this, scene.getWidth(), scene.getHeight());
        
        // Change l'icône
        this.getIcons().add(new Image("icon.png"));
        
        this.setScene(scene);
        this.show();
        gameMenu.displayMainMenu();
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
    
    public void setCenterRoot(Pane pane) {
        this.root.setCenter(pane);
    }
    
    public void setBottomRoot(Pane pane) {
        this.root.setBottom(pane);
    }
    
    public void openGrid() {
        this.gamePane.openGrid();
    }
    
    public void closeGrid() {
        this.gamePane.closeGrid();
    }

    public void newGame() {
        if(this.currentPart == null)
            startNewPart();
        else {
            cleanGameGrid();
        }
        this.show();
        play.newGame();
    }

    public void startNewPart() {
        if(newPartToStart) {
            if(gameMode == "2 player")
                this.currentPart = new Part(this, new Player("sebastien", false), 
                        new Player("françois", false));
            else
                this.currentPart = new Part(this, new Player("sebastien", false), 
                        new Player("computer", true));
            this.play.setCurrentPart(currentPart);
            this.setCenterRoot(gamePane);
            this.setBottomRoot(this.footerPane);
            this.footerPane.anime();
            newPartToStart = false;
        }
    }

    private void cleanGameGrid() {
        openGrid();
        gamePane.cleanGameGrid();
        newPartToStart = true;
    }

    public void winMessage(int currentPlayer) {
        if(currentPlayer == 2)
            showPopupMessage("Red wins", this, "red");
        else
            showPopupMessage("Yellow wins", this, "yellow");
    }

    public void drawMessage() {
        showPopupMessage("Draw", this, "draw");
    }
    
    public Popup createPopup(final String message, final String color) {
        final Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.setHideOnEscape(true);
        Label label = new Label(message);
        label.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                popup.hide();
                gameMenu.displayMainMenu();
            }
        });
        if(color.equals("draw"))
            label.setTextFill(Color.WHITE);
        else if(color.equals("red"))
            label.setTextFill(Color.RED);
        else if(color.equals("yellow"))
            label.setTextFill(Color.YELLOW);
        label.getStylesheets().add("/popup.css");
        label.getStyleClass().add("popup");
        popup.getContent().add(label);
        return popup;
    }

    public void showPopupMessage(final String message, final Stage stage, final String color) {
        final Popup popup = createPopup(message, color);
        popup.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                popup.setX(stage.getX() + stage.getWidth() / 2 - popup.getWidth() / 2);
                popup.setY(stage.getY() + stage.getHeight() / 2 - popup.getHeight() / 2);
            }
        });
        popup.show(stage);
    }
}
