package Game.Tiles.Units.Enemies;

import Game.Tiles.Empty;
import Game.Tiles.Units.Players.Player;
import Game.Tiles.Units.Unit;
import Game.Utils.ConsoleColors;

public abstract class Enemy extends Unit {
    protected int experienceValue;
    protected Enemy(char tile, String name, int healthCapacity, int attack, int defense, int experienceValue){
        super(tile, name, healthCapacity, attack, defense);
        this.experienceValue = experienceValue;
    }
    public int getExperienceValue(){
        return experienceValue;
    }
    public void accept(Unit u){
        u.visit(this);
    }
    public void visit(Player p){
        p.attack(this);
        if(p.isDead())
            p.onDeath();
    }
    public void gotAttackedAbilityCast(double damageTry, Player p){
        double defenseRnd = (Math.random() * (this.attackPoints + 1));
        double score = damageTry - defenseRnd;
        if(score > 0){
            this.health.setHealthAmount(this.health.getHealthAmount() - (int)score);
            messageCallback.send(String.format("%s got ability cast damage %f from %s, now %s has %d health amount from %d",this.name,score,p.getName(),this.name,this.health.getHealthAmount(),this.health.getHealthPool()));
        }
        else
            messageCallback.send(String.format("%s tried to ability cast damage %s without success",p.getName(), this.name));
        if(isDead())
            onAbilityDeath(p);
    }
    public void onDeath(Player p){
        p.setExperience(this.getExperienceValue());
        StringBuilder s = new StringBuilder();
        s.append(ConsoleColors.GREEN).append(String.format("%s died. %s gained %d experience.", this.getName(), p.getName(), this.getExperienceValue())).append(ConsoleColors.RESET);
        messageCallback.send(s.toString());
        Empty e = new Empty(position);
        gameManager.gameBoard.emptys.add(e);
        gameManager.gameBoard.enemies.remove(this);
        e.swap(p);
    }
    public void onAbilityDeath(Player p){
        p.setExperience(this.getExperienceValue());
        StringBuilder s = new StringBuilder();
        s.append(ConsoleColors.GREEN).append(String.format("%s died. %s gained %d experience.", this.getName(), p.getName(), this.getExperienceValue())).append(ConsoleColors.RESET);
        messageCallback.send(s.toString());
        Empty e = new Empty(position);
        gameManager.gameBoard.emptys.add(e);
        gameManager.gameBoard.enemies.remove(this);
    }
    public abstract void onTick();
    public void visit(Enemy e){}
    public void setExperienceValue(int experienceValue) {
        this.experienceValue += experienceValue;
    }
}
