package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public enum MoveDirection {
    FORWARD("f", "Zwierzak idzie do przodu"),
    BACKWARD("b", "Zwierzak idzie do tyłu"),
    RIGHT("r", "Zwierzak skręca w prawo"),
    LEFT("l", "Zwierzak skręca w lewo");

    public final String code;
    public final String msg;
    MoveDirection(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private static final Map<String, MoveDirection> VALUE_MAP = new HashMap<>();
    static {
        for (MoveDirection dir : MoveDirection.values()) {
            VALUE_MAP.put(dir.code, dir);
        }
    }

    public static MoveDirection fromCode(String code) {
        return VALUE_MAP.get(code);
    }

    public static boolean allowed(String code) {
        return VALUE_MAP.containsKey(code);
    }
}
