package com.everis.alicante.courses.beca.java.friendsnet.controller;

import com.everis.alicante.courses.beca.java.friendsnet.entity.Event;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Person;
import com.everis.alicante.courses.beca.java.friendsnet.manager.EventManager;
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
public class EventController {

    @Autowired
    private EventManager eventManager;

    @Autowired
    private PersonManager personManager;

    @GetMapping(value = "/event")
    public List<Event> getAll() {
        return eventManager.findAll();
    }

    @GetMapping(value = "/event/{id}")
    public Event getById(@PathVariable String id) {
        //Parseo id PathVariable para ver si es entero, si va sigue, si no null.
        try {
            int tempId = Integer.parseInt(id);
            return eventManager.findById(tempId);
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    // Return lista de eventos, una persona puede ser parte de muchos eventos.
    @GetMapping(value = "/event/person/{id}")
    public List<Event> getByPersonId(@PathVariable String id) {
       
        try {
            int tempId = Integer.parseInt(id);
            Person person = personManager.findById(tempId);
            List<Event> events = new ArrayList<>();
            //Se comprueba si las personas pertenecen al evento, si no lo añade.
            for (Event event : eventManager.findAll()) {
                for (Person tempPerson : event.getPersons()) {
                    if (tempPerson.getId() == tempId) {
                        events.add(event);
                    }
                }
            }
            return events;
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    @PostMapping(value = "/event")
    public void create(@RequestBody Event event) {
        //Se comprueba si las listas de personas en eventos esta vacia, si lo esta el evento no se crea.(no puede estar un evento sin persona)
        if (event.getPersons().isEmpty()) {
            return;
        } else {
            Set<Person> personSet = new HashSet<>();
            List<Person> personsList = personManager.findAll();
            for (Person person : event.getPersons()) {
                for (Person personFromList : personsList) {
                    if (person.getId() == personFromList.getId()) {
                        personSet.add(personFromList);
                    }
                }
            }
            event.setPersons(personSet);
            eventManager.save(event);
        }
    }

    //Update event, si añadimos persona al evento para que lo guarde.
    @PutMapping(value = "/event")
    public void update(@RequestBody Event event) {
        if (event.getPersons().isEmpty()) {
            eventManager.remove(event.getId());
        } else {
            eventManager.update(event);
        }
    }

    @DeleteMapping(value = "/event/{id}")
    public void remove(@PathVariable String id) {
       
        try {
            int tempId = Integer.parseInt(id);
            eventManager.remove(tempId);
        } catch (NumberFormatException exception) {
            return;
        }
    }
}
