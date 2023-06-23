package Game.Tiles.Units.Enemies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {
    private Trap BonusTrap;

    @BeforeEach
    void setUp() {
        BonusTrap = new Trap('B', "Bonus Trap", 1, 1 ,1, 250, 1, 5);
    }
    @Test
    void copy() {
        assertEquals(new Trap('B', "Bonus Trap", 1, 1 ,1, 250, 1, 5).toString(), BonusTrap.toString());
    }
}