package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.view.CommentViewModel;
import bg.softuni.pathfinder.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class CommentRestController {

    private final CommentService commentService;

    @GetMapping("/api/{routeId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(
            @PathVariable Long routeId,
            Principal principal) {


        return ResponseEntity.ok(commentService.getComments(routeId));
    }
}
