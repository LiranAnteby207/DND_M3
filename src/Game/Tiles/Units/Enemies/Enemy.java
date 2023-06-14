package Game.Tiles.Units.Enemies;

import Game.Tiles.Units.Players.Player;
import Game.Tiles.Units.Unit;

public abstract class Enemy extends Unit {
    protected int experienceValue = 0;
    protected Enemy(char tile, String name, int healthCapacity, int attack, int defense){
        super(tile, name, healthCapacity, attack, defense);
    }
    public int getExperienceValue(){
        return experienceValue;
    }
    public void accept(Unit u){
        u.visit(this);
    }
    public void visit(Player p){
        //fight
    }
    public void visit(Enemy e){}
    public void setExperienceValue(int experienceValue) {
        this.experienceValue += experienceValue;
    }
}
