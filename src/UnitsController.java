import Game.Tiles.Units.Enemies.Monster;
import Game.Tiles.Units.Enemies.Trap;
import Game.Tiles.Units.Players.Mage;
import Game.Tiles.Units.Players.Rogue;
import Game.Tiles.Units.Players.Warrior;
import Game.Tiles.Units.Unit;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
public class UnitsController {
    public Map<String,Unit> Players = new HashMap<>();
    public Map<String,Unit> Enemies = new HashMap<>();
    private final static String dirAddons = "src/DataBase";
    public UnitsController(){
        connectToDb();
    }
    public void connectToDb(){
        buildUnit("/DataBase/DbPlayers", Players); // players list
        buildUnit("/DataBase/DbEnemies", Enemies); // enemies list
    }
    public void buildUnit(String path, Map<String,Unit>){

    }
}
