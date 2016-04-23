package me.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by chn on 16/1/23.
 */
@Entity
public class Affiliation {

    @Id
    long affId;

    @Column
    String name;

    double calcDues() {
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

    @Override
    public String toString() {
        return this.getClass().getCanonicalName();
    }

}
