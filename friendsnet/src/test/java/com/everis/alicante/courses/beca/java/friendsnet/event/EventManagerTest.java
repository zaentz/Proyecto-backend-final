package com.everis.alicante.courses.beca.java.friendsnet.event;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.EventDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Event;
import com.everis.alicante.courses.beca.java.friendsnet.manager.EventManager;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EventManagerTest {

    @TestConfiguration
    static class EventManagerTestContextConfiguration {

        @Bean
        public EventManager eventManager() {
            return new EventManager();
        }
    }

    @Autowired
    private EventManager eventManager;

    @MockBean
    private EventDAO eventDAO;

    @Before
    public void setUp() {
        Event event = new Event();
        event.setId(1);
        event.setName("TestEvent");

        Mockito.when(eventDAO.findById(event.getId())).thenReturn(Optional.of(event));
    }

    @Test
    public void findByIdTest() {
        String eventName = "TestEvent";
        Event foundEvent = eventManager.findById(1);

        Assertions.assertThat(foundEvent.getName()).isEqualTo(eventName);
    }
}
