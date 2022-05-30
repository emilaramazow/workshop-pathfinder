package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.RouteEntity;
import bg.softuni.pathfinder.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<RouteEntity> getMostCommented() {
        return routeRepository.findMostCommented();
    }
}
