package Game.Utils;

import Game.Tiles.Tile;

import java.lang.Math;
public class Range {
    protected double range;
    public Range(Tile t1, Tile t2){
        int x1 = t1.getPosition().getX();
        int y1 = t1.getPosition().getY();
        int x2 = t2.getPosition().getX();
        int y2 = t2.getPosition().getY();
        this.range = Math.sqrt(Math.pow(x1 - x2,2) + Math.pow(y1 - y2,2));
    }

    public double getRange() {
        return range;
    }
}
