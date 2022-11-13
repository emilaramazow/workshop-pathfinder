package bg.softuni.pathfinder.model.entity;

import bg.softuni.pathfinder.enums.CategoryNameEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private CategoryNameEnum name;
    @Lob
    private String description;

}

