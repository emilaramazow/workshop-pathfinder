package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.entity.Comment;
import bg.softuni.pathfinder.model.service.CommentServiceModel;
import bg.softuni.pathfinder.model.view.CommentViewModel;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.service.CommentService;
import bg.softuni.pathfinder.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final RouteRepository routeRepository;

    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long routeId) {
        var routeOpt = routeRepository.findById(routeId);

        if (routeOpt.isEmpty()) {
            throw new ObjectNotFoundException("Route with id " + routeId + " was not found!");
        }


        return routeOpt
                .get()
                .getComments()
                .stream()
                .map(this::mapAsComment)
                .collect(Collectors.toList());
    }

    @Override
    public CommentViewModel createComment(CommentServiceModel commentServiceModel) {
        throw new UnsupportedOperationException("NOT YET!");
    }

    // mapping method
    private CommentViewModel mapAsComment(Comment commentEntity) {
        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel.setCommentId(commentEntity.getId());
        commentViewModel.setCanApprove(true);
        commentViewModel.setCanDelete(true);
        commentViewModel.setCreated(commentEntity.getCreated());
        commentViewModel.setMessage(commentEntity.getTextContent());
        commentViewModel.setUser(commentEntity.getAuthor().getFullName());

        return commentViewModel;
    }
}
