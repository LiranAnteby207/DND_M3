package Game.Tiles.Units.Players;

import Game.Callbacks.MessageCallback;
import Game.Tiles.Units.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WarriorTest {
    private MessageCallback ms = s -> System.out.println(s);
    private Warrior JonSnow;



    @BeforeEach
    void setUp() {
        Unit.messageCallback = ms;

        JonSnow = new Warrior('@',"Jon Snow", 300, 30, 4, 3);
    }

    @Test
    void copy() {
        assertEquals(new Warrior('@',"Jon Snow", 300, 30, 4, 3).describe(),JonSnow.describe());
    }

    @Test
    void describe() {
        assertEquals("Warrior Jon Snow level 1 has: health amount: 300 out of 300, attack: 30, defense: 4 remaining coolDown left: 0", JonSnow.describe());
    }

    @Test
    void levelUp() {
        JonSnow.levelUp();
        assertEquals(2, JonSnow.getLevel());
    }
}