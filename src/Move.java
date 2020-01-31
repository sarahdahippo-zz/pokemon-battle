/**
* Move.java
*
* Characteristics of an inidividual move of Pokemon
* @author Sarah
* @version 1.0.0
**/
public class Move {
    private String name;
    private int power;
    private String type;
    /**
     * Move constructor
     * @param n name of move
     * @param p power of move
     * @param t type of move
     */
    public Move(String n, int p, String t) {
        name = n;
        power = p;
        type = t;

    }
    /**
     * getter for name
     * @return name of move
     */
    public String getName() {
        return name;
    }
    /**
     * getter for power
     * @return power of move
     */
    public int getPower() {
        return power;
    }
    /**
     * getter for type
     * @return type of move
     */
    public String getType() {
        return type;
    }
}
