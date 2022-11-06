package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.binding.RouteAddBindingModel;
import bg.softuni.pathfinder.model.service.RouteServiceModel;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public RouteController(RouteService routeService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.routeService = routeService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public RouteAddBindingModel routeAddBindingModel() {
        return new RouteAddBindingModel();
    }

    @GetMapping("/all")
    public String allRoutes(Model model) {

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
        if (currentUser.getId() == null) {
            return "redirect:/users/login";
        }

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
