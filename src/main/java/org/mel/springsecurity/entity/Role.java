package org.mel.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Role implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String desc;

    @ManyToMany(cascade = { CascadeType.REFRESH }, fetch =  FetchType.EAGER,mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;

    public Role(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Role() {

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
