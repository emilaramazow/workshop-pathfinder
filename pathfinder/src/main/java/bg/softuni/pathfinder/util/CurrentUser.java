package bg.softuni.pathfinder.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@NoArgsConstructor
@Getter
public class CurrentUser {

    private Long id;
    private String username;

}
