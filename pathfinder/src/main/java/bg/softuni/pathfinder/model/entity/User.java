package bg.softuni.pathfinder.model.entity;

import bg.softuni.pathfinder.enums.LevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column
    @Min(10)
    private Integer age;
    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
