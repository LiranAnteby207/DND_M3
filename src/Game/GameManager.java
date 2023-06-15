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
import Input.InputFromUser;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

public class GameManager {
    public MessageCallback messageCallback;
    public GameBoard gameBoard;
    public MoveController moveController;
    public List<Enemy> enemies;
    public int tickCount = 0;
    public UnitsController unitsController;
    public EnemiesKnowledge enemiesKnowledge;
    public List<File> levelsFiles=new ArrayList<>();
    public List<Unit> listTurn=new ArrayList<>();
    public GameManager(MessageCallback messageCallback){
        this.messageCallback = messageCallback;
        this.unitsController = new UnitsController();
        this.enemiesKnowledge = new EnemiesKnowledge(gameBoard);
        this.moveController.gameBoard = this.gameBoard;
        Unit.gameManager=this;
        Unit.messageCallback = messageCallback;
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
        messageCallback.send("Welcome to DND!!!!!\n");
        messageCallback.send("*** Game instructions:\n");
        messageCallback.send("* Game Controls:\n");
        messageCallback.send(
                "-Move up:\tW\n" +
                        "-Move down:\tS\n" +
                        "-Move right:\tD\n" +
                        "-Move left:\tA\n" +
                        "-Wait:\tQ\n" +
                        "-Attack: Stepping on an enemy\n" +
                        "-Cast special Attack:\tE\n");
        messageCallback.send("* Map description:\n");
        messageCallback.send("-(.):\t Free space\n" +
                "-(#):\t Wall\n" +
                "-(@):\t Your player\n");
        messageCallback.send("* Enemies list:\n");
        messageCallback.send("-(s):\t Lannister Solider\n" +
                "-(k):\t Lannister Knight\n" +
                "-(q):\t Queen’s Guard\n" +
                "-(z):\t Wright\n" +
                "-(b):\t Bear-Wright\n" +
                "-(g):\t Giant-Wright\n" +
                "-(w):\t White Walker\n" +
                "-(M):\t The Mountain\n" +
                "-(C):\t Queen Cersei\n" +
                "-(N):\t Night’s King\n" +
                "^ Traps:\n" +
                "-(B):\t Bonus Trap\n" +
                "-(Q):\t Queen’s Trap\n" +
                "-(D):\t Death Trap\n" );
    }
    public void getPlayer(){
        String PlayerChosen = choosePlayer();
        Player p = null;
        for(Map.Entry<String, Player> player : UnitsController.Players.entrySet()){
            if(player.getValue().getName() == PlayerChosen){
                p = player.getValue();
                break;
            }
        }
        if(p == null) {
            messageCallback.send("Entered wrong input, try again");
            getPlayer();
        }
        else{
            gameBoard.setPlayer(p);
        }
    }
    public String choosePlayer(){
        messageCallback.send("Choose your player: ");
        int i = 0;
        for(Map.Entry<String, Player> player : UnitsController.Players.entrySet())
            messageCallback.send(i + " :" + player.getValue().toString());
        messageCallback.send("Enter the number of the player you desire!");
        return InputFromUser.getRegex();
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
