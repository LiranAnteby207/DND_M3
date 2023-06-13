public class Health {
    protected int HealthPool;
    protected int HealthAmount;
    public Health(int healthCapacity){
        this.HealthAmount = healthCapacity;
        this.HealthPool = healthCapacity;
    }

    public int getHealthAmount() {
        return HealthAmount;
    }

    public void setHealthAmount(int healthAmount) {
        HealthAmount = healthAmount;
    }
    public void addHealthPool(int health){
        HealthPool += health;
    }
    public int getHealthPool() {
        return HealthPool;
    }

    public void setHealthPool(int healthPool) {
        HealthPool = healthPool;
    }
}
