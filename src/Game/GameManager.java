package Game;
import Controllers.InputController;
import Controllers.UnitsController;
import Game.Callbacks.MessageCallback;
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
    public UnitsController unitsController;
    public List<File> levelsFiles=new ArrayList<>();
    public List<Unit> listTurn=new ArrayList<>();
    public GameManager(MessageCallback messageCallback){
        this.messageCallback = messageCallback;
        this.unitsController = new UnitsController();
        this.gameBoard = new GameBoard();
        this.enemies = gameBoard.enemies;
        Unit.gameManager=this;
        Unit.messageCallback = messageCallback;
    }

    public void start(String path){
        putOutInstructions();
        getPlayer();
        ListOfAllMaps(path);
        for (File f : this.levelsFiles){
            Player p = gameBoard.getPlayer();
            p.initialize(p.getPosition());//
            if(p.isDead())
                break;
            loadGame(f);
            startLevel();
            if(p.isDead())
                break;
        }
        if(gameBoard.getPlayer().isDead())
            messageCallback.send("Game Over!");
        else
            messageCallback.send("You Won!!!");
    }
    public void startLevel(){
        while(!gameBoard.getPlayer().isDead() && this.enemies.size() != 0){
            printBoard();
            Iterator<Unit> tickIter = listTurn.iterator();
            this.tickCount++;
            messageCallback.send("tick number "+ tickCount);
            gameBoard.getPlayer().onTick();
            while(!gameBoard.getPlayer().isDead() && tickIter.hasNext()){
                Unit u = tickIter.next();
                if(!u.isDead())
                    u.onTick();
            }
            Iterator<Unit> iterator = listTurn.iterator();
            while (iterator.hasNext()) {
                Unit u = iterator.next();
                if (u.isDead()) {
                    iterator.remove();
                }
            }
            if(gameBoard.getPlayer().isDead()){
                messageCallback.send("player is dead!");
                break;
            }
        }

    }
    public void printBoard(){
        if (gameBoard.enemies.size() > 0){
            messageCallback.send(gameBoard.toString());
            messageCallback.send(gameBoard.getPlayer().describe());
        }
    }
    public void loadGame(File f){
        gameBoard.buildLevelBoard(f);
        listTurn.clear();
//        listTurn.add(gameBoard.getPlayer());
        for(Unit enemy: gameBoard.enemies)
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
        for(Map.Entry<String, List<Player>> players : UnitsController.Players.entrySet()) {
            for(Player player : players.getValue()){
                if(Objects.equals(player.getName(), PlayerChosen)){
                    p = player;
                    break;
                }
            }
            if(p != null)break;
        }
        gameBoard.setPlayer(p);
    }
    public String choosePlayer(){
        messageCallback.send("Choose your player: ");
        int i = 0;
        for(Map.Entry<String, List<Player>> player : UnitsController.Players.entrySet()){
            for(Player p : player.getValue()){
                messageCallback.send(i + " :" + p.describe());
                i++;
            }
        }
        String value = null;
        while(value == null){
            messageCallback.send("Enter the number of the player you desire!");
            value = getNameOfChosenPlayer(InputController.inputCache());
        }
        return value;
    }
    private String getNameOfChosenPlayer(char choise){
        if(choise == '0')
            return "Ygritte";
        if(choise == '1')
            return "Jon Snow";
        if(choise == '2')
            return "The Hound";
        if(choise == '3')
            return "Melisandre";
        if(choise == '4')
            return "Thoros of Myr";
        if(choise == '5')
            return "Arya Stark";
        if(choise == '6')
            return "Bronn";
        messageCallback.send("Enter a number between 0-6 !");
        return null;
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
