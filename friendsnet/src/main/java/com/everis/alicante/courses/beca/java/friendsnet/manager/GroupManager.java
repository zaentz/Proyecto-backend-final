package com.everis.alicante.courses.beca.java.friendsnet.manager;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.GroupDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Group;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupManager {

    @Autowired
    private GroupDAO groupDAO;

    public List<Group> findAll() {
        List<Group> groups = new ArrayList<>();
        // Lleno la lista con eventos cargados, Java 8 stuff
        groupDAO.findAll().forEach(groups::add);
        return groups;
    }

    public Group findById(int id) {
        return groupDAO.findById(id).orElse(null);
    }

    public void save(Group group) {
        groupDAO.save(group);
    }

    public void update(Group group) {
        groupDAO.save(group);
    }

    public void remove(int id) {
        groupDAO.deleteById(id);
    }
}
