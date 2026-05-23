package com.tony.security_module.principal;


import java.util.List;

public class UserPrincipal {
    private final String id;
    private final String email;
    private final String role;
    private final List<String> permissions;


    public UserPrincipal(String id, String email, String role, List<String> permissions) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.permissions = permissions;
    }


    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public List<String> getPermissions() {
        return permissions;
    }

}
