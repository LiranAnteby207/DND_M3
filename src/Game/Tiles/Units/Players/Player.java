package Game.Tiles.Units.Players;

import Controllers.InputController;
import Game.Callbacks.MessageCallback;
import Game.Tiles.Units.Enemies.Enemy;
import Game.Tiles.Units.Health;
import Game.Tiles.Units.Unit;
import Game.Utils.ConsoleColors;
import Game.Utils.Position;

public abstract class Player extends Unit {
    protected int experience = 0;
    protected int level = 1;
    protected Player(char tile, String name, int healthCapacity, int attack, int defense){
        super(tile, name, healthCapacity, attack, defense);
    }
    public void initialize(Position p ){
        super.initialize(p);
    }
    public abstract void onTick();
    public void levelUp(){
        StringBuilder s = new StringBuilder();
        s.append(ConsoleColors.CYAN).append(String.format("%s leveled up!!!",this.name)).append(ConsoleColors.RESET);
        messageCallback.send(s.toString());
        this.experience -= 50 * this.level;
        this.level += 1;
        this.health.addHealthPool(10 * this.level);
        this.health.setHealthAmount( this.health.getHealthPool());
        setAttackPoints(getAttackPoints() + 4 * this.level);
        setDefensePoints(getDefensePoints() + level);
    }
    public abstract String describe();
    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }
    public void accept(Unit u){
        u.visit(this);
    }
    public void visit (Enemy e){
        e.attack(this);
        if(e.isDead())
            e.onDeath(this);
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
    }
    public abstract void abilityCast();
    public void setLevel(int level) {
        this.level = level;
    }

}
