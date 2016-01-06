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
