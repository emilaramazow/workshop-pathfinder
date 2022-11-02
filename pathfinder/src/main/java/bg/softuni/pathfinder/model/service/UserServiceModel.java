package bg.softuni.pathfinder.model.service;

import bg.softuni.pathfinder.enums.LevelEnum;
import bg.softuni.pathfinder.model.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserServiceModel {

    private Long id;
    private String username;
    private String fullName;
    private String password;
    private String email;
    private Integer age;
    private LevelEnum level;
    private Set<Role> roles;

}
