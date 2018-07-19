package com.everis.alicante.courses.beca.java.friendsnet.person;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.PersonDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Person;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonDAOTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonDAO personDAO;

    @Test
    public void findByIdTest() {
        Person person = new Person();
        person.setName("TestName");
        person.setSurname("TestSurname");
        person.setPicture(new byte[]{0});
        entityManager.persist(person);
        entityManager.flush();

        Person personFromDB = personDAO.findById(person.getId()).orElse(null);

        Assertions.assertThat(personFromDB.getName()).isEqualTo(person.getName());
    }
}
