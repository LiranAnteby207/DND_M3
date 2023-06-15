package Game.Utils;

public class Position implements Comparable<Position>{
    protected int x;
    protected int y;

    protected Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    protected Position(Position place){
        this.x = place.x;
        this.y = place.y;

    }
    public Position copy(){
        return new Position(this.x, this.y);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int compareTo(Position o) {
        return 0;
    }
}
