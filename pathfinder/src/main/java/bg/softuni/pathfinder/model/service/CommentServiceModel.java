package bg.softuni.pathfinder.model.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentServiceModel {

    private Long routeId;
    private String message;
    private String creator;

}
