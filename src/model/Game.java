/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author seb
 */
public class Game {
    public final static int VIDE = 0;
    public final static int RED = 1;
    public final static int YELLOW = 2;
    
    private int [][] grid; // 0 = vide, 1 red player, 2 = yellow player
    private int gridWidth;
    private int gridHeight;
    
    public Game() {
        initGame(7,6);
    }
    
    private void initGame(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        
        for(int col = 0; col < gridHeight; ++col) {
            for(int row = 0; row < gridWidth; ++row) {
                grid[row][col] = VIDE;
                // test
                System.out.println("X");
            }
        }
    }
}
