package io.tacsio.mercadolivre.config.security;

public enum ApplicationPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),
    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write"),
    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write");

    private final String permission;

    ApplicationPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
