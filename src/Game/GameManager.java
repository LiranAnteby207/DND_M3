package Game;
import Game.Callbacks.MessageCallback;
//import Game.Handelers.InputHandler;
//import Game.Handelers.MoveHandler;
//import Game.Handelers.TargetHandler;
import Game.Tiles.Units.Enemies.Enemy;
import Game.Tiles.Units.Players.Player;
import Game.Tiles.Units.Unit;
import java.io.File;
import java.io.FilenameFilter;
import java.util.*;
public class GameManager {
    public MessageCallback messageCallback;
    public GameBoard gameBoard;
    public List<Enemy> enemies;
    public int tickCount = 0;
    public List<File> levelsFiles=new ArrayList<File>();
    public List<Unit> listTurn=new ArrayList<Unit>();
    public GameManager(MessageCallback messageCallback){
        this.messageCallback = messageCallback;
        DatabaseUnits.buildDictionary();
        TargetHandler.gameBoard=this.board;
        MoveHandler.gameBoard=this.board;
        Unit.gameManager=this;
        Unit.setMessageCallback(m);
    }

    public void stat(String path){
    }
}
