package bg.softuni.pathfinder.model.entity;

import bg.softuni.pathfinder.enums.RoleNameEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleNameEnum role;

}
