package Game.Tiles.Units;

import Game.Callbacks.MessageCallback;
import Game.GameManager;
import Game.Tiles.Empty;
import Game.Tiles.Tile;
import Game.Tiles.Units.Enemies.Enemy;
import Game.Tiles.Units.Players.Player;
import Game.Tiles.Wall;
import Game.Utils.Position;

import java.lang.Math;

public abstract class Unit extends Tile {
    protected String name;
    protected Health health;
    protected int attackPoints;
    protected int defensePoints;
    public static MessageCallback messageCallback;
    public static GameManager gameManager;
    protected Unit(char tile, String name, int healthCapacity, int attack, int defense){
        super(tile);
        this.name = name;
        this.health = new Health(healthCapacity);
        this.attackPoints = attack;
        this.defensePoints = defense;
    }
    public void initialize(Position p){
        super.initialize(p);
    }
    public String toString(){
        return this.name + ", " + this.health.toString() + ", attack points: " +
                this.attackPoints + ", defense points: " + this.defensePoints;
    }
    public abstract void onTick();
    public abstract Unit copy();
    public void attack(Unit unit){
        double attackRnd = (Math.random() * (this.attackPoints + 1));
        double defenseRnd = (Math.random() * (unit.attackPoints + 1));
        double score = attackRnd - defenseRnd;
        if(score > 0){
            unit.health.HealthAmount -= score;
        }
    }
    public boolean isDead(){
        return this.health.HealthAmount <= 0;
    }
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
    public void move(char act){
        if (act == 'w' )
            moveUp();
        if ( act == 's')
            moveDown();
        if (act == 'a')
            moveLeft();
        if (act == 'd')
            moveRight();
    }
    protected void moveLeft(){
        int x = position.getX();
        int y = position.getY();
        Tile tile = gameManager.gameBoard.getTile(x +1,y);
        this.interact(tile);
    }
    protected void moveRight(){
        int x = position.getX();
        int y = position.getY();
        Tile tile = gameManager.gameBoard.getTile(x - 1,y );
        this.interact(tile);

    }
    protected void moveUp(){
        int x = position.getX();
        int y = position.getY();
        Tile tile = gameManager.gameBoard.getTile(x,y+1);
        this.interact(tile);

    }
    protected void moveDown(){
        int x = position.getX();
        int y = position.getY();
        Tile tile = gameManager.gameBoard.getTile(x ,y-1);
        this.interact(tile);
    }
}
