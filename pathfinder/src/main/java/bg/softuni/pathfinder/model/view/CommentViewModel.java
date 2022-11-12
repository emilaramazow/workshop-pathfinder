package bg.softuni.pathfinder.model.view;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CommentViewModel {

    private Long commentId;
    private String message;
    private String user;
    private LocalDate created;
    private boolean canApprove;
    private boolean canDelete;

}
