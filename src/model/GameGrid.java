package model;

/**
 *
 * @author francois
 */
public class GameGrid {
    private int[][] grid = new int[6][7];
    // index pour la position du dernier pion dans la colonne
    private int []numRows = new int[] {5,5,5,5,5,5,5};

    public int[][] getGrid() {
        return grid;
    }

    public int[] getNumRows() {
        return numRows;
    }

    public void setNumRows(int[] numRows) {
        this.numRows = numRows;
    }
    
}
