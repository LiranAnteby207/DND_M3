public class Warrior extends Player{
    protected int abilityCooldown;
    protected int remainingCooldown = 0;
    protected Warrior(char tile, String name, int healthCapacity, int attack, int defense, int abilityCooldown){
        super(tile, name, healthCapacity, attack, defense);
        this.abilityCooldown = abilityCooldown;
    }
    public int getAbilityCooldown(){ return this.abilityCooldown;}
    public int getRemainingCooldown(){ return this.remainingCooldown;}
    public void setRemainingCooldown(int update){ this.remainingCooldown = update;}
    public void setWarriorExperience(int exp){
        setExperience(exp);
        //eirjjgnergiiuunereregluuhhbq3gkluhb3qgouiyhbq3gpoiuybq34ggpbq23gpiiubn3rgg
    }
}
