package com.everis.alicante.courses.beca.java.friendsnet.controller;

import com.everis.alicante.courses.beca.java.friendsnet.entity.Person;
import com.everis.alicante.courses.beca.java.friendsnet.manager.PersonManager;

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
public class PersonController {

    @Autowired
    private PersonManager personManager;

    @GetMapping(value = "/person")
    public List<Person> getAll() {
        return personManager.findAll();
    }

    @GetMapping(value = "/person/{id}")
    public Person getById(@PathVariable String id) {
    	 //Parseo id PathVariable para ver si es entero, si va sigue, si no null.
        try {
            int tempId = Integer.parseInt(id);
            return personManager.findById(tempId);
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    @PostMapping(value = "/person")
    public void create(@RequestBody Person person) {
        personManager.save(person);
    }

    @PutMapping(value = "/person")
    public void update(@RequestBody Person person) {
        personManager.update(person);
    }

    @DeleteMapping(value = "/person/{id}")
    public void remove(@PathVariable String id) {
        try {
            int tempId = Integer.parseInt(id);
            personManager.remove(tempId);
        } catch (NumberFormatException exception) {
            return;
        }
    }
}
