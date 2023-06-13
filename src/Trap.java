public class Trap extends Enemy{
    protected int visibilityTime;
    protected int invisibilityTime;
    protected int ticksCount = 0;
    protected boolean visible = true;
    protected Trap(int visibilityTime, int invisibilityTime, String name, int healthCapacity, int attack, int defense){
        super('T',name, healthCapacity, attack, defense);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
    }

}
