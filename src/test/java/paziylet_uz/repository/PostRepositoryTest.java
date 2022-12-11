package paziylet_uz.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import paziylet_uz.domain.Post;

import java.util.List;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
//        postRepository.save(
//                new Post(
//                        "title1",
//                        "description",
//                        List.of(
//                                "name",
//                                "aa",
//                                "bb"
//                        )
//                )
//        );
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllByTags() {
//        postRepository.findAll().forEach(System.out::println);
//        Assertions.assertThat(
//                postRepository.findAllByTags("aa")
//        ).size().isEqualTo(1);
//        Assertions.assertThat(
//                postRepository.findAllByTagsContaining(List.of("aa"))
//        ).size().isEqualTo(6);
    }
}