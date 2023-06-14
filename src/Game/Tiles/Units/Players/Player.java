package Game.Tiles.Units.Players;

import Game.Tiles.Units.Enemies.Enemy;
import Game.Tiles.Units.Unit;

public abstract class Player extends Unit {
    protected int experience = 0;
    protected int level = 1;
    protected Player(char tile, String name, int healthCapacity, int attack, int defense){
        super(tile, name, healthCapacity, attack, defense);
    }
    public void levelUp(){
        this.experience -= 50 * this.level;
        this.level += 1;
        this.health.addHealthPool(10 * this.level);
        this.health.setHealthPool(this.health.getHealthPool());
        setAttackPoints(getAttackPoints() + 4 * this.level);
        setDefensePoints(getDefensePoints() + level);
    }
    public int getExperience() {
        return experience;
    }

    private int getLevel() {
        return level;
    }
    public void accept(Unit u){
        u.visit(this);
    }
    public void visit (Enemy e){
        //something
    }
    public void visit (Player p){}
    public void setExperience(int experienceFromFight) {
        this.experience += experienceFromFight;
        while(this.experience >= this.level * 50 ){
            levelUp();
        }
    }
    public void onDeath(){
        this.setTile('X');
        //BoardController.endGame();
    }
    public abstract void abilityCast();
    public void setLevel(int level) {
        this.level = level;
    }
}
