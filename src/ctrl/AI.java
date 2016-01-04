/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ctrl;

import model.PoneStock;

/**
 *
 * @author localwsp
 */
public class AI {
    
    private GameCtrl gameCtrl;

    public AI(GameCtrl gameCtrl) {
        this.gameCtrl = gameCtrl;
    }
    
    public void play(String color, PoneStock poneStock) {
        for(int co = 0; co < 7; ++co) {
            if(gameCtrl.legalMove(co)) {
                poneStock.autoMove(co);
                break;
            }
        }
    }
    
}
