package com.everis.alicante.courses.beca.java.friendsnet.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
//  Renomrbo la tabla de la db, ya que "Like" es una palabra reserva de SQL
@Table(name = "FMLike")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDateTime creationDate;
    private LikeType type;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Person person;

    public Like() {
    }

    public Like(int id, LocalDateTime creationDate, LikeType type, Post post, Person person) {
        this.id = id;
        this.creationDate = creationDate;
        this.type = type;
        this.post = post;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LikeType getType() {
        return type;
    }

    public void setType(LikeType type) {
        this.type = type;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
