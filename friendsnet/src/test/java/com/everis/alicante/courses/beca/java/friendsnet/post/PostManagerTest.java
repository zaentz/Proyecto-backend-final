package com.everis.alicante.courses.beca.java.friendsnet.post;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.PostDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Post;
import com.everis.alicante.courses.beca.java.friendsnet.manager.PostManager;
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
public class PostManagerTest {

    @TestConfiguration
    static class PostManagerTestContextConfiguration {

        @Bean
        public PostManager postManager() {
            return new PostManager();
        }
    }

    @Autowired
    private PostManager postManager;

    @MockBean
    private PostDAO postDAO;

    @Before
    public void setUp() {
        Post post = new Post();
        post.setId(1);
        post.setText("PostText");

        Mockito.when(postDAO.findById(post.getId())).thenReturn(Optional.of(post));
    }

    @Test
    public void findByIdTest() {
        String postText = "PostText";
        Post foundPost = postManager.findById(1);

        Assertions.assertThat(foundPost.getText()).isEqualTo(postText);
    }
}
