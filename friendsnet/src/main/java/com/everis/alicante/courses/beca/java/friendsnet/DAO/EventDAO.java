package com.everis.alicante.courses.beca.java.friendsnet.DAO;

import com.everis.alicante.courses.beca.java.friendsnet.entity.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventDAO extends CrudRepository<Event, Integer> {
}
