package Game.Tiles.Units.Players;

import Controllers.InputController;
import Game.Tiles.Units.Enemies.Enemy;
import Game.Utils.Range;

import java.util.*;

public class Warrior extends Player {
    protected int abilityCooldown;//received as a constructor argument.Represents the number of
    //game ticks required to pass before the warrior can cast the ability again.
    protected int remainingCooldown = 0;
    public Warrior(char tile, String name, int healthCapacity, int attack, int defense, int abilityCooldown){
        super(tile, name, healthCapacity, attack, defense);
        this.abilityCooldown = abilityCooldown;
    }
    public Warrior copy(){
        return new Warrior(this.tile, this.name, this.health.getHealthPool(), this.attackPoints, this.defensePoints, this.abilityCooldown);
    }
    public String describe(){
        return String.format("Warrior %s level %s has: health amount: %s out of %s, remaining coolDown left: %s", this.name,this.level, this.health.getHealthAmount(),this.health.getHealthPool(),this.remainingCooldown);
    }
    public void abilityCast(){
        this.remainingCooldown = this.abilityCooldown;
        int newHealthAmount = Math.min(this.health.getHealthAmount() + (10 * this.getDefensePoints()), this.health.getHealthPool());
        this.health.setHealthAmount(newHealthAmount);
        AvengersShield();
    }

    //randomly hits one enemy withing range < 3 for an amount
    //equals to 10% of the warrior’s max health and heals the warrior for amount equals to (10×defense)
    //(but will not exceed the total amount of health pool).
    //??
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
            randomEnemy.visit(this);
        }
    }

    private Enemy getRandomEnemy(List<Enemy> enemies) {
        int randomIndex = new Random().nextInt(enemies.size());
        return enemies.get(randomIndex);
    }

    public void onTick(){
        messageCallback.send("Choose your next move!");
        char act = InputController.inputCache();
        if(act == 'e')
            abilityCast();
        else{
            move(act);
        }
        if (this.remainingCooldown > 0){
            this.remainingCooldown = this.remainingCooldown - 1;
        }
    }

    public void levelUp(){
        this.remainingCooldown = 0;
        this.health.addHealthPool(5 * this.level);
        this.health.setHealthPool(this.health.getHealthPool());
        setAttackPoints(getAttackPoints() + 2 * this.level);
        setDefensePoints(getDefensePoints() + level);
        super.levelUp();
    }
    public int getAbilityCooldown(){ return this.abilityCooldown;}
    public int getRemainingCooldown(){ return this.remainingCooldown;}
    public void setRemainingCooldown(int update){ this.remainingCooldown = update;}
    public void setWarriorExperience(int exp){
        setExperience(exp);
        //eirjjgnergiiuunereregluuhhbq3gkluhb3qgouiyhbq3gpoiuybq34ggpbq23gpiiubn3rgg
    }
}
