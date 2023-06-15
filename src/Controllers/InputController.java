package Controllers;

import Input.InputFromUser;

import java.util.Scanner;

public class InputController {
    private static String userInputRegex = "[" + InputFromUser.CastAbility.getRegex() + "]";
    private static String menuRegex = "[1-" + InputFromUser.playerPool.size() + "]";

    // get the player choice
    public static InputFromUser inputPlayerGame() {
        return InputFromUser.FindByKey(inputCache(userInputRegex) + "");
    }
    private static char inputCache(String regex) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    }
}

