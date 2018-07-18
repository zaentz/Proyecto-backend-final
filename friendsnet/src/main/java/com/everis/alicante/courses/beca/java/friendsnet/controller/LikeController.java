package com.everis.alicante.courses.beca.java.friendsnet.controller;

import com.everis.alicante.courses.beca.java.friendsnet.entity.Like;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Person;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Post;
import com.everis.alicante.courses.beca.java.friendsnet.manager.LikeManager;
import com.everis.alicante.courses.beca.java.friendsnet.manager.PersonManager;
import com.everis.alicante.courses.beca.java.friendsnet.manager.PostManager;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeController {

    @Autowired
    private LikeManager likeManager;

    @Autowired
    private PersonManager personManager;

    @Autowired
    private PostManager postManager;

    @GetMapping(value = "/like")
    public List<Like> getAll() {
        return likeManager.findAll();
    }

    @GetMapping(value = "/like/{id}")
    public Like getById(@PathVariable String id) {
    	 //Parseo id PathVariable para ver si es entero, si va sigue, si no null.
        try {
            int tempId = Integer.parseInt(id);
            return likeManager.findById(tempId);
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    @GetMapping(value = "/like/person/{id}")
    public List<Like> getByPersonId(@PathVariable String id) {
        try {
            int tempId = Integer.parseInt(id);
            Person person = personManager.findById(tempId);
            List<Like> likes = new ArrayList<>();
            
            for (Like like : likeManager.findAll()) {
                if (like.getPerson() == person) {
                    likes.add(like);
                }
            }
            return likes;
        } catch (NumberFormatException exception) {
            return null;
        }
    }

   
    @GetMapping(value = "/like/post/{id}")
    public List<Like> getByEventId(@PathVariable String id) {
   
        try {
            int tempId = Integer.parseInt(id);
            Post post = postManager.findById(tempId);
            List<Like> likes = new ArrayList<>();

            for (Like like : likeManager.findAll()) {
                if (like.getPost() == post) {
                    likes.add(like);
                }
            }
            return likes;
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    @PostMapping(value = "/like")
    public void create(@RequestBody Like like) {
        likeManager.save(like);
    }

    @PutMapping(value = "/like")
    public void update(@RequestBody Like like) {
        likeManager.update(like);
    }

    @DeleteMapping(value = "/like/{id}")
    public void remove(@PathVariable String id) {
        try {
            int tempId = Integer.parseInt(id);
            likeManager.remove(tempId);
        } catch (NumberFormatException exception) {
            return;
        }
    }
}
