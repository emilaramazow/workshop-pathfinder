package bg.softuni.pathfinder.model.entity;

import bg.softuni.pathfinder.enums.LevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "routes")
@NoArgsConstructor
@Getter
@Setter
public class Route extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;
    @Lob
    private String description;
    @Lob
    private String gpxCoordinates;
    @Enumerated(EnumType.STRING)
    private LevelEnum level;
    @Column(name = "video_url")
    private String videoURL;

    @ManyToOne
    private User author;
    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY)
    private List<Picture> pictures;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> categories;
    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

}
