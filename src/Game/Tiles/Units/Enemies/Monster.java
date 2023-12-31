package Game.Tiles.Units.Enemies;

import Game.Tiles.Units.Players.Player;
import Game.Utils.Range;

public class Monster extends Enemy {
    protected int VisionRange;
    public Monster(char tile, String name, int healthCapacity, int attack, int defense, int visionRange, int experience){
        super(tile, name, healthCapacity, attack, defense,experience);
        this.VisionRange = visionRange;
    }
    public Monster copy(){
        return new Monster(this.tile, this.name, this.health.getHealthPool(), this.attackPoints, this.defensePoints, this.VisionRange, this.experienceValue);
    }
    public void onTick(){
        Player p = gameManager.gameBoard.getPlayer();
        move(p);
    }
    public void move(Player p){
        Range r = new Range(p, this);
        if(r.getRange() < VisionRange)
            this.moveTowards(p);
        else
            moveRandom();
    }
    private void moveTowards(Player p) {
        int dx = p.getPosition().getX() - this.getPosition().getX();
        int dy = p.getPosition().getY() - this.getPosition().getY();
        if (Math.abs(dx) > Math.abs(dy)){
            if (dx > 0) moveRight();
            else moveLeft();
        }
        else {
            if(dy > 0) moveDown();
            else moveUp();
        }
    }
        private void moveRandom(){
        double rnd = Math.random();
        int randomMove = (int) (rnd * (4) + 1);
        if(randomMove == 1) moveRight();
        if(randomMove == 2) moveLeft();
        if(randomMove == 3) moveUp();
        else moveDown();
    }
}
