package com.everis.alicante.courses.beca.java.friendsnet.post;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.PostDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Post;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostDAOTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostDAO postDAO;

    @Test
    public void findByIdTest() {
        Post post = new Post();
        post.setText("PostText");
        entityManager.persist(post);
        entityManager.flush();

        Post postFromDB = postDAO.findById(post.getId()).orElse(null);

        Assertions.assertThat(postFromDB.getText()).isEqualTo(post.getText());
    }
}
