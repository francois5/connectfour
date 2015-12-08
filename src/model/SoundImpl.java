/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/**
 *
 * @author seb
 */
public class SoundImpl implements Sound {
    URL url = SoundImpl.class.getResource("sounds/dropPone.wav");
    AudioClip ac = Applet.newAudioClip(url); 
    boolean isOn = true;
    
    @Override
    public void play() {
        if(isOn) {
            ac.play();
        }
    }
    
    @Override
    public void setOff() {
        isOn = false;
    }
    
    @Override
    public void setOn() {
        isOn = true;
    }
    
    @Override
    public boolean getIsOn() {
        return isOn;
    }
}
