package Game.Tiles.Units.Players;

public class Mage extends Player {
    protected int ManaPool;
    protected int ManaCost;
    protected int SpellPower;
    protected int HitCount;
    protected int MageRange;
    public Mage(char tile, String name, int health, int attack, int defense, int manaPool, int manaCost, int spellPower, int hitCount, int range) {
        super(tile,name,health,attack,defense);
        this.ManaPool = manaPool;
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
    public void abilityCast(){}
}
