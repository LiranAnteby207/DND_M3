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
    protected int visibilityTime;
    protected int invisibilityTime;
    protected int ticksCount = 0;
    protected boolean visible = true;
//    private  char VISIBLE_TILE;
//    private  char INVISIBLE_TILE='.';
    protected Trap(int visibilityTime, int invisibilityTime, String name, int healthCapacity, int attack, int defense){
        super('T',name, healthCapacity, attack, defense);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
    }
    public void turn(Player p){
     setVisible(ticksCount<visibilityTime);
     if (ticksCount == (visibilityTime + invisibilityTime))
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
    public void setVisible(Boolean visible){
        this.visible = visible;
        //setTile(visible ? VISIBLE_TILE : INVISIBLE_TILE);
        if (!visible)
            setTile('.');
    }

}
