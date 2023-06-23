package Game.Tiles.Units.Players;

import Controllers.InputController;
import Game.Tiles.Units.Enemies.Enemy;
import Game.Utils.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mage extends Player {
    protected int ManaPool;
    protected int CurrentMana;
    protected int ManaCost;
    protected int SpellPower;
    protected int HitCount;
    protected int AbilityRange;
    public Mage(char tile, String name, int health, int attack, int defense, int manaPool, int manaCost, int spellPower, int hitCount, int range) {
        super(tile,name,health,attack,defense);
        this.ManaPool = manaPool;
        this.CurrentMana = this.ManaPool / 4;
        this.ManaCost = manaCost;
        this.SpellPower = spellPower;
        this.HitCount = hitCount;
        this.AbilityRange = range;
    }
    public String describe(){
        return String.format("Mage %s level %s has: health amount: %s out of %s, attack: %d, defence: %d \n has mana pool %s, mana cost %s, spell power %s, hit count %s, mage range %s .", this.name,this.level, this.health.getHealthAmount(),this.health.getHealthPool(),attackPoints,defensePoints,ManaPool,ManaCost,SpellPower,HitCount,AbilityRange);
    }
    public Mage copy(){
        return new Mage(this.tile, this.name, this.health.getHealthPool(), this.attackPoints, this.defensePoints, this.ManaPool, this.ManaCost, this.SpellPower, this.HitCount, this.AbilityRange);
    }
    public void levelUp(){
        super.levelUp();
        this.ManaPool += 25 * this.level;
        this.CurrentMana = Math.min(this.CurrentMana+(this.ManaPool/4) , ManaPool);
        this.SpellPower = this.SpellPower + (10 * level);
    }

    public void onTick() {
        messageCallback.send("Choose your next move!");
        char act = InputController.inputCache();
        if(act == 'e'){
            if(this.CurrentMana < ManaCost)
                abilityCast();
        }
        else{
            move(act);
        }
        this.CurrentMana = Math.min(this.ManaPool , this.CurrentMana + this.level);
    }
    public void abilityCast(){
        this.CurrentMana = this.CurrentMana - this.ManaCost;
        List<Enemy> inRangeEnemies = new ArrayList<>();
        for (Enemy e : gameManager.gameBoard.enemies) {
            Range rng = new Range(e, this);
            if (rng.getRange() <= this.AbilityRange) {
                inRangeEnemies.add(e);
            }
        }
        int hits = 0;

        while ((hits < this.HitCount) && (!inRangeEnemies.isEmpty()) ){
            Enemy randomEnemy = getRandomEnemy(inRangeEnemies);
            randomEnemy.gotAttackedAbilityCast(this.SpellPower, this);
            if(randomEnemy.isDead())
                inRangeEnemies.remove(randomEnemy);
            hits++;
        }
    }
    private Enemy getRandomEnemy(List<Enemy> enemies) {
        int randomIndex = new Random().nextInt(enemies.size());
        return enemies.get(randomIndex);
    }

}
