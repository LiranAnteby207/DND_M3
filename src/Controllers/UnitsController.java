package Controllers;

import Game.Tiles.Units.Enemies.Enemy;
import Game.Tiles.Units.Enemies.Monster;
import Game.Tiles.Units.Enemies.Trap;
import Game.Tiles.Units.Players.*;

import java.io.*;
import java.util.*;
public class UnitsController {
    public static Map<String, List<Player>> Players = new HashMap<>();
    public static Map<String, Enemy> Enemies = new HashMap<>();
//    private final static String DataBase = "src/DataBase";
    public UnitsController(){
        buildUnit("src/DataBase/DbPlayers"); // players list
        buildUnit("src/DataBase/DbEnemies"); // enemies list();
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
        if (Objects.equals(data[0], "Warrior")) {
            String name = data[1];
            int health = Integer.parseInt(data[2]);
            int attack = Integer.parseInt(data[3]);
            int defense = Integer.parseInt(data[4]);
            int coolDown = Integer.parseInt(data[5]);
            Warrior warrior = new Warrior('@', name, health, attack, defense, coolDown);
            List<Player> warriorList = Players.getOrDefault("Warrior", new ArrayList<>());
            warriorList.add(warrior);
            Players.put("Warrior", warriorList);
        }if (Objects.equals(data[0], "Hunter")) {
            String name = data[1];
            int health = Integer.parseInt(data[2]);
            int attack = Integer.parseInt(data[3]);
            int defense = Integer.parseInt(data[4]);
            int range = Integer.parseInt(data[5]);
            Hunter hunter = new Hunter('@', name, health, attack, defense, range);
            List<Player> HunterList = Players.getOrDefault("Hunter", new ArrayList<>());
            HunterList.add(hunter);
            Players.put("Hunter", HunterList);
        }

        if (Objects.equals(data[0], "Mage")) {
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
            List<Player> MageList = Players.getOrDefault("Mage", new ArrayList<>());
            MageList.add(mage);
            Players.put("Mage", MageList);
        }
        if (Objects.equals(data[0], "Rogue")) {
            String name = data[1];
            int health = Integer.parseInt(data[2]);
            int attack = Integer.parseInt(data[3]);
            int defense = Integer.parseInt(data[4]);
            int cost = Integer.parseInt(data[5]);
            Rogue rogue = new Rogue('@', name, health, attack, defense, cost);
            List<Player> RogueList = Players.getOrDefault("Rogue", new ArrayList<>());
            RogueList.add(rogue);
            Players.put("Rogue", RogueList);
        }
        if (Objects.equals(data[0], "Monster")) {
            String name = data[1];
            char tile = data[2].charAt(0);
            int health = Integer.parseInt(data[3]);
            int attack = Integer.parseInt(data[4]);
            int defense = Integer.parseInt(data[5]);
            int visionRange = Integer.parseInt(data[6]);
            int ExperienceValue = Integer.parseInt(data[7]);
            Monster monster = new Monster(tile, name, health, attack, defense, visionRange, ExperienceValue);
            Enemies.put(tile+"", monster);
        }
        if (Objects.equals(data[0], "Trap")) {
            String name = data[1];
            char tile = data[2].charAt(0);
            int health = Integer.parseInt(data[3]);
            int attack = Integer.parseInt(data[4]);
            int defense = Integer.parseInt(data[5]);
            int ExperienceValue = Integer.parseInt(data[6]);
            int visibilityTime = Integer.parseInt(data[7]);
            int inVisibilityTime = Integer.parseInt(data[8]);
            Trap trap = new Trap(tile, name, health, attack, defense, ExperienceValue, visibilityTime, inVisibilityTime);
            Enemies.put(tile+"", trap);
        }
    }
}
