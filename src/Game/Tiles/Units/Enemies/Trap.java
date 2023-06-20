package Game.Tiles.Units.Enemies;

import Game.Tiles.Empty;
import Game.Tiles.Units.Players.Player;
import Game.Utils.Range;
//package Game.Tiles.Units.Enemies;
//import Game.Handelers.TargetHandler;
import Game.Tiles.Units.Players.Player;
//import Game.Tiles;
import Game.Tiles.Units.Unit;
import Game.Utils.Position;
import Game.Utils.Range;

public class Trap extends Enemy {
    protected int VisibilityTime;
    protected int InVisibilityTime;
    protected int ExperienceValue;
    protected int ticksCount = 0;
    protected boolean visible = true;
//    private  char VISIBLE_TILE;
//    private  char INVISIBLE_TILE='.';
public Trap(char tile, String name, int healthCapacity, int attack, int defense, int experienceValue, int visibilityTime, int invisibilityTime){
        super(tile,name, healthCapacity, attack, defense);
        this.VisibilityTime = visibilityTime;
        this.InVisibilityTime = invisibilityTime;
        this.ExperienceValue = experienceValue;
    }
    public void turn(Player p){
     setVisible(ticksCount<VisibilityTime);
     if (ticksCount == (VisibilityTime + InVisibilityTime))
         ticksCount = 0;
     else
         ticksCount +=1;
     Range range = new Range(this, p);
     if (range.getRange() < 2)
         attack(p);
    }

    public boolean isVisible() {
        return visible;
    }
    public void onDeath(){this.remove(); }
    public Trap copy(){
    return new Trap(this.tile, this.name, this.health.getHealthPool(), this.attackPoints, this.defensePoints, this.ExperienceValue,this.VisibilityTime, this.InVisibilityTime);
    }
    public void setVisible(Boolean visible){
        this.visible = visible;
        //setTile(visible ? VISIBLE_TILE : INVISIBLE_TILE);
        if (!visible)
            setTile('.');
    }

}
