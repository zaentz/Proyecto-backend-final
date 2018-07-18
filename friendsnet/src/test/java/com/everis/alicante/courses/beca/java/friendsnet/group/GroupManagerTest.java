package com.everis.alicante.courses.beca.java.friendsnet.group;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.GroupDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Group;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Person;
import com.everis.alicante.courses.beca.java.friendsnet.manager.GroupManager;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
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
public class GroupManagerTest {
    
    @TestConfiguration
    static class GroupManagerTestContextConfiguration {
        
        @Bean
        public GroupManager groupManager() {
            return new GroupManager();
        }
    }

    @Autowired
    private GroupManager groupManager;

    @MockBean
    private GroupDAO groupDAO;

    @Before
    public void setUp() {
        Person person = new Person();
        person.setName("TestName");
        person.setSurname("TestSurname");
        person.setPicture(new byte[]{0});

        Set<Person> personSet = new HashSet<>();
        personSet.add(person);

        Group group = new Group();
        group.setId(1);
        group.setName("TestGroup");
        group.setPersons(personSet);
        group.setPicture(new byte[]{0});

        Mockito.when(groupDAO.findById(group.getId())).thenReturn(Optional.of(group));
    }

    @Test
    public void findByIdTest() {
        String groupName = "TestGroup";
        Group foundGroup = groupManager.findById(1);

        Assertions.assertThat(foundGroup.getName()).isEqualTo(groupName);
    }
}
