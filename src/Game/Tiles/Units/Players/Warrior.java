package Game.Tiles.Units.Players;

import Controllers.InputController;
import Game.Tiles.Units.Enemies.Enemy;
import Game.Utils.Range;

import java.util.*;

public class Warrior extends Player {
    protected int abilityCooldown;
    protected int remainingCooldown = 0;
    public Warrior(char tile, String name, int healthCapacity, int attack, int defense, int abilityCooldown){
        super(tile, name, healthCapacity, attack, defense);
        this.abilityCooldown = abilityCooldown;
    }
    public Warrior copy(){
        return new Warrior(this.tile, this.name, this.health.getHealthPool(), this.attackPoints, this.defensePoints, this.abilityCooldown);
    }
    public String describe(){
        return String.format("Warrior %s level %s has: health amount: %s out of %s, attack: %d, defense: %d remaining coolDown left: %s", this.name,this.level, this.health.getHealthAmount(),this.health.getHealthPool(),attackPoints,defensePoints,this.remainingCooldown);
    }
    public void abilityCast(){
        this.remainingCooldown = this.abilityCooldown;
        int newHealthAmount = Math.min(this.health.getHealthAmount() + (10 * this.getDefensePoints()), this.health.getHealthPool());
        this.health.setHealthAmount(newHealthAmount);
        AvengersShield();
    }
    public void AvengersShield() {
        List<Enemy> inRangeEnemies = new ArrayList<>();
        for (Enemy e : gameManager.gameBoard.enemies) {
            Range rng = new Range(e, this);
            if (rng.getRange() < 3) {
                inRangeEnemies.add(e);
            }
        }

        if (!inRangeEnemies.isEmpty()) {
            Enemy randomEnemy = getRandomEnemy(inRangeEnemies);
            randomEnemy.gotAttackedAbilityCast(0.1*this.health.getHealthPool(),this);
        }
    }

    private Enemy getRandomEnemy(List<Enemy> enemies) {
        int randomIndex = new Random().nextInt(enemies.size());
        return enemies.get(randomIndex);
    }

    public void onTick(){
        messageCallback.send("Choose your next move!");
        char act = InputController.inputCache();
        if(act == 'e') {
            if (this.remainingCooldown == 0)
                abilityCast();
        }
        else{
            move(act);
        }
        if (this.remainingCooldown > 0){
            this.remainingCooldown = this.remainingCooldown - 1;
        }
    }

    public void levelUp(){
        super.levelUp();
        this.remainingCooldown = 0;
        this.health.addHealthPool(5 * this.level);
        this.health.setHealthPool(this.health.getHealthPool());
        setAttackPoints(getAttackPoints() + 2 * this.level);
        setDefensePoints(getDefensePoints() + level);
    }
}
