package agh.ics.oop.map.element;

import java.util.HashMap;
import java.util.Map;

public enum MoveDirection {
    FORWARD("f", "forward", "Zwierzak idzie do przodu"),
    BACKWARD("b", "backward", "Zwierzak idzie do tyłu"),
    RIGHT("r", "right", "Zwierzak skręca w prawo"),
    LEFT("l", "left", "Zwierzak skręca w lewo");

    public final String code;
    public final String fullcode;
    public final String msg;
    MoveDirection(String code, String fullcode, String msg) {
        this.code = code;
        this.fullcode = fullcode;
        this.msg = msg;
    }

    private static final Map<String, MoveDirection> VALUE_MAP = new HashMap<>();
    static {
        for (MoveDirection dir : MoveDirection.values()) {
            VALUE_MAP.put(dir.code, dir);
            VALUE_MAP.put(dir.fullcode, dir);
        }
    }

    public static MoveDirection fromCode(String code) {
        return VALUE_MAP.get(code);
    }

    public static boolean allowed(String code) {
        return VALUE_MAP.containsKey(code);
    }
}
