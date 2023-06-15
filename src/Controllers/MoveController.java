package Controllers;

import Game.GameBoard;
import Game.Callbacks.MessageCallback;
import Game.Tiles.Units.Enemies.Enemy;
import Game.Tiles.Units.Players.Player;
import Game.Utils.Position;
import Input.InputFromUser;
import View.Input.InputProvider;


public class MoveController {
    public GameBoard gameBoard;
    private static MessageCallback messageCallback;


    // receive a move and a player and commit the movement
    public static void move(InputFromUser input, Player player){
        Position moveTo = moveStep(input, player.getPosition());
        Enemy target = targetCondidate(moveTo, player);
        if (target == null){
            player.setPosition(moveTo);
        }
        else {
            combat(player, target);
            player.attack(target);
        }
    }

    private static Enemy targetCondidate(Position moveTo, Player player) {
    }

    // update the position of the unit and committed move
    private static Position moveStep(InputController input, Position pos) {
        Position newPosition = pos.copy();
        switch (input) {
            case Right -> newPosition.x++;
            case Left -> newPosition.x--;
            case Up -> newPosition.y--;
            case Down -> newPosition.y++;
        }
        if (!isValidMove(newPos))
            newPosition = pos.copy();
        return newPosition;
    }


}
