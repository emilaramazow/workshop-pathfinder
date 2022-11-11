package bg.softuni.pathfinder.model.view;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class CommentViewModel {

    private Long commentId;
    private String message;
    private String user;
    private Instant created;
    private boolean canApprove;
    private boolean canDelete;

}
