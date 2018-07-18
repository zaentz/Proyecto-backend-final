package com.everis.alicante.courses.beca.java.friendsnet.manager;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.EventDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Event;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventManager {

    @Autowired
    private EventDAO eventDAO;

    public List<Event> findAll() {
        List<Event> events = new ArrayList<>();
        // Lleno la lista con eventos cargados, Java 8 stuff
        eventDAO.findAll().forEach(events::add);
        return events;
    }

    public Event findById(int id) {
        return eventDAO.findById(id).orElse(null);
    }

    public void save(Event event) {
        eventDAO.save(event);
    }

    public void update(Event event) {
        eventDAO.save(event);
    }

    public void remove(int id) {
        eventDAO.deleteById(id);
    }
}
