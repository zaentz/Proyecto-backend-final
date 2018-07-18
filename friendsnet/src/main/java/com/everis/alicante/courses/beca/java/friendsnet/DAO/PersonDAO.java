package com.everis.alicante.courses.beca.java.friendsnet.DAO;

import com.everis.alicante.courses.beca.java.friendsnet.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonDAO extends CrudRepository<Person, Integer> {
}
