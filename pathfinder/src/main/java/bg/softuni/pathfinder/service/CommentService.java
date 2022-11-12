package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.service.CommentServiceModel;
import bg.softuni.pathfinder.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {

    List<CommentViewModel> getComments(Long routeId);

    CommentViewModel createComment(CommentServiceModel commentServiceModel);
}
