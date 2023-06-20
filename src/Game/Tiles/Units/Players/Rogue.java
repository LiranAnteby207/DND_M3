package Game.Tiles.Units.Players;

public class Rogue extends Player {
    protected int Cost;
    public Rogue(char tile, String name, int health, int attack, int defense, int cost) {
        super(tile,name,health,attack,defense);
        this.Cost = cost;
    }
    public Rogue copy(){
        return new Rogue(this.tile, this.name, this.health.getHealthPool(), this.attackPoints, this.defensePoints, this.Cost);
    }
    public String describe(){
        return String.format("Rogue %s level %s has: health amount: %s out of %s .", this.name,this.level, this.health.getHealthAmount(),this.health.getHealthPool());
    }
    public void abilityCast(){}
}
