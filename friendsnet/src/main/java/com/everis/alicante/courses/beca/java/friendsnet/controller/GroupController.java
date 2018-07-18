package com.everis.alicante.courses.beca.java.friendsnet.controller;

import com.everis.alicante.courses.beca.java.friendsnet.entity.Group;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Person;
import com.everis.alicante.courses.beca.java.friendsnet.manager.GroupManager;
import com.everis.alicante.courses.beca.java.friendsnet.manager.PersonManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

    @Autowired
    private GroupManager groupManager;

    @Autowired
    private PersonManager personManager;

    @GetMapping(value = "/group")
    public List<Group> getAll() {
        return groupManager.findAll();
    }

    @GetMapping(value = "/group/{id}")
    public Group getById(@PathVariable String id) {
    	 //Parseo id PathVariable para ver si es entero, si va sigue, si no null.
        try {
            int tempId = Integer.parseInt(id);
            return groupManager.findById(tempId);
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    // Retunr una lista de groups para el id de la persona, una persona puede estar en muchos grupos.
    @GetMapping(value = "/group/person/{id}")
    public List<Group> getByPersonId(@PathVariable String id) {
        try {
            int tempId = Integer.parseInt(id);
            Person person = personManager.findById(tempId);
            List<Group> groups = new ArrayList<>();
            for (Group group : groupManager.findAll()) {
                for (Person tempPerson : group.getPersons()) {
                    if (tempPerson.getId() == tempId) {
                        groups.add(group);
                    }
                }
            }
            return groups;
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    @PostMapping(value = "/group")
    public void create(@RequestBody Group group) {
        if (group.getPersons().isEmpty()) {
            return;
        } else {
            Set<Person> personSet = new HashSet<>();
            List<Person> personsList = personManager.findAll();
            for (Person person : group.getPersons()) {
                for (Person personFromList : personsList) {
                    if (person.getId() == personFromList.getId()) {
                        personSet.add(personFromList);
                    }
                }
            }
            group.setPersons(personSet);
            groupManager.save(group);
        }
    }

    
    @PutMapping(value = "/group")
    public void update(@RequestBody Group group) {
       
        if (group.getPersons().isEmpty()) {
            groupManager.remove(group.getId());
        } else {
            groupManager.update(group);
        }
    }

    @DeleteMapping(value = "/group/{id}")
    public void remove(@PathVariable String id) {
        try {
            int tempId = Integer.parseInt(id);
            groupManager.remove(tempId);
        } catch (NumberFormatException exception) {
            return;
        }
    }
}
