package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.binding.UserRegisterBindingModel;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.model.service.UserServiceModel;
import bg.softuni.pathfinder.model.view.UserProfileViewModel;
import bg.softuni.pathfinder.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;


    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }


    @GetMapping("/register")
    public String register(Model model) {

//        if (!model.containsAttribute("userRegisterBindingModel")) {
//            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
//        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);

            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        // TODO : existing user name with custom validator


        userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


//    // ще работим с профила на потребителя като задаваме path variable ( {id} ) за да вземем ид-то на потребителя
//    @GetMapping("/profile/{id}")
//    private String profile(@PathVariable Long id, Model model) {
//
//        model
//                .addAttribute("user", modelMapper.map(userService.findById(id), UserProfileViewModel.class));
//
//        return "profile";
//    }


    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String email = principal.getName();

        User user = userService.getUserByEmail(email);

        UserProfileViewModel userProfileViewModel = new UserProfileViewModel(
                email,
                user.getUsername(),
                user.getFullName(),
                user.getAge(),
                user.getLevel()
        );

        model.addAttribute("user", userProfileViewModel);

        return "profile";
    }

}














