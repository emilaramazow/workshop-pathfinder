package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.binding.NewCommentBindingModel;
import bg.softuni.pathfinder.model.service.CommentServiceModel;
import bg.softuni.pathfinder.model.validation.ApiError;
import bg.softuni.pathfinder.model.view.CommentViewModel;
import bg.softuni.pathfinder.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class CommentRestController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @GetMapping("/api/{routeId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(
            @PathVariable Long routeId,
            Principal principal) {


        return ResponseEntity.ok(commentService.getComments(routeId));
    }

    @PostMapping("/api/{routeId}/comments")
    public ResponseEntity<CommentViewModel> newComment(
            @AuthenticationPrincipal UserDetails principal,
            @PathVariable Long routeId,
            @RequestBody @Valid NewCommentBindingModel newCommentBindingModel
    ) {

        // @AuthenticationPrincipal -> анонимни юзъри няма да могат да публикуват коментари
        // @RequestBody -> да вземе от JSON обекта, който пращаме да създаде NewCommentBindingModel

        CommentServiceModel commentServiceModel = modelMapper
                .map(newCommentBindingModel, CommentServiceModel.class);

        // сетваме ид-то, защото моделмапър-a няма да се справи с ид-то, защото го няма в NewCommentBindingModel
        commentServiceModel.setRouteId(routeId);

        CommentViewModel newComment = commentService.createComment(commentServiceModel);

        // ще направим стринг, който ще е локацията на новосъздадения коментар
        URI locationOfNewComment = URI.create(String.format("/api/%s/comments/%s", routeId, newComment.getCommentId()));

        return ResponseEntity.created(locationOfNewComment).body(newComment);
    }

    // ако @Valid върне грешка го прихващаме :
    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)   ->   когато изпозлваме ResponseEntity, този статус не би трябвало да е необходим
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException exc) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        exc.getFieldErrors()
                .forEach(fe -> apiError.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(apiError);
    }


}


