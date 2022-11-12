package bg.softuni.pathfinder.model.view;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
