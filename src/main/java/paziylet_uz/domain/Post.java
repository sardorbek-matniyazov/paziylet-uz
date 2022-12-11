package paziylet_uz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import paziylet_uz.domain.based.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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

    @Column(name = "post_search_query")
    private String searchQuery;

    public Post(String title1, String desc, List<String> tags) {
        this.title = title1;
        this.description = desc;
        this.tags = tags;
    }

    public Post setQuerySearch() {
        this.searchQuery = this.title + this.description + tags.stream().reduce("", String::concat);
        return this;
    }

    public Post setImages(Set<String> images) {
        this.images = images;
        return this;
    }
}
