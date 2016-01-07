package model;

import vue.widgets.GameStage;

/**
 *
 * @author seb
 */
public class Part {
    private Player[] players = new Player[2];
    
    public Part(GameStage gameStage, Player player1, Player player2) {
        players[0] = player1;
        players[1] = player2;
    }

    public Player getPlayerOne() {
        return players[1];
    }
    
    public Player getPlayerTwo() {
        return players[0];
    }

}
