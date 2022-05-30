package bg.softuni.pathfinder.model;

import bg.softuni.pathfinder.enums.UserRolesEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private UserRolesEnum name;

    public RoleEntity() {
    }

    public Long getId() {
        return id;
    }

    public RoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public UserRolesEnum getName() {
        return name;
    }

    public RoleEntity setName(UserRolesEnum name) {
        this.name = name;
        return this;
    }
}
