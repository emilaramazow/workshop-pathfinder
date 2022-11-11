package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    private final PictureService pictureService;

    @GetMapping("/")
    public String index(Model model) {

        // няма да мапваме, а само ще вземем URL-те
        model.addAttribute("pictures",
                pictureService.findAllUrls());

        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

}
