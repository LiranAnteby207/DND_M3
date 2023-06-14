package Game.Tiles.Units;

import Game.Tiles.Empty;
import Game.Tiles.Tile;
import Game.Tiles.Units.Enemies.Enemy;
import Game.Tiles.Units.Players.Player;
import Game.Tiles.Wall;

import java.lang.Math;

public abstract class Unit extends Tile {
    protected String name;
    protected Health health;
    protected int attackPoints;
    protected int defensePoints;
    protected Unit(char tile, String name, int healthCapacity, int attack, int defense){
        super(tile);
        this.name = name;
        this.health = new Health(healthCapacity);
        this.attackPoints = attack;
        this.defensePoints = defense;
    }
    public void attack(Unit unit){
        int attackRnd = (int)(Math.random() * (this.attackPoints + 1));
        int defenseRnd = (int)(Math.random() * (unit.attackPoints + 1));
        int score = attackRnd - defenseRnd;
        if(score > 0){
            unit.health.HealthAmount -= score;
            if(unit.health.HealthAmount <= 0)
                unit.onDeath();
        }
        this.swap(unit);
    }
    public abstract void onDeath();
    public String getName() {
        return name;
    }

    public Health getHealth() {
        return health;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }
    public void visit(Wall w){}
    public void visit(Empty e){
        this.swap(e);
    }
    public abstract void visit(Enemy e);
    public abstract void visit(Player p);

    public void interact(Tile tile){
        tile.accept(this);
    }
    protected void moveLeft(){
        //need to get the tile from the left and do 'swap'

        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

    }
    protected void moveRight(){
        //need to get the tile from the right and do 'swap'

    }
    protected void moveUp(){
        //need to get the tile from the up and do 'swap'

    }
    protected void moveDown(){
        //need to get the tile from the down and do 'swap'

    }
}
