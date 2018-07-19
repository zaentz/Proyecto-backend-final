package com.everis.alicante.courses.beca.java.friendsnet.event;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.EventDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Event;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventDAOTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventDAO eventDAO;

    @Test
    public void findByIdTest() {
        Event event = new Event();
        event.setName("TestEvent");
        entityManager.persist(event);
        entityManager.flush();

        Event eventFromDB = eventDAO.findById(event.getId()).orElse(null);

        Assertions.assertThat(eventFromDB.getName()).isEqualTo(event.getName());
    }
}
