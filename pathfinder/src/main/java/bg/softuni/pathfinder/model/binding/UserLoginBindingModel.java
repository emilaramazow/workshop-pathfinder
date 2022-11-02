package bg.softuni.pathfinder.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginBindingModel {

    private String username;
    private String password;
}
