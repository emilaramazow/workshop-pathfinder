package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.dto.UserRegistrationDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    @GetMapping("/register")
    public String register(Model model) {
        UserRegistrationDTO dto = new UserRegistrationDTO();
        model.addAttribute("userRegistrationDTO", dto);
        return "register";
    }


    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDTO userRegistrationDTO,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        System.out.println(userRegistrationDTO.toString());

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            return "redirect:/register";

        }

        // insert in db
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

}
