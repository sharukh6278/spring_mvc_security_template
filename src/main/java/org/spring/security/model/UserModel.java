package org.spring.security.model;

import org.spring.security.beans.Role;

import java.io.Serializable;
import java.util.List;

public class UserModel implements Serializable {

    private String id;
    private String name;
    private String email;
    private String password;

    private String stdInClass;

    private boolean blocked;
    private List<Role> roles;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStdInClass() {
        return stdInClass;
    }

    public void setStdInClass(String stdInClass) {
        this.stdInClass = stdInClass;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", stdInClass='" + stdInClass + '\'' +
                ", roles=" + roles +
                '}';
    }
}

