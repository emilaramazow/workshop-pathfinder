package bg.softuni.pathfinder.web;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import bg.softuni.pathfinder.model.entity.Comment;
import bg.softuni.pathfinder.model.entity.Route;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.repository.CommentRepository;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;


// INTEGRATION TESTING  ->  вдигаме spring приложение с in-memory db (h2) за да може да се правят тестовете

@WithMockUser("emil")
@SpringBootTest
@AutoConfigureMockMvc
class CommentRestControllerTest {

    private static final String COMMENT_ONE = "something";
    private static final String COMMENT_TWO = "something-else";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserRepository userRepository;

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
        long routeId = initRoute();

        mockMvc.perform(get("/api/" + routeId + "/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].message", is(COMMENT_ONE)))
                .andExpect(jsonPath("$.[1].message", is(COMMENT_TWO)));
    }

    private long initRoute() {
        Route testRoute = new Route();

        testRoute.setName("Testing route");

        testRoute = routeRepository.save(testRoute);

        Comment commentOne = new Comment();
        commentOne.setCreated(LocalDate.now());
        commentOne.setAuthor(testUser);
        commentOne.setTextContent(COMMENT_ONE);
        commentOne.setApproved(true);
        commentOne.setRoute(testRoute);

        Comment commentTwo = new Comment();
        commentTwo.setCreated(LocalDate.now());
        commentTwo.setAuthor(testUser);
        commentTwo.setTextContent(COMMENT_TWO);
        commentTwo.setApproved(true);
        commentTwo.setRoute(testRoute);

        testRoute.setComments(List.of(commentOne, commentTwo));

        return routeRepository.save(testRoute).getId();
    }

}
