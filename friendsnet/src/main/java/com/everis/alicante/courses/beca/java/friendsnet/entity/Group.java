package com.everis.alicante.courses.beca.java.friendsnet.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
// Renomrbo la tabla de la db, ya que "Group" es una palabra reserva de SQL
@Table(name = "FMGroup")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private byte[] picture;

    @ManyToMany
    private Set<Person> persons;

    public Group() {
    }

    public Group(int id, String name, byte[] picture, Set<Person> persons) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.persons = persons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
