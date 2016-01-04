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
public class Player {
    private String name;
    private boolean computer;
    
    public Player(String name, boolean computer) {
        this.name = name;
        this.computer = computer;
    }

    public String getName() {
        return name;
    }

    public boolean isComputer() {
        return computer;
    }

}
