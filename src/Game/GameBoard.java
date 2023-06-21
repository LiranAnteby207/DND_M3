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
    public HashMap<Position, Wall> walls = new HashMap<Position, Wall>();
    public HashMap<Position, Empty> emptys = new HashMap<Position, Empty>();
    public String[][]board;
    public void setPlayer(Player p){
        this.player = p;
    }
    public Player getPlayer(){return this.player;}
    public void buildLevelBoard(File f){
        walls.clear();
        try {
            Scanner mapReader = new Scanner(f);
            int y = 0, x=0;
            Position position = new Position(x, y);
            Unit enemy;
            String data;
            while (mapReader.hasNextLine()){
                data = mapReader.nextLine();

                x = 0;
                for (char ch: data.toCharArray()){
                    if (ch == '@') {
                        player.setPosition(position);
                        setPlayer(player);
                    }
                    if (ch == '#') {
                        walls.put(position, new Wall(position));
                    }
                    if(ch == '.'){
                        emptys.put(position, new Empty(position));
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
            this.width = x;
            this.height = y;
            this.board = new String[y][x];
        }
        catch (Exception e){}
    }
    // build the board in arrays
    private void buildArray(){
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                board[y][x] = ".";
        board[player.getPosition().getY()][player.getPosition().getX()] =player.toString();
        for (Unit enemy : this.enemies){
            board[enemy.getPosition().getY()][enemy.getPosition().getX()] = enemy.toString();
        }
        for (Position wallPos: this.walls.keySet()){
            board[wallPos.getY()][wallPos.getX()] = this.walls.get(wallPos).toString();
        }
    }
    public Tile getTile(int x, int y){
        for (Enemy enemy : enemies) {
            if(enemy.getPosition().getX() == x && enemy.getPosition().getY() == y)
                return enemy;
        }
        for (Map.Entry<Position, Wall> entry : walls.entrySet()) {
            Position position = entry.getKey();
            if (position.getX() == x && position.getY() == y)
                return entry.getValue();
        }
        for (Map.Entry<Position, Empty> entry : emptys.entrySet()) {
            Position position = entry.getKey();
            if (position.getX() == x && position.getY() == y)
                return entry.getValue();
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
            for(int j=0; i< board[i].length; j++){
                finalBoard += board[i][j] ;
            }
            finalBoard += "\n";
        }
        return finalBoard;
    }
}
