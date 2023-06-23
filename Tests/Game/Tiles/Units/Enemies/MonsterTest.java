package Game.Tiles.Units.Enemies;

import Game.Callbacks.MessageCallback;
import Game.GameBoard;
import Game.Tiles.Units.Players.Rogue;
import Game.Utils.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MonsterTest {
    private Monster LannisterSolider;
    private MessageCallback ms = s -> System.out.println(s);
   // private GameBoard gameBoard = new GameBoard();


    @BeforeEach
    void setUp() {
        LannisterSolider = new Monster('s',"Lannister Solider",80,8,3,3,25);
    }

    @Test
    void copy() {
        assertEquals(new Monster('s',"Lannister Solider",80,8,3,3,25).toString(), LannisterSolider.toString());
    }

//    @Test
//    void onTick(){
//        Position p = LannisterSolider.getPosition();
//        LannisterSolider.onTick();
//        assertNotEquals(p,LannisterSolider.getPosition());
//    }
}