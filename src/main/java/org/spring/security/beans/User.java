package org.spring.security.beans;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "Watch_Earn_User")
@Entity
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "blocked")
    private boolean blocked;

    @Column(name = "stdInClass")
    private String stdInClass ;

    @CreatedDate
    @Temporal(TemporalType.DATE)
    @Column(name = "createdDate")
    protected Date createdDate=new Date();

    @OneToMany(cascade = CascadeType.ALL,mappedBy="user")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Role> roles;

    /*@OneToMany(cascade = CascadeType.ALL,mappedBy="user")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Book> bookList;*/

    public User(){}

    public User(long id, String name, String email, String password, boolean blocked, String stdInClass, Date createdDate, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.blocked = blocked;
        this.stdInClass = stdInClass;
        this.createdDate = createdDate;
        this.roles = roles;

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", blocked=" + blocked +
                ", stdInClass='" + stdInClass + '\'' +
                ", createdDate=" + createdDate +
                ", roles=" + roles +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getStdInClass() {
        return stdInClass;
    }

    public void setStdInClass(String stdInClass) {
        this.stdInClass = stdInClass;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}