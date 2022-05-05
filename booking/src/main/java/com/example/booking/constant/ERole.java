package com.example.booking.constant;


import java.util.HashMap;
import java.util.Map;

public enum ERole {
    ADMIN(2, "ADMIN"),
    CUSTOMER(3, "CUSTOMER");

    private Integer type;
    private String typeInStr;

    ERole(Integer type, String typeInStr){
        this.type = type;
        this.typeInStr = typeInStr;
    }

    public Integer getValue() {
        return this.type;
    }

    public String getTypeInStr() {
        return typeInStr;
    }

    private static final Map<Integer, ERole> BY_TYPE = new HashMap<>();

    private static final Map<String, ERole> BY_STRING = new HashMap<>();

    static {
        for (ERole e : values()) {
            BY_TYPE.put(e.type, e);
        }
    }

    static {
        for (ERole e : values()) {
            BY_STRING.put(e.typeInStr, e);
        }
    }

    public static ERole valueOfType(Integer type) {
        return BY_TYPE.get(type);
    }
    public static ERole valueOfString(String value) {
        return BY_STRING.get(value);
    }
}
