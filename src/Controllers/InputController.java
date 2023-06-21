package Controllers;

import Input.InputFromUser;

import java.util.Scanner;

public class InputController {
    public static char inputCache(){
        Scanner myScr = new Scanner(System.in);
        return myScr.next().charAt(0);
    }
}

