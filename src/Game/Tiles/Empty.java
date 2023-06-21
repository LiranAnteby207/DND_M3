package Game.Tiles;

import Game.Tiles.Units.Unit;
import Game.Utils.Position;

public class Empty extends Tile {
    public Empty(Position p){
        super('.');
        position = new Position(p);
    }
    public void accept(Unit unit){
        unit.visit(this);
    }
}
