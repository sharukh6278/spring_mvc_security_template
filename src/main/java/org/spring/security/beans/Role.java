package org.spring.security.beans;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity(name = "Watch_Earn_Role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    User user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy="role")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Privilege> privilegeList;


    @Override
    public String getAuthority() {
        return name;
    }

    public  Role(){}

    public Role(Long id, String name, User user, List<Privilege> privilegeList) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.privilegeList = privilegeList;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", privilegeList=" + privilegeList +
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Privilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<Privilege> privilegeList) {
        this.privilegeList = privilegeList;
    }
}