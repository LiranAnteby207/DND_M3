package Game.Tiles.Units.Enemies;

import Game.Tiles.Empty;
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
        p.attack(this);
        if(isDead())
            onDeath((p));
    }
    public void onDeath(Player p){
        p.setExperience(experienceValue);
        messageCallback.send(String.format("%s died. %s gained %d experience.", this.getName(), p.getName(), this.getExperienceValue()));
        Empty e = new Empty(position);
        gameManager.gameBoard.emptys.add(e);
        gameManager.gameBoard.enemies.remove(this);
        e.swap(p);
    }
    public abstract void onTick();
    public void visit(Enemy e){}
    public void setExperienceValue(int experienceValue) {
        this.experienceValue += experienceValue;
    }
}
