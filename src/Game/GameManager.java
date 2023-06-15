package Game;
import Controllers.MoveController;
import Controllers.UnitsController;
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
    GameBoard gameBoard;
    public MoveController moveController;
    public List<Enemy> enemies;
    public int tickCount = 0;
    public UnitsController unitsController;
    public List<File> levelsFiles=new ArrayList<>();
    public List<Unit> listTurn=new ArrayList<>();
    public GameManager(MessageCallback messageCallback){
        this.messageCallback = messageCallback;
        this.unitsController = new UnitsController();
        TargetHandler.gameBoard=this.board;
        this.moveController.gameBoard = this.gameBoard;
        Unit.gameManager=this;
        Unit.setMessageCallback(m);
    }

    public void start(String path){
        putOutInstructions();
        getPlayer();
        ListOfAllMaps(path);
        for (File f : this.levelsFiles){
            Player p = gameBoard.getPlayer();
            p.initialize(p.getPosition(), messageCallback);
            if(p.isDead())
                break;
            loadGame(f);
            startLevel();
        }
        if(gameBoard.getPlayer().isDead())
            messageCallback.send("Game Over!");
        else
            messageCallback.send("You Won!!!");
    }
    public void loadGame(File f){
        board.buildLevelBoard(f);
        listTurn.clear();
        listTurn.add(gameBoard.getPlayer());
        for(Unit enemy: board.enemies)
            listTurn.add(enemy);
    }
    public void putOutInstructions(){
        // to make message with all instructions
    }
    public void getPlayer(){
        //create input handeler to get from user the player he wants
        //add the player to the game board.
    }
    public void ListOfAllMaps(String path){
        File f = new File(path);
        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return  name.endsWith("txt");
            }
        });
        assert matchingFiles != null;
        levelsFiles= Arrays.asList(matchingFiles);
        levelsFiles.sort((File f1,File f2)->f1.getName().compareTo(f2.getName()));
    }
}
