/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import java.util.List;
import java.util.Observable;
import model.Part;
import model.PoneStock;
import puissance4.GameStage;

/**
 *
 * @author seb
 */
public class GameCtrl extends Observable {
    public static int RED_PLAYER = 1;
    public static int YELLOW_PLAYER = 2;
    private PoneStock rightPoneStock; 
    private PoneStock leftPoneStock;
    private GameStage gameStage;
    private Part currentPart; 
    private AI ai;
    
    private int[][] grid = new int[6][7];
    // index pour la position du dernier pion dans la colonne
    private int []numRows = new int[] {5,5,5,5,5,5,5};
    // taille pour les tableaux internes
    private int rowSize = 7;
    
    // numéro du joueur courant
    private int currentPlayer;
    public int[] nbHit = new int[3];
    public int[] nbWin = new int[3];
    
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

    public void setCurrentPart(Part currentPart) {
        this.currentPart = currentPart;
    }
    
    public void dataChanged() {
        setChanged();
        notifyObservers();
    }
    
    public boolean gridAddPone(int numCol) {
        // Si la case n'est pas vide
        if(!legalMove(numCol))
            return false;
        grid[numRows[numCol]][numCol] = currentPlayer;
        printGrid();
        --numRows[numCol];
        // On incrémente le nombre de coups du joueur courant
        ++nbHit[currentPlayer];
        // On passe au joueur suivant
        nextPlayer();
        dataChanged();
        checkWin();
        return true;
    }
    
    public boolean legalMove(int numCol) {
        return !(numCol >= numRows.length || numRows[numCol] >= grid.length || numRows[numCol] < 0 ||
              numCol >= grid[numRows[numCol]].length || grid[numRows[numCol]][numCol] != 0);
    }
    
    private void checkWin() {
        if(checkWinColumn() || checkWinLine() || checkWinDiagonal()) {
            gameStage.winMessage(currentPlayer);
        }
    }
    
    private boolean checkWinLine() {
        int countSameColor = 0;
        int curColor = 0;
        int precColor = 0;
        for(int li = 0; li < grid.length; ++li) {
            countSameColor = 0;
            precColor = 0;
            for(int co = 0; co < grid[0].length; ++co) {
                curColor = grid[li][co];
                if(curColor != 0) {
                    if(curColor == precColor)
                        ++countSameColor;
                    else
                        countSameColor = 0;
                    if(countSameColor == 3)
                        return true;
                }
                precColor = curColor;
            }
        }
        return false;
    }
    
    private boolean checkWinColumn() {
        int countSameColor = 0;
        int curColor = 0;
        int precColor = 0;
        for(int co = 0; co < grid[0].length; ++co) {
            countSameColor = 0;
            precColor = 0;
            for(int li = 0; li < grid.length; ++li) {
                curColor = grid[li][co];
                if(curColor != 0) {
                    if(curColor == precColor)
                        ++countSameColor;
                    else
                        countSameColor = 0;
                    if(countSameColor == 3)
                        return true;
                }
                precColor = curColor;
            }
        }
        return false;
    }
    
    private boolean checkWinDiagonal() {
        return (checkWinDiagonalRight() || checkWinDiagonalLeft());
    }
    
    private boolean checkWinDiagonalRight() {
        for (int i = 0; i < 2; ++i) {
            int countSameColor = 0;
            int curColor = 0;
            int precColor = 0;
            for (int co = 0; co < grid[0].length; ++co) {
                countSameColor = 0;
                precColor = 0;
                for (int li = grid.length-1; li >= 0 ; --li) {
                    if(i == 0) {
                        if((co + li) < grid[0].length)
                            curColor = grid[li][co + li];
                        else
                            curColor = 0;
                    }
                    else {
                        if((co - li) >= 0)
                            curColor = grid[li][co - li];
                        else
                            curColor = 0;
                    }
                    if (curColor != 0) {
                        if (curColor == precColor) {
                            ++countSameColor;
                        } else {
                            countSameColor = 0;
                        }
                        if (countSameColor == 3) {
                            return true;
                        }
                    }
                    precColor = curColor;
                }
            }
        }
        return false;
    }
    
    private boolean checkWinDiagonalLeft() {
        for (int i = 0; i < 2; ++i) {
            int countSameColor = 0;
            int curColor = 0;
            int precColor = 0;
            for (int co = 0; co < grid[0].length; ++co) {
                countSameColor = 0;
                precColor = 0;
                for (int li = 0; li < grid.length; ++li) {
                    if(i == 0) {
                        if((co + li) < grid[0].length)
                            curColor = grid[li][co + li];
                        else
                            curColor = 0;
                    }
                    else {
                        if((co - li) >= 0)
                            curColor = grid[li][co - li];
                        else
                            curColor = 0;
                    }
                    if (curColor != 0) {
                        if (curColor == precColor) {
                            ++countSameColor;
                        } else {
                            countSameColor = 0;
                        }
                        if (countSameColor == 3) {
                            return true;
                        }
                    }
                    precColor = curColor;
                }
            }
        }
        return false;
    }
    
    public boolean columnFull(int co) {
        return numRows[co] == -1;
    }
    
    public void nextPlayer() {
        if(currentPlayer == 1) {
            ++ currentPlayer;
            leftPoneStock.disable();
            if(currentPart.getPlayerOne().isComputer()) {
                rightPoneStock.disable();
                ai.play("yellow", rightPoneStock);
            }
            else
                rightPoneStock.enable();
        }
        else if(currentPlayer == 2) {
            --currentPlayer;
            rightPoneStock.disable();
            if(currentPart.getPlayerTwo().isComputer()) {
                leftPoneStock.disable();
                ai.play("red", leftPoneStock);
            }
            else
                leftPoneStock.enable();
        }
    }
    
    public void printGrid() {
        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j < rowSize; ++j) {
                System.out.print("|" + grid[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
        System.out.println("");
    }
    
    public int getCurrentPlayer() {
        return currentPlayer;
    }
    
    public String getNbHit(int player) {
        return nbHit[player]+"";
    }
    
    public String getWin(int player) {
        return nbWin[player]+"";
    }
    
    private void cleanGrid() {
        for(int i = 0; i < grid.length; ++i) {
            for(int y = 0; y < grid[0].length; ++y)
                grid[i][y] = 0;
        }
    }

    public void newGame() {
        currentPlayer = 1;
        rightPoneStock.disable();
        leftPoneStock.enable();
        nbHit[1] = 0;
        nbHit[2] = 0;
        numRows = new int[] {5,5,5,5,5,5,5};
        cleanGrid();
        dataChanged();
    }

}