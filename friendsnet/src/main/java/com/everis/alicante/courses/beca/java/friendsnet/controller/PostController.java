package com.everis.alicante.courses.beca.java.friendsnet.controller;

import com.everis.alicante.courses.beca.java.friendsnet.entity.Event;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Person;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Post;
import com.everis.alicante.courses.beca.java.friendsnet.manager.EventManager;
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
public class PostController {

    @Autowired
    private PostManager postManager;

    @Autowired
    private PersonManager personManager;

    @Autowired
    private EventManager eventManager;

    @GetMapping(value = "/post")
    public List<Post> getAll() {
        return postManager.findAll();
    }

    @GetMapping(value = "/post/{id}")
    public Post getById(@PathVariable String id) {
    	 //Parseo id PathVariable para ver si es entero, si va sigue, si no null.
        try {
            int tempId = Integer.parseInt(id);
            return postManager.findById(tempId);
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    // Lo mismo que en event
    @GetMapping(value = "/post/person/{id}")
    public List<Post> getByPersonId(@PathVariable String id) {
        try {
            int tempId = Integer.parseInt(id);
            Person person = personManager.findById(tempId);
            List<Post> posts = new ArrayList<>();
            //Se comprueba si la persona crea un post en particular... mismo que en eventos
            for (Post post : postManager.findAll()) {
                if (post.getPerson() == person) {
                    posts.add(post);
                }
            }
            return posts;
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    // Devuelve lista de post para un evento,en el evento se puedes crear muchos posts.
    @GetMapping(value = "/post/event/{id}")
    public List<Post> getByEventId(@PathVariable String id) {
        try {
            int tempId = Integer.parseInt(id);
            Event event = eventManager.findById(tempId);
            List<Post> posts = new ArrayList<>();
            for (Post post : postManager.findAll()) {
                if (post.getEvent() == event) {
                    posts.add(post);
                }
            }
            return posts;
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    @PostMapping(value = "/post")
    public void create(@RequestBody Post post) {
        postManager.save(post);
    }

    @PutMapping(value = "/post")
    public void update(@RequestBody Post post) {
        postManager.update(post);
    }

    @DeleteMapping(value = "/post/{id}")
    public void remove(@PathVariable String id) {
        try {
            int tempId = Integer.parseInt(id);
            postManager.remove(tempId);
        } catch (NumberFormatException exception) {
            return;
        }
    }
}
