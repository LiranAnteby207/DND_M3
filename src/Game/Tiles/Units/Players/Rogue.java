package Game.Tiles.Units.Players;

public class Rogue extends Player {
    protected int Cost;
    public Rogue(char tile, String name, int health, int attack, int defense, int cost) {
        super(tile,name,health,attack,defense);
        this.Cost = cost;
    }
}
