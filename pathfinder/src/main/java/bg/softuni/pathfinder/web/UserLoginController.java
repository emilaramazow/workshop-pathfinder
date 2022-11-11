package bg.softuni.pathfinder.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserLoginController {

    @GetMapping("/users/login")
    public String login() {
        return "login";
    }

}
