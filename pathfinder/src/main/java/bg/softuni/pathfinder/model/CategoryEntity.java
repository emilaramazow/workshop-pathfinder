package bg.softuni.pathfinder.model;

import bg.softuni.pathfinder.enums.RouteCategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RouteCategoryEnum name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public CategoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public CategoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public RouteCategoryEnum getName() {
        return name;
    }

    public CategoryEntity setName(RouteCategoryEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
