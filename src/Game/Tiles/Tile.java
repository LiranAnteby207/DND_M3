package Game.Tiles;

import Game.Tiles.Units.Unit;
import Game.Utils.Position;

public abstract class Tile implements Comparable<Tile> {
    protected char tile;
    protected Position position;
    protected Tile(char tile){this.tile = tile;}
    protected void initialize(Position position){
        this.position = position;
    }
    public String toString(){
        return tile +"";
    }
    public char getTile() {return tile;}
    public void setTile(char t) {this.tile = t;}

    public Position getPosition() {return position;}
    public void setPosition(Position p){
        this.position = p;
    }
    @Override
    public int compareTo(Tile tile) {
        return getPosition().compareTo(tile.getPosition());
    }
    public abstract void accept(Unit unit);
    public void swap(Tile t){
        Position temp = this.position;
        this.position = t.position;
        t.position = temp;
    }
}
