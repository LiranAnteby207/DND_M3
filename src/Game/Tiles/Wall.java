package Game.Tiles;

import Game.Tiles.Units.Unit;
import Game.Utils.Position;

public class Wall extends Tile {

    public Wall(Position p){super('#');
        super.initialize(p);
    }

    @Override
    public void accept(Unit unit) {}
}
