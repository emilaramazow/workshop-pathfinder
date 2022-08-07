package bg.softuni.pathfinder.model.entity;

import bg.softuni.pathfinder.enums.LevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity{

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String password;
    @Column
    private String email;
    @Column
    private Integer age;
    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    @ManyToMany
    private Set<Role> roles;
}
