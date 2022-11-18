package bg.softuni.pathfinder.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    // INTEGRATION TESTING FOR USER CONTROLLER

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    // for cleaning userRepository, because commentRestController use the same user
    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }


    @Test
    void testOpenRegisterForm() throws Exception {
        // we made a get request, register method is with @GetMapping

        mockMvc
                .perform(
                        // get request to the endpoint
                        get("/users/register"))
                // http status -> ok -> 200
                .andExpect(status().isOk())
                // expected view model
                .andExpect(view().name("register"));
    }

    private static final String TEST_USER_EMAIL = "emil@example.com";
    private static final Integer TEST_USER_AGE = 25;


    @Test
    void testRegisterUser() throws Exception {
        // take the fields from userRegisterBindingModel

        // post request to the endpoint, because registerUser method is with @PostMapping
        mockMvc.perform(post("/users/register")
                        // parameters for the request -> parameters is the fields from the userBindingModel
                        .param("username", TEST_USER_EMAIL)
                        .param("fullName", "Emil Emilov")
                        .param("email", TEST_USER_EMAIL)
                        .param("age", String.valueOf(TEST_USER_AGE))
                        .param("password", "123456")
                        .param("confirmPassword", "123456")
                        // csrf token
                        .with(csrf())
                        // the contentType form which will be submit to the server
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, userRepository.count());

        Optional<User> newlyCreatedUserOpt = userRepository.findByEmail(TEST_USER_EMAIL);

        Assertions.assertTrue(newlyCreatedUserOpt.isPresent());

        User newlyCreatedUser = newlyCreatedUserOpt.get();
        Assertions.assertEquals(TEST_USER_AGE,newlyCreatedUser.getAge());

        //todo - check the remaining properties
    }

}
