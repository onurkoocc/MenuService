package com.qrmenu.MenuService.config.security;

import java.util.UUID;

public class UserContextHolder {

    private static final ThreadLocal<UUID> userId = new ThreadLocal<>();
    private static final ThreadLocal<String> userRole = new ThreadLocal<>();

    public static void setUserId(UUID id) {
        userId.set(id);
    }

    public static UUID getUserId() {
        return userId.get();
    }

    public static void setUserRole(String role) {
        userRole.set(role);
    }

    public static String getUserRole() {
        return userRole.get();
    }

    public static void clear() {
        userId.remove();
        userRole.remove();
    }
}
