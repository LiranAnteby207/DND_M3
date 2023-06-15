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
    public void abilityCast(){}
}
