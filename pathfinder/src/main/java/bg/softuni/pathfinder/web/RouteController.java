package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.binding.RouteAddBindingModel;
import bg.softuni.pathfinder.model.service.RouteServiceModel;
import bg.softuni.pathfinder.service.RouteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@AllArgsConstructor
@RequestMapping("/routes")
public class RouteController {

    private static Logger LOGGER = LoggerFactory.getLogger(RouteController.class);
    private final RouteService routeService;
    private final ModelMapper modelMapper;

    @ModelAttribute
    public RouteAddBindingModel routeAddBindingModel() {
        return new RouteAddBindingModel();
    }

    @GetMapping("/all")
    public String allRoutes(Model model) {

        LOGGER.debug("All routes were requested...");

        // подаваме нашите пътища към темплейта с Model
        model.addAttribute("routes", routeService.findAllRoutesView());

        return "routes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("route", routeService.findRouteById(id));

        return "route-details";
    }


    @GetMapping("/add")
    public String add() {

        return "add-route";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid RouteAddBindingModel routeAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("routeAddBindingModel", routeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.routeAddBindingModel", bindingResult);

            return "redirect:add";
        }

        RouteServiceModel routeServiceModel = modelMapper
                .map(routeAddBindingModel, RouteServiceModel.class);

        // не можем да мапнем файла с model mapper, затова сетваме координатите по следния начин:
        routeServiceModel.setGpxCoordinates(new String(routeAddBindingModel.getGpxCoordinates().getBytes()));

        routeService.addNewRoute(routeServiceModel);

        return "redirect:all";
    }


}
