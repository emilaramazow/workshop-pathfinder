package bg.softuni.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BicycleController {

    @GetMapping("/bicycle")
    public String bicycle() {
        return "bicycle";
    }
}
