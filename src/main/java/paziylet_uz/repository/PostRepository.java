package paziylet_uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paziylet_uz.domain.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByTitle(String title);

    List<Post> findAllBySearchQueryContaining(String tag);

    boolean existsByTitleAndIdIsNot(String title, Long id);
}