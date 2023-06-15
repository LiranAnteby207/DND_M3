package Controllers;

import Game.Tiles.Units.Enemies.Enemy;
import Game.Tiles.Units.Enemies.Monster;
import Game.Tiles.Units.Enemies.Trap;
import Game.Tiles.Units.Players.Mage;
import Game.Tiles.Units.Players.Player;
import Game.Tiles.Units.Players.Rogue;
import Game.Tiles.Units.Players.Warrior;
import Game.Tiles.Units.Unit;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
public class UnitsController {
    public static Map<String, Player> Players = new HashMap<>();
    public static Map<String, Enemy> Enemies = new HashMap<>();
//    private final static String DataBase = "src/DataBase";
    public UnitsController(){
        buildUnit("/DataBase/DbPlayers"); // players list
        buildUnit("/DataBase/DbEnemies"); // enemies list();
    }
    public void buildUnit(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|"); // maybe bad reading
                createUnit(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createUnit(String[] data){
        if (data[0] == "Warrior") {
            String name = data[1];
            int health = Integer.parseInt(data[2]);
            int attack = Integer.parseInt(data[3]);
            int defense = Integer.parseInt(data[4]);
            int coolDown = Integer.parseInt(data[5]);
            Warrior warrior = new Warrior('@', name, health, attack, defense, coolDown);
            Players.put("Warrior", warrior);
        }
        if (data[0] == "Mage") {
            String name = data[1];
            int health = Integer.parseInt(data[2]);
            int attack = Integer.parseInt(data[3]);
            int defense = Integer.parseInt(data[4]);
            int ManaPool = Integer.parseInt(data[5]);
            int ManaCost = Integer.parseInt(data[6]);
            int SpellPower = Integer.parseInt(data[7]);
            int HitCount = Integer.parseInt(data[8]);
            int range = Integer.parseInt(data[9]);
            Mage mage = new Mage('@', name, health, attack, defense, ManaPool, ManaCost, SpellPower, HitCount, range);
            Players.put("Mage", mage);
        }
        if (data[0] == "Rogue") {
            String name = data[1];
            int health = Integer.parseInt(data[2]);
            int attack = Integer.parseInt(data[3]);
            int defense = Integer.parseInt(data[4]);
            int cost = Integer.parseInt(data[5]);
            Rogue rogue = new Rogue('@', name, health, attack, defense, cost);
            Players.put("Rogue", rogue);
        }
        if (data[0] == "Monster") {
            String name = data[1];
            char tile = data[2].charAt(0);
            int health = Integer.parseInt(data[3]);
            int attack = Integer.parseInt(data[4]);
            int defense = Integer.parseInt(data[5]);
            int visionRange = Integer.parseInt(data[6]);
            int ExperienceValue = Integer.parseInt(data[7]);
            Monster monster = new Monster(tile, name, health, attack, defense, visionRange, ExperienceValue);
            Enemies.put("Monster", monster);
        }
        if (data[0] == "Trap") {
            String name = data[1];
            char tile = data[2].charAt(0);
            int health = Integer.parseInt(data[3]);
            int attack = Integer.parseInt(data[4]);
            int defense = Integer.parseInt(data[5]);
            int ExperienceValue = Integer.parseInt(data[6]);
            int visibilityTime = Integer.parseInt(data[7]);
            int inVisibilityTime = Integer.parseInt(data[8]);
            Trap trap = new Trap(tile, name, health, attack, defense, ExperienceValue, visibilityTime, inVisibilityTime);
            Enemies.put("Trap", trap);
        }
    }
}
