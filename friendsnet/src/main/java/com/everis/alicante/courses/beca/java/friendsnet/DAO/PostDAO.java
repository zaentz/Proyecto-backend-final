package com.everis.alicante.courses.beca.java.friendsnet.DAO;

import com.everis.alicante.courses.beca.java.friendsnet.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostDAO extends CrudRepository<Post, Integer> {
}
