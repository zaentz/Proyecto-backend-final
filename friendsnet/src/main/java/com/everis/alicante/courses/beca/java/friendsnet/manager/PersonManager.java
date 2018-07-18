package com.everis.alicante.courses.beca.java.friendsnet.manager;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.PersonDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Person;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonManager {
    
    @Autowired
    private PersonDAO personDAO;
    
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        // Lleno la lista con eventos cargados, Java 8 stuff
        personDAO.findAll().forEach(persons::add);
        return persons;
    }
    
    public Person findById(int id) {
        return personDAO.findById(id).orElse(null);
    }
    
    public void save(Person person) {
        personDAO.save(person);
    }
    
    public void update(Person person) {
        personDAO.save(person);
    }
    
    public void remove(int id) {
        personDAO.deleteById(id);
    }
}
