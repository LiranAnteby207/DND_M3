package Game.Tiles.Units.Players;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RogueTest {
    private Rogue AryaStark;

    @BeforeEach
    void setUp() {
        AryaStark = new Rogue('@', "Arya Stark" ,150, 40, 2,20);
    }

    @Test
    void copy() {
        assertEquals(new Rogue('@', "Arya Stark" ,150, 40, 2,20),AryaStark.copy());
    }

    @Test
    void onTick() {
    }

    @Test
    void abilityCast() {
    }

    @Test
    void levelUp() {
    }
}