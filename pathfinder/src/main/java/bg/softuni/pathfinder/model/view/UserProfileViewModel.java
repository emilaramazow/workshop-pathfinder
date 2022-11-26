package bg.softuni.pathfinder.model.view;

import bg.softuni.pathfinder.enums.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserProfileViewModel {

    // view model -> само за да визуализираме профила в /users/{id}/profile
    // private Long id;

    private String email;
    private String username;
    private String fullName;
    private Integer age;
    private LevelEnum level;

}
