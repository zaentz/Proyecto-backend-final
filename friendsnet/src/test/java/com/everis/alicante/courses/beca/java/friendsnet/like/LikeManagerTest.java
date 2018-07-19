package com.everis.alicante.courses.beca.java.friendsnet.like;

import com.everis.alicante.courses.beca.java.friendsnet.DAO.LikeDAO;
import com.everis.alicante.courses.beca.java.friendsnet.entity.Like;
import com.everis.alicante.courses.beca.java.friendsnet.entity.LikeType;
import com.everis.alicante.courses.beca.java.friendsnet.manager.LikeManager;
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
public class LikeManagerTest {

    @TestConfiguration
    static class LikeManagerTestContextConfiguration {

        @Bean
        public LikeManager likeManager() {
            return new LikeManager();
        }
    }

    @Autowired
    private LikeManager likeManager;

    @MockBean
    private LikeDAO likeDAO;

    @Before
    public void setUp() {
        Like like = new Like();
        like.setId(1);
        like.setType(LikeType.COOL);

        Mockito.when(likeDAO.findById(like.getId())).thenReturn(Optional.of(like));
    }

    @Test
    public void findByIdTest() {
        LikeType likeType = LikeType.COOL;
        Like foundLike = likeManager.findById(1);

        Assertions.assertThat(foundLike.getType()).isEqualTo(likeType);
    }
}
