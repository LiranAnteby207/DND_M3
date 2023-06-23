package Game.Tiles.Units.Enemies;
import Game.Tiles.Units.Players.Player;
import Game.Utils.Range;
public class Trap extends Enemy {
    protected int VisibilityTime;
    protected int InVisibilityTime;
    protected int ticksCount = 0;
    protected boolean visible = true;
    private  char VISIBLE_TILE ;
    private  char INVISIBLE_TILE='.';
public Trap(char tile, String name, int healthCapacity, int attack, int defense, int experienceValue, int visibilityTime, int invisibilityTime){
        super(tile,name, healthCapacity, attack, defense,experienceValue);
        VISIBLE_TILE = tile;
        this.VisibilityTime = visibilityTime;
        this.InVisibilityTime = invisibilityTime;
    }
    public void onTick(){
        visible = ticksCount < VisibilityTime;
        if(!visible)
            this.tile = INVISIBLE_TILE;
        else
            this.tile = VISIBLE_TILE;
        Player p = gameManager.gameBoard.getPlayer();
        Range range = new Range(this,p);
        if(ticksCount == VisibilityTime + InVisibilityTime)
            ticksCount = 0;
        else
            ticksCount ++;
        if(range.getRange() < 2 )
            p.attack(this);
    }
    public boolean isVisible() {
        return visible;
    }
    public Trap copy(){
    return new Trap(this.tile, this.name, this.health.getHealthPool(), this.attackPoints, this.defensePoints, this.experienceValue,this.VisibilityTime, this.InVisibilityTime);
    }
}
