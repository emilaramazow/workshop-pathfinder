package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.view.RouteViewModel;
import bg.softuni.pathfinder.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/all")
    public String allRoutes(Model model) {

        // подаваме нашите пътища към темплейта с Model
        model.addAttribute("routes", routeService.findAllRoutesView());

        return "routes";
    }
}
