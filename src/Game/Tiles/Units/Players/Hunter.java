package Game.Tiles.Units.Players;

import Controllers.InputController;
import Game.Tiles.Units.Enemies.Enemy;
import Game.Utils.Range;

import java.util.ArrayList;
import java.util.List;

public class Hunter extends Player{
    protected int range;
    protected int arrowsCount ;
    protected int ticksCount=0;
    public Hunter(char tile, String name, int health, int attack, int defense, int range) {
        super(tile,name,health,attack,defense);
        this.range = range;
        this.arrowsCount = 10 * this.level;
    }
    public Hunter copy(){
        return new Hunter(this.tile, this.name, this.health.getHealthPool(), this.attackPoints, this.defensePoints, this.range);
    }
    public String describe(){
        return String.format("Hunter %s level %d has: health amount: %d out of %d ,attack %d, defense %d,  range %d, arrowsCount is %d", this.name, this.level, this.health.getHealthAmount(),this.health.getHealthPool(),this.attackPoints, this.defensePoints, this.range, this.arrowsCount);
    }
    public void onTick(){
        messageCallback.send("Choose your next move!");
        char act = InputController.inputCache();
        if(act == 'e'){
            if(this.arrowsCount != 0)
                abilityCast();
            else
                messageCallback.send("Not enough energy!");
        }
        else{
            move(act);
            if(this.ticksCount == 10){
                this.arrowsCount++;
                this.ticksCount = 0;
            }
            else
                this.ticksCount++;
        }
    }
    public void abilityCast(){
        this.arrowsCount--;
        Enemy closestEnemy = getClosestEnemy();
        if(closestEnemy != null){
            closestEnemy.gotAttackedAbilityCast(this.attackPoints,this);
        }
    }
    private Enemy getClosestEnemy(){
        double minRange = Integer.MAX_VALUE;
        Enemy value = null;
        for(Enemy e: gameManager.gameBoard.enemies){
            Range rng = new Range(this,e);
            if(rng.getRange() < minRange){
                minRange = rng.getRange();
                value = e;
            }
        }
        return value;
    }
    public void levelUp() {
        super.levelUp();
        this.arrowsCount += this.level * 10;
        this.attackPoints += 2*this.level;
        this.defensePoints += this.level;
    }
}
