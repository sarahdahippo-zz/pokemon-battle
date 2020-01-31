/**
* Pokemon.java
*
* Characteristics of inidividual Pokemon
* @author Sarah
* @version 1.0.0
**/
public class Pokemon {
    private String name;
    private int level;
    private double maxHP;
    private double currentHP;
    private int atk;
    private Move[] moves;
    private String type;
    private boolean fainted;
    /**
     * Pokemon constructor
     * @param n name of Pokemon
     * @param l level of Pokemon
     * @param h maxHP of Pokemon
     * @param a attack level of Pokemon
     * @param t type of Pokemon
     * @param move array of moves of Pokemon
     */
    public Pokemon(String n, int l, double h, int a, String t, Move... move) {
        name = n;
        level = l;
        maxHP = h;
        atk = a;
        type = t;
        currentHP = maxHP;
        moves = move;
    }
    /**
     * getter for name
     * @return name of Pokemon
     */
    public String getName() {
        return name;
    }
    /**
     * getter for level
     * @return level of Pokemon
     */
    public int getLevel() {
        return level;
    }
    /**
     * getter for attack level
     * @return attack level of Pokemon
     */
    public int getAtk() {
        return atk;
    }
    /**
     * getter for maxHP
     * @return maxHP of Pokemon
     */
    public double getMaxHP() {
        return maxHP;
    }
    /**
     * getter for current HP
     * @return currentHP of Pokemon
     */
    public double getCurrentHP() {
        return currentHP;
    }
    /**
     * getter for type
     * @return type of Pokemon
     */
    public String getType() {
        return type;
    }
    /**
     * getter for moves
     * @return an array of the moves of Pokemon
     */
    public Move[] getMoves() {
        return moves;
    }
    /**
     * setter for name
     * @param name of Pokemon
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * setter for level
     * @param level of Pokemon
     */
    public void setLevel(int level) {
        this.level = level;
    }
    /**
     * setter for attack level
     * @param attack level of Pokemon
     */
    public void setAtk(int attack) {
        this.atk = atk;
    }
    /**
     * setter for maxHP
     * @param maxHP of Pokemon
     */
    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }
    /**
     * setter for type
     * @param type of Pokemon
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * setter for moves
     * @param moves array of the moves of Pokemon
     */
    public void setMoves(Move[] moves) {
        this.moves = moves;
    }
    /**
     * setter for currentHP
     * @param currentHP of Pokemon
     */
    public void setCurrentHP(double currentHP) {
        this.currentHP = currentHP;
        if (this.currentHP <= 0) {
            this.currentHP = 0;
            fainted = true;
        }
    }
    /**
     * compares Pokemon types to determine damage
     * @param move array of moves of Pokemon
     * @return amount of damage
     */
    public double compareType(Move move) {
        if (move.getType().equals(type)) {
            return 0.5;
        } else if (move.getType().equals("WATER")) {
            if (type.equals("FIRE")) {
                return 2.0;
            } else if (type.equals("GRASS")) {
                return 0.5;
            }
        } else if (move.getType().equals("GRASS")) {
            if (type.equals("WATER")) {
                return 2.0;
            } else if (type.equals("FIRE")) {
                return 0.5;
            } else if (type.equals("FLYING")) {
                return 0.5;
            }
        } else if (move.getType().equals("FIRE")) {
            if (type.equals("GRASS")) {
                return 2.0;
            } else if (type.equals("WATER")) {
                return 0.5;
            }
        } else if (move.getType().equals("FLYING")) {
            if (type.equals("GRASS")) {
                return 2.0;
            }
        }
        return 1;
    }
    /**
     * checks if Pokemon has fainted
     * @return fainted
     */
    public boolean isFainted() {
        return fainted;
    }
    /**
     * setter for fainted
     * @param fainted boolean value of whether Pokemon has fainted
     */
    public void setFainted(boolean fainted) {
        this.fainted = fainted;
    }
}
