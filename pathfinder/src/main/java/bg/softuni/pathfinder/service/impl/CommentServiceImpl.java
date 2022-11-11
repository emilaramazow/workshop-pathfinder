package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.view.CommentViewModel;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.service.CommentService;
import bg.softuni.pathfinder.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final RouteRepository routeRepository;

    @Override
    public List<CommentViewModel> getComments(Long routeId) {
        var routeOpt = routeRepository.findById(routeId);

        if (routeOpt.isEmpty()) {
            throw new ObjectNotFoundException("Route with id " + routeId + " was not found!");
        }


        return null;
    }
}
