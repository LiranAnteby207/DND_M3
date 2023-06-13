public abstract class Tile implements Comparable<Tile> {
    protected char tile;
    protected Position position;
    protected Tile(char tile){this.tile = tile;}
    protected void initialize(Position position){
        this.position = position;
    }
    public char getTile() {return tile;}

    public Position getPosition() {return position;}
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
