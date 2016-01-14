package ctrl;

import model.Part;
import model.GameGrid;
import vue.drawings.PoneStock;
import vue.widgets.GameStage;

/**
 *
 * @author seb
 */
public class GameCtrl {
    public static int RED_PLAYER = 1;
    public static int YELLOW_PLAYER = 2;
    private PoneStock rightPoneStock; 
    private PoneStock leftPoneStock;
    private GameStage gameStage; 
    private Part currentPart;
    private AI ai;
    private boolean gameEnd = false;
    private boolean computerTurn = false;
    
    private final GameGrid gameGrid = new GameGrid();
    
    public GameCtrl() {
    }
    
    public void init(GameStage gameStage, 
            PoneStock rightPoneStock, PoneStock leftPoneStock) {
        this.rightPoneStock = rightPoneStock;
        this.leftPoneStock = leftPoneStock;
        this.gameStage = gameStage;
        this.ai = new AI(this);
        newGame();
    }

    public GameGrid getGameGrid() {
        return gameGrid;
    }

    public void setCurrentPart(Part currentPart) {
        this.currentPart = currentPart;
    }

    
    public boolean gridAddPone(int numCol) {
        // Si la case n'est pas vide
        if(!legalMove(numCol) || gameEnd)
            return false;
        gameGrid.getGrid()[gameGrid.getNumRows()[numCol]][numCol] = gameGrid.getCurrentPlayer();
        //printGrid();
        --gameGrid.getNumRows()[numCol];
        // On incrémente le nombre de coups du joueur courant
        ++gameGrid.getNbHit()[gameGrid.getCurrentPlayer()];
        
        // On notifie les observers
        gameGrid.dataChanged();
        // On passe au joueur suivant
        nextPlayer();
        
        if(ai.win(gameGrid.getGrid())) {
            gameStage.winMessage(gameGrid.getCurrentPlayer());
            gameEnd = true;
        }
        else if(checkDraw()) {
            gameStage.drawMessage();
            gameEnd = true;
        }
        if(computerTurn && !gameEnd) {
            if(gameGrid.getCurrentPlayer() == 1)
                ai.play(RED_PLAYER, leftPoneStock, gameGrid.getGrid(), true);
            else
                ai.play(YELLOW_PLAYER, rightPoneStock, gameGrid.getGrid(), true);
        }
            
        return true;
    }
    
    public boolean legalMove(int co) {
        return ai.legalMove(co, gameGrid.getGrid());
    }
    
    public boolean columnFull(int co) {
        return gameGrid.getNumRows()[co] == -1;
    }
    
    public void nextPlayer() {
        if(gameGrid.getCurrentPlayer() == 1) {
            gameGrid.setCurrentPlayer(2);
            leftPoneStock.disable();
            if(currentPart.getPlayerOne().isComputer()) {
                rightPoneStock.disable();
                computerTurn = true;
            }
            else {
                rightPoneStock.enable();
                computerTurn = false;
            }
        }
        else if(gameGrid.getCurrentPlayer() == 2) {
            gameGrid.setCurrentPlayer(1);
            rightPoneStock.disable();
            if(currentPart.getPlayerTwo().isComputer()) {
                leftPoneStock.disable();
                computerTurn = true;
            }
            else {
                leftPoneStock.enable();
                computerTurn = false;
            }
        }
    }
    
    public void printGrid(int[][] grid) {
        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j < grid[0].length; ++j) {
                System.out.print("|" + grid[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
        System.out.println("");
    }
    
    private void cleanGrid() {
        for(int i = 0; i < 6; ++i) {
            for(int y = 0; y < 7; ++y)
                gameGrid.getGrid()[i][y] = 0;
        }
    }

    public void newGame() {
        gameGrid.setCurrentPlayer(1);
        gameEnd = false;
        rightPoneStock.disable();
        leftPoneStock.enable();
        gameGrid.getNbHit()[1] = 0;
        gameGrid.getNbHit()[2] = 0;
        gameGrid.setNumRows(new int[] {5,5,5,5,5,5,5});
        cleanGrid();
        gameGrid.dataChanged();
    }

    private boolean checkDraw() {
        for(int co = 0; co < 7; ++co)
            if(gameGrid.getGrid()[0][co] == 0)
                return false;
        return true;
    }

    void update() {
        ai.update();
    }
    
}
