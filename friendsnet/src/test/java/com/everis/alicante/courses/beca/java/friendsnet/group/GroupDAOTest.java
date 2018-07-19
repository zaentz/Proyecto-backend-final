package com.everis.alicante.courses.beca.java.friendsnet.group;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.GroupDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Group;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GroupDAOTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GroupDAO groupDAO;

    @Test
    public void findByIdTest() {
        Group group = new Group();
        group.setName("TestGroup");
        entityManager.persist(group);
        entityManager.flush();

        Group groupFromDB = groupDAO.findById(group.getId()).orElse(null);

        Assertions.assertThat(groupFromDB.getName()).isEqualTo(group.getName());
    }
}
