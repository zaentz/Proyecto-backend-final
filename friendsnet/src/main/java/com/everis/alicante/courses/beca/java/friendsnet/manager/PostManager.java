package com.everis.alicante.courses.beca.java.friendsnet.manager;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.PostDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Post;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostManager {

    @Autowired
    private PostDAO postDAO;

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        // Lleno la lista con eventos cargados, Java 8 stuff
        postDAO.findAll().forEach(posts::add);
        return posts;
    }

    public Post findById(int id) {
        return postDAO.findById(id).orElse(null);
    }

    public void save(Post post) {
        postDAO.save(post);
    }

    public void update(Post post) {
        postDAO.save(post);
    }

    public void remove(int id) {
        postDAO.deleteById(id);
    }
}
