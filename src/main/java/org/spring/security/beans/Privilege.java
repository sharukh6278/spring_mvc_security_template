package org.spring.security.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Privilege")
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    Role role;

    public Privilege(){}

    public Privilege(Long id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
