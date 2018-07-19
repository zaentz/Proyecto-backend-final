package com.everis.alicante.courses.beca.java.friendsnet.person;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.PersonDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Person;
import com.everis.alicante.courses.beca.java.friendsnet.manager.PersonManager;
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
public class PersonManagerTest {

    @TestConfiguration
    static class PersonManagerTestContextConfiguration {

        @Bean
        public PersonManager personManager() {
            return new PersonManager();
        }
    }

    @Autowired
    private PersonManager personManager;

    @MockBean
    private PersonDAO personDAO;

    @Before
    public void setUp() {
        Person person = new Person();
        person.setId(1);
        person.setName("TestName");
        person.setSurname("TestSurname");
        person.setPicture(new byte[]{0});

        Mockito.when(personDAO.findById(person.getId())).thenReturn(Optional.of(person));
    }

    @Test
    public void findByIdTest() {
        String personName = "TestName";
        Person foundPerson = personManager.findById(1);

        Assertions.assertThat(foundPerson.getName()).isEqualTo(personName);
    }
}
