package bg.softuni.pathfinder.model.view;

import bg.softuni.pathfinder.enums.LevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserViewModel {

    // view model -> само за да визуализираме профила в /users/{id}/profile

    private Long id;
    private String fullName;
    private String username;
    private Integer age;
    private LevelEnum level;

}
