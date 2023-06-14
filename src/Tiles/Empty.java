package Tiles;

public class Empty extends Tile {
    protected Empty(BoardController boardController, Position p){
        super('.',boardController);
        position = new Position(p);
    }
    public void accept(Unit unit){
        unit.visit(this);
    }
}
