package com.everis.alicante.courses.beca.java.friendsnet.manager;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.LikeDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Like;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeManager {

    @Autowired
    private LikeDAO likeDAO;

    public List<Like> findAll() {
        List<Like> likes = new ArrayList<>();
        // Lleno la lista con eventos cargados, Java 8 stuff
        likeDAO.findAll().forEach(likes::add);
        return likes;
    }

    public Like findById(int id) {
        return likeDAO.findById(id).orElse(null);
    }

    public void save(Like like) {
        likeDAO.save(like);
    }

    public void update(Like like) {
        likeDAO.save(like);
    }

    public void remove(int id) {
        likeDAO.deleteById(id);
    }
}
