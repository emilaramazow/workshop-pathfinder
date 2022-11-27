package bg.softuni.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PedestrianController {

    @GetMapping("/pedestrian")
    public String pedestrian() {
        return "pedestrian";
    }
}
