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
public class SoundFactory {
    private static Sound sound = null;
    
    public static Sound getSound() {
        if(sound == null) {
            sound = new SoundImpl();
        }
        return sound;
    }
}
