package model;

import java.util.Observable;

/**
 *
 * @author francois
 */
public class GameGrid  extends Observable {
    private int[][] grid = new int[6][7];
    // index pour la position du dernier pion dans la colonne
    private int []numRows = new int[] {5,5,5,5,5,5,5};
    // numéro du joueur courant
    private int currentPlayer;
    private int[] nbHit = new int[3];
    private int[] nbWin = new int[3];

    public int[][] getGrid() {
        return grid;
    }

    public int[] getNumRows() {
        return numRows;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int[] getNbHit() {
        return nbHit;
    }

    public int[] getNbWin() {
        return nbWin;
    }
    
    public String getNbHit(int player) {
        return nbHit[player]+"";
    }
    
    public String getWin(int player) {
        return nbHit[player]+"";
    }

    public void setNumRows(int[] numRows) {
        this.numRows = numRows;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setNbHit(int[] nbHit) {
        this.nbHit = nbHit;
    }

    public void setNbWin(int[] nbWin) {
        this.nbWin = nbWin;
    }
    
    public void dataChanged() {
        setChanged();
        notifyObservers();
    }
    
}
