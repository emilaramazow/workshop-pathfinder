package bg.softuni.pathfinder.model;

import bg.softuni.pathfinder.enums.LevelEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
public class RouteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "gpx_coordinates")
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private UserEntity author;

    @Column(name = "video_url")
    private String videoURL;

    @OneToMany(targetEntity = CommentEntity.class, mappedBy = "route", cascade = CascadeType.ALL)
    private Set<CommentEntity> comments;

    @OneToOne
    private PictureEntity header;

    @ManyToMany
    private Set<CategoryEntity> categories;

    public RouteEntity() {
        this.comments = new HashSet<>();
        this.categories = new HashSet<>();
    }


    public Long getId() {
        return id;
    }

    public RouteEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RouteEntity setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public RouteEntity setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteEntity setName(String name) {
        this.name = name;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public RouteEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public RouteEntity setVideoURL(String videoURL) {
        this.videoURL = videoURL;
        return this;
    }

    public Set<CommentEntity> getComments() {
        return comments;
    }

    public RouteEntity setComments(Set<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }

    public PictureEntity getHeader() {
        return header;
    }

    public RouteEntity setHeader(PictureEntity header) {
        this.header = header;
        return this;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public RouteEntity setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }
}
