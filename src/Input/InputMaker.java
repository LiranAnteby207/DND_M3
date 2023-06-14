package Input;

public enum InputMaker {
    Up("w"),
    Down("s"),
    Left("a"),
    Right("d"),
    CastAbility("e"),
    Wait("q");

    private String key;

    InputMaker(String key) {
        this.key = key;
    }

    // getter method
    public String GetKey() {
        return this.key;
    }

    public String getRegex() {
        String regex = "";
        for (InputMaker inp : InputMaker.values()) {
            regex += inp.GetKey();
        }
        return regex;
    }

    public static InputMaker FindByKey(String key) {
        for (InputMaker v : values()) {
            if (v.key.equals(key)) {
                return v;
            }
        }
        return null;
    }
}