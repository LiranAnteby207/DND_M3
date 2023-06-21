package Game.Tiles.Units.Players;

public class Warrior extends Player {
    protected int abilityCooldown;
    protected int remainingCooldown = 0;
    public Warrior(char tile, String name, int healthCapacity, int attack, int defense, int abilityCooldown){
        super(tile, name, healthCapacity, attack, defense);
        this.abilityCooldown = abilityCooldown;
    }
    public Warrior copy(){
        return new Warrior(this.tile, this.name, this.health.getHealthPool(), this.attackPoints, this.defensePoints, this.abilityCooldown);
    }
    public String describe(){
        return String.format("Warrior %s level %s has: health amount: %s out of %s, remaining coolDown left: %s", this.name,this.level, this.health.getHealthAmount(),this.health.getHealthPool(),this.remainingCooldown);
    }
    public void abilityCast(){

    }
    public int getAbilityCooldown(){ return this.abilityCooldown;}
    public int getRemainingCooldown(){ return this.remainingCooldown;}
    public void setRemainingCooldown(int update){ this.remainingCooldown = update;}
    public void setWarriorExperience(int exp){
        setExperience(exp);
        //eirjjgnergiiuunereregluuhhbq3gkluhb3qgouiyhbq3gpoiuybq34ggpbq23gpiiubn3rgg
    }
}
