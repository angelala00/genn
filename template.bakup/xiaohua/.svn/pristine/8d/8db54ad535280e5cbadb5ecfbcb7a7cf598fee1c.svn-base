package com.cjteam.xiao.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by ChenLong on 14-3-3.
 */
public class Role implements GrantedAuthority {
    private static final long serialVersionUID = 1L;
    private String name;

    public Role(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Role [name=");
        builder.append(name);
        builder.append("]");
        return builder.toString();
    }
}
