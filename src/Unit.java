public abstract class Unit extends Tile{
    protected String name;
    protected Health health;
    protected int attackPoints;
    protected int defensePoints;
    protected Unit(char tile, String name, int healthCapacity, int attack, int defense){
        super(tile);
        this.name = name;
        this.health = new Health(healthCapacity);
        this.attackPoints = attack;
        this.defensePoints = defense;
    }

    public String getName() {
        return name;
    }

    public Health getHealth() {
        return health;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }
    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }
    protected void moveLeft(){
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

    }
    public void visit(Wall w){}
    public void visit(Empty e){
        this.swap(e);
    }
    public abstract void visit(Enemy e);
    public abstract void visit(Player p);

    public void interact(Tile tile){
        tile.accept(this);
    }
    protected void moveRight(){

    }
    protected void moveUp(){

    }
    protected void moveDown(){

    }
}
