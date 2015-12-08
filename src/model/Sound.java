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
public interface Sound {
    public void play();
    public void setOff();
    public void setOn();
    public boolean getIsOn();
}
