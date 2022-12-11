package paziylet_uz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import paziylet_uz.domain.Attachment;

import java.util.Optional;

public interface FileRepository extends JpaRepository<Attachment, Long> {
    boolean existsByFileName(String fileName);

    Optional<Attachment> findByFileName(String fileName);
}
