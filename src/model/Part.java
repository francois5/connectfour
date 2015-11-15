/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import puissance4.GameStage;
import vue.GamePane;

/**
 *
 * @author seb
 */
public class Part {
    private Player[] players = new Player[2];
    private final GameStage gameStage = new GameStage();
    
    public Part(Player player1, Player player2) {
        players[0] = player1;
        players[1] = player2;
    }
    
    public void play() {
        int winner = -1;
        int curPlayer = 0;
        
        while(winner == -1) {
            
            // A implémenter : Si 4 pions alignés, on a un winner
            // winner reçoit curPlayer
            
            // Changement de player pour l'itération suivante
            ++curPlayer;
            // Si curPlayer == 0, il passe à 1 % 2 == 1
            // Si curPlayer == 1, il passe à 2 % 2 == 0
            curPlayer %= 2; 
            
        }
        
    }
}
