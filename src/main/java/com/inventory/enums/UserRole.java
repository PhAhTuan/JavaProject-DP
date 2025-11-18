package com.inventory.enums;

public enum UserRole {
    ADMIN,
    MANAGER,
    ANALYST,
    USER,
    UNKNOWN;
    
    public static UserRole fromInt(Integer role) {
        if (role == null) return UNKNOWN;
        return switch (role) {
            case 1 -> ADMIN;
            case 2 -> MANAGER;
            case 3 -> ANALYST;
            case 4 -> USER;
            default -> UNKNOWN;
        };
    }
}