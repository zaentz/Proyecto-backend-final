package com.everis.alicante.courses.beca.java.friendsnet.like;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.LikeDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Like;
import com.everis.alicante.courses.beca.java.friendsnet.entity.LikeType;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LikeDAOTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private LikeDAO likeDAO;
    
    @Test
    public void findByIdTest() {
        Like like = new Like();
        like.setType(LikeType.COOL);
        entityManager.persist(like);
        entityManager.flush();

        Like likeFromDB = likeDAO.findById(like.getId()).orElse(null);

        Assertions.assertThat(likeFromDB.getType()).isEqualTo(like.getType());
    }
}
