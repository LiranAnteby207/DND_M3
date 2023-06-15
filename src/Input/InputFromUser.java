package Input;

import java.util.HashMap;
import java.util.Map;

public class InputFromUser {

    private static final Map<String, InputFromUser> inputMap = new HashMap<>();
    private String key;

    public static final InputFromUser Up = new InputFromUser("w");
    public static final InputFromUser Down = new InputFromUser("s");
    public static final InputFromUser Left = new InputFromUser("a");
    public static final InputFromUser Right = new InputFromUser("d");
    public static final InputFromUser CastAbility = new InputFromUser("e");
    public static final InputFromUser Wait = new InputFromUser("q");

    public InputFromUser(String key) {
        this.key = key;
    }

    //The reason to use static in your code is to create a single instance of each
    // Input.Controllers.InputController object that can be accessed without needing to create multiple
    // instances of the class. This is useful when you want to have predefined instances
    // that are shared across different parts of your code.
    static {
        inputMap.put("w",Up);
        inputMap.put("s", Down);
        inputMap.put("a", Left);
        inputMap.put("d", Right);
        inputMap.put("e", CastAbility);
        inputMap.put("q", Wait);
    }


    // getter method
    public String GetKey() {
        return this.key;
    }

    public static String getRegex() {
        StringBuilder regex = new StringBuilder();
        for (InputFromUser input : inputMap.values()) {
            regex.append(input.GetKey());
        }
        return regex.toString();
    }

    public static InputFromUser FindByKey(String key) {
        return inputMap.get(key);
    }
}
