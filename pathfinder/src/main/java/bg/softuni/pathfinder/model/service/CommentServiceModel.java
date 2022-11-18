package bg.softuni.pathfinder.model.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentServiceModel {

    private Long routeId;
    private String message;
    private String creator;

    public CommentServiceModel(Long routeId, String message, String creator) {
        this.routeId = routeId;
        this.message = message;
        this.creator = creator;
    }
}
