/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Observable;

/**
 *
 * @author seb
 */
public class Play extends Observable {
    public static int RED_PLAYER = 0;
    public static int YELLOW_PLAYER = 1;
    
    private int[][] grid = new int[6][7];
    // index pour la position du dernier pion dans la colonne
    private int []numRows = new int[] {5,5,5,5,5,5,5};
    // taille pour les tableaux internes
    private int rowSize = 7;
    
    // numéro du joueur courant
    private int currentPlayer;
    public int[] nbHit = new int[2];
    public int[] nbWin = new int[2];
    
    public Play() {
        // RED_PLAYER commence
        currentPlayer = 0;
    }
    
    public void dataChanged() {
        setChanged();
        notifyObservers();
    }
    
    public void gridAddPone(int numCol) {
        // Si la case n'est pas vide
        if(grid[numRows[numCol]][numCol] == 0) {
            grid[numRows[numCol]][numCol] = currentPlayer;
            printGrid();
            --numRows[numCol];
            // On incrémente le nombre de coups du joueur courant
            ++nbHit[currentPlayer];
            // On passe au joueur suivant
            nextPlayer();
            dataChanged();
        }
    }
    
    public void nextPlayer() {
        ++currentPlayer;
        currentPlayer %= 2;
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
}
