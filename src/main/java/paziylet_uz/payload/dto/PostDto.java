package paziylet_uz.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import paziylet_uz.domain.Post;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    @NotBlank(message = "Post title is required")
    private String title;
    @NotBlank(message = "Post description is required")
    private String description;
    private List<String> tags;
    public Post toEntity() {
        return new Post(
                this.title,
                this.description,
                this.tags
        );
    }
}
