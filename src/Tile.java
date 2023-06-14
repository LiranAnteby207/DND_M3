public abstract class Tile implements Comparable<Tile> {
    private BoardController boardController;
    protected char tile;
    protected Position position;
    protected Tile(char tile, BoardController boardController){this.tile = tile;this.boardController = boardController}
    protected void initialize(Position position){
        this.position = position;
    }
    public char getTile() {return tile;}
    public void setTile(char t) {this.tile = t;}

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
