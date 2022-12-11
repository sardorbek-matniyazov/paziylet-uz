package paziylet_uz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "attachment")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Long id;

    @Column(name = "attachment_original_name", nullable = false, length = 100)
    private String originalName;
    @Column(name = "attachment_file_name", nullable = false, unique = true, length = 150)
    private String fileName;
    @Column(name = "attachment_content_type", nullable = false, length = 100)
    private String contentType;
    @Column(name = "attachment_size", nullable = false)
    private Long size;

    public Attachment(String originalFilename, String toString, String contentType, long size) {
        this.originalName = originalFilename;
        this.fileName = toString;
        this.contentType = contentType;
        this.size = size;
    }
}
