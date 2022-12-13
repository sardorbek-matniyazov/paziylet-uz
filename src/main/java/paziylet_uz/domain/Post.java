package paziylet_uz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import paziylet_uz.domain.based.BaseEntity;
import paziylet_uz.domain.enums.PostType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "post")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "post_title", nullable = false, length = 100, unique = true)
    private String title;
    @Column(name = "post_desc", nullable = false, length = 1_000_000)
    private String description;
    @Column(name = "post_type", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private PostType type;

    @JoinTable(
            name = "post_images",
            joinColumns = {
                    @JoinColumn(
                            referencedColumnName = "post_id",
                            name = "post_id"
                    )
            }
    )
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> images = new HashSet<>();

    @JoinTable(
            name = "post_tags",
            joinColumns = {
                    @JoinColumn(
                            referencedColumnName = "post_id",
                            name = "post_id"
                    )
            }
    )
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags = new ArrayList<>();

    @JsonIgnore
    @Column(name = "post_search_query")
    private String searchQuery;

    public Post(String title, String desc, PostType type, List<String> tags) {
        this.title = title;
        this.type = type;
        this.description = desc;
        this.tags = tags;
    }

    public Post setQuerySearch() {
        this.searchQuery = this.title + tags.stream().reduce(" ", (i, a) -> i + a + " ");
        return this;
    }

    public Post setImages(Set<String> images) {
        this.images = images;
        return this;
    }
}
