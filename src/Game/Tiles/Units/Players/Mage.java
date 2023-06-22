package Game.Tiles.Units.Players;

import Controllers.InputController;
import Game.Tiles.Units.Enemies.Enemy;
import Game.Utils.Range;

import java.util.ArrayList;
import java.util.List;

public class Mage extends Player {
    protected int ManaPool; // Initial value is received as a constructor argument
    protected int CurrentMana;
    protected int ManaCost;//Received as an argument.
    protected int SpellPower; // Initial value is received as a constructor argument.
    protected int HitCount; //maximal number of times a single cast of the ability can hit. Received as an argument.
    protected int MageRange; //Received as an argument
    public Mage(char tile, String name, int health, int attack, int defense, int manaPool, int manaCost, int spellPower, int hitCount, int range) {
        super(tile,name,health,attack,defense);
        this.ManaPool = manaPool;
        this.CurrentMana = this.ManaPool / 4;
        this.ManaCost = manaCost;
        this.SpellPower = spellPower;
        this.HitCount = hitCount;
        this.MageRange = range;
    }
    public String describe(){
        return String.format("Mage %s level %s has: health amount: %s out of %s \n has mana pool %s, mana cost %s, spell power %s, hit count %s, mage range %s .", this.name,this.level, this.health.getHealthAmount(),this.health.getHealthPool(),ManaPool,ManaCost,SpellPower,HitCount,MageRange);
    }
    public Mage copy(){
        return new Mage(this.tile, this.name, this.health.getHealthPool(), this.attackPoints, this.defensePoints, this.ManaPool, this.ManaCost, this.SpellPower, this.HitCount, this.MageRange);
    }
    public void levelUp(){
        this.ManaPool = this.ManaPool + (25 * level);
        this.CurrentMana = Math.min(this.CurrentMana+(this.ManaPool/4) , ManaPool);
        this.SpellPower = this.SpellPower + (10 * level);
        super.levelUp();
    }

    public void onTick() {
        messageCallback.send("Choose your next move!");
        char act = InputController.inputCache();
        if(act == 'e')
            abilityCast();
        else{
            move(act);
        }
        this.CurrentMana = Math.min(this.ManaPool , this.CurrentMana + (1 *level));
    }
    public void abilityCast(){
        this.CurrentMana = this.CurrentMana - copy().ManaCost; //?

        int hits = 0;
        while ((hits < this.HitCount) && ( < ) ){

        }


    }
}
