package Game.Tiles.Units.Players;

import Game.Callbacks.MessageCallback;
import Game.Tiles.Units.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RogueTest {
    private Rogue AryaStark;
    private MessageCallback ms = s -> System.out.println(s);


    @BeforeEach
    void setUp() {
        Unit.messageCallback = ms;
        AryaStark = new Rogue('@', "Arya Stark" ,150, 40, 2,20);
    }
    @Test
    void copy() {
        assertEquals(new Rogue('@', "Arya Stark" ,150, 40, 2,20).describe(),AryaStark.copy().describe());
    }
    
    @Test
    void levelUp() {
        AryaStark.levelUp();
        assertEquals(2, AryaStark.getLevel());
    }
}