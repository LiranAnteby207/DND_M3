package Controllers;

import Input.InputFromUser;

import java.util.Scanner;

public static class InputController {
    public static char inputCache(){
        Scanner myScr = new Scanner(System.in);
        return myScr.next().charAt(0);
    }
//    private static String userInputRegex = "[" + InputFromUser.CastAbility.getRegex() + "]";
//    private static String menuRegex = "[1-" + InputFromUser.playerPool.size() + "]";
//
//    // get the player choice
//    public static InputFromUser inputPlayerGame() {
//        return InputFromUser.FindByKey(inputCache(userInputRegex) + "");
//    }
//    private static char inputCache(String regex) {
//        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//    }
}

