package Game.Tiles.Units.Enemies;

import Game.Tiles.Units.Players.Player;

public class Monster extends Enemy {
    protected int visionRange;
    protected Monster(int visionRange, char tile, String name, int healthCapacity, int attack, int defense){
        super(tile, name, healthCapacity, attack, defense);
        this.visionRange = visionRange;
    }
    public void move(Player p){
        Range r = new Range(p, this);
        if(r.range < visionRange)
            this.moveTowards(p);
        else
            moveRandom();
    }
    public void moveTowards(Player p) {
        int dx = p.getPosition().getX() - this.getPosition().getX();
        int dy = p.getPosition().getY() - this.getPosition().getY();
        if (Math.abs(dx) > Math.abs(dy)){
            if (dx > 0) moveLeft();
            else moveRight();
        }
        else {
            if(dy > 0) moveUp();
            else moveDown();
        }
    }
    public void moveRandom(){
        double rnd = Math.random();
        int randomMove = (int) (rnd * (4) + 1);
        if(randomMove == 1) moveRight();
        if(randomMove == 2) moveLeft();
        if(randomMove == 3) moveUp();
        else moveDown();
    }
}
