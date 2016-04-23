package me.service;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by chn on 16/1/23.
 */
@Entity
public class Affiliation {

    @Id
    long affId;

    @Column
    String name;


    @ManyToMany(cascade = CascadeType.ALL)
    public Set<Employee> members;

    double calcCharge() {
        return 0;
    }

    public long getAffId() {
        return affId;
    }

    public void setAffId(long affId) {
        this.affId = affId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getMembers() {
        return members;
    }

    public void setMembers(Set<Employee> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return this.getClass().getCanonicalName();
    }

}
