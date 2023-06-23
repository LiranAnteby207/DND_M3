package Game.Tiles.Units.Players;

import Game.Callbacks.MessageCallback;
import Game.Tiles.Units.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {
    private MessageCallback ms = s -> System.out.println(s);
    private  Mage Melisandre;



    @BeforeEach
    void setUp() {
        Unit.messageCallback = ms;
        Melisandre = new Mage('@',"Melisandre",100,5, 1, 300, 30, 15, 5, 6);
    }

    @Test
    void copy() {
        assertEquals(new Mage('@',"Melisandre",100,5, 1, 300, 30, 15, 5, 6).describe(), Melisandre.describe());
    }

    @Test
    void describe(){
        assertEquals("Mage Melisandre level 1 has: health amount: 100 out of 100, attack: 5, defence: 1 \n" +
                " has mana pool 300, mana cost 30, spell power 15, hit count 5, mage range 6 .", Melisandre.describe());
    }

    @Test
    void levelUp() {
        Melisandre.levelUp();
        assertEquals(2, Melisandre.getLevel());
    }
}