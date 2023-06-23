package Game;

import Controllers.UnitsController;
import Game.Tiles.Empty;
import Game.Tiles.Units.Enemies.Enemy;
import Game.Tiles.Tile;
import Game.Tiles.Units.Players.Player;
import Game.Tiles.Units.Unit;
import Game.Tiles.Wall;
import Game.Utils.Position;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class GameBoard {
    public Player player;
    public int height;
    public int width;
    public List<Enemy> enemies= new ArrayList<Enemy>();
    public List<Wall> walls = new ArrayList<>();
    public List<Empty> emptys = new ArrayList<>();
    public String[][]board;
    public GameBoard(){
    }
    public void setPlayer(Player p){
        this.player = p;
    }
    public Player getPlayer(){return this.player;}
    public void buildLevelBoard(File f){
        walls.clear();
        try {
            Scanner mapReader = new Scanner(f);
            int y = 0, x=0;
            Unit enemy;
            String data;
            while (mapReader.hasNextLine()){
                data = mapReader.nextLine();

                x = 0;
                for (char ch: data.toCharArray()){
                    Position position = new Position(x, y);
                    if (ch == '@') {
                        player.setPosition(position);
                        setPlayer(player);
                    }
                    if (ch == '#') {
                        walls.add( new Wall(position));
                    }
                    if(ch == '.'){
                        emptys.add(new Empty(position));
                    }
                    if (UnitsController.Enemies.containsKey(ch+"")){
                        enemy = UnitsController.Enemies.get(ch+"").copy();
                        enemy.setPosition(position);
                        enemies.add((Enemy) enemy);
                    }
                    x++;
                }
                y++;
            }
            this.width = x ;
            this.height = y ;
            this.board = new String[height][width];
        }
        catch (Exception e){}
    }
    // build the board in arrays
    private void buildArray(){
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                board[y][x] = ".";
        board[player.getPosition().getY()][player.getPosition().getX()] =player.getTile()+ "";
        for (Unit enemy : this.enemies){
            board[enemy.getPosition().getY()][enemy.getPosition().getX()] = enemy.getTile()+"";
        }
        for (Wall wall: this.walls){
            board[wall.getPosition().getY()][wall.getPosition().getX()] = wall.toString();
        }
    }
    public Tile getTile(int x, int y){
        if(getPlayer().getPosition().getX() == x && getPlayer().getPosition().getY() == y)
            return getPlayer();
        for (Enemy enemy : enemies) {
            if(enemy.getPosition().getX() == x && enemy.getPosition().getY() == y)
                return enemy;
        }
        for (Wall wall : walls) {
            Position position = wall.getPosition();
            if (position.getX() == x && position.getY() == y)
                return wall;
        }
        for (Empty empty : emptys) {
            Position position = empty.getPosition();
            if (position.getX() == x && position.getY() == y)
                return empty;
        }
        return null;
    }
    @Override
    public String toString() {
        buildArray();
        return boardToString();
    }
    private String boardToString(){
        String finalBoard = "";
        for (int i =0; i < board.length; i++){
            for(int j=0; j< board[i].length; j++){
                finalBoard += board[i][j];
            }
            finalBoard += "\n";
        }
        return finalBoard.toString();
    }
}
