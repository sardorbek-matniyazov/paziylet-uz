package paziylet_uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paziylet_uz.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByTitle(String title);
}