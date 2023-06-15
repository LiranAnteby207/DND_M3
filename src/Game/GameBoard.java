package Game;

import Game.Tiles.Empty;
import Game.Tiles.Units.Enemies.Enemy;
import Game.Tiles.Tile;
import Game.Tiles.Units.Players.Player;
import Game.Utils.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    public Player player;
    private List<Tile> tiles;
    // 50*20 - so list size 1000!

    public GameBoard(Tile[][] board) {
        tiles = new ArrayList<>();
        for (Tile[] line : board) {
            tiles.addAll(Arrays.asList(line));
        }
    }
    public void setPlayer(Player p){
        this.player = p;
    }
    public Player getPlayer(){return this.player;}
    public Tile get(int x, int y) {
        for(Tile t : tiles){
            if (t.getPosition().equals(Position.at(x, y))){
                return t;
            }
        }
        // Throw an exception if no such tile.
    }
    public void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        tiles.add(new Empty(p));
    }

    @Override
    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        // TODO: Implement me
    }
}
