package paziylet_uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import paziylet_uz.domain.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByTitle(String title);

    @Query(value = "select p from post p where p.searchQuery  like %?1%")
    List<Post> findAllBySearchQueryLike(String tag);
}