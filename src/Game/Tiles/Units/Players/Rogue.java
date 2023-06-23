package Game.Tiles.Units.Players;

import Controllers.InputController;
import Game.Tiles.Units.Enemies.Enemy;
import Game.Utils.Range;

public class Rogue extends Player {
    protected int Cost;
    protected int currentEnergy = 100;
    public Rogue(char tile, String name, int health, int attack, int defense, int cost) {
        super(tile,name,health,attack,defense);
        this.Cost = cost;
    }
    public Rogue copy(){
        return new Rogue(this.tile, this.name, this.health.getHealthPool(), this.attackPoints, this.defensePoints, this.Cost);
    }
    public String describe(){
        return String.format("Rogue %s level %d has: health amount: %d out of %d ,attack %d, defense %d,  current energy %d, cost is %d", this.name, this.level, this.health.getHealthAmount(),this.health.getHealthPool(),this.attackPoints, this.defensePoints, currentEnergy, Cost);
    }
    public void onTick(){
        messageCallback.send("Choose your next move!");
        char act = InputController.inputCache();
        if(act == 'e'){
            if(this.currentEnergy >= Cost)
                abilityCast();
            else
                messageCallback.send("Not enough energy!");
        }
        else{
            move(act);
        }
        currentEnergy = Math.min(currentEnergy + 10, 100);
    }
    public void abilityCast(){
        fanOfKnives();
        currentEnergy -= Cost;
    }
    private void fanOfKnives(){
        for(Enemy e : gameManager.gameBoard.enemies){
            Range rng = new Range(e,this);
            if(rng.getRange() < 2)
                e.gotAttackedAbilityCast(this.attackPoints,this);
        }
    }
    public void levelUp() {
        super.levelUp();
        this.currentEnergy = 100;
        this.attackPoints += 3*this.level;
    }
}
