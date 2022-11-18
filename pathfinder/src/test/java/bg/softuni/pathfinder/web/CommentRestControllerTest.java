package bg.softuni.pathfinder.web;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import bg.softuni.pathfinder.model.binding.NewCommentBindingModel;
import bg.softuni.pathfinder.model.entity.Comment;
import bg.softuni.pathfinder.model.entity.Route;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.repository.CommentRepository;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;


// INTEGRATION TESTING  ->  вдигаме spring приложение с in-memory db (h2) за да може да се правят тестовете

@WithMockUser("emil")
@SpringBootTest
@AutoConfigureMockMvc
class CommentRestControllerTest {

    private static final String COMMENT_ONE = "Hey Spring, you are very cool!";
    private static final String COMMENT_TWO = "Well.......... thank you!";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private User testUser;


    @BeforeEach
    void setUp() {
        testUser = new User();

        testUser.setUsername("emil");
        testUser.setFullName("emil emilov");
        testUser.setPassword("password");

        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        routeRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testGetComments() throws Exception {
        var route = initComments(initRoute());

        mockMvc.perform(get("/api/" + route.getId() + "/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].message", is(COMMENT_ONE)))
                .andExpect(jsonPath("$.[1].message", is(COMMENT_TWO)));
    }

    // test rest controller -> we must send json
    @Test
    void testCreateComments() throws Exception {

        NewCommentBindingModel testComment = new NewCommentBindingModel()
                .setMessage(COMMENT_ONE);

        var emptyRoute = initRoute();

        mockMvc.perform(
                post("/api/" + emptyRoute.getId() + "/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testComment))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/" + emptyRoute.getId() + "/comments/\\d")))
                .andExpect(jsonPath("$.message").value(is(COMMENT_ONE)))
        ;
    }

    private Route initRoute() {
        Route testRoute = new Route();
        testRoute.setName("Testing route");

        return routeRepository.save(testRoute);
    }

    private Route initComments(Route route) {

        Comment commentOne = new Comment();
        commentOne.setCreated(LocalDate.now());
        commentOne.setAuthor(testUser);
        commentOne.setTextContent(COMMENT_ONE);
        commentOne.setApproved(true);
        commentOne.setRoute(route);

        Comment commentTwo = new Comment();
        commentTwo.setCreated(LocalDate.now());
        commentTwo.setAuthor(testUser);
        commentTwo.setTextContent(COMMENT_TWO);
        commentTwo.setApproved(true);
        commentTwo.setRoute(route);

        route.setComments(List.of(commentOne, commentTwo));

        return routeRepository.save(route);
    }

}
