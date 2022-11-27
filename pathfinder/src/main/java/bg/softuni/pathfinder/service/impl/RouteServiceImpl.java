package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.entity.Route;
import bg.softuni.pathfinder.model.service.RouteServiceModel;
import bg.softuni.pathfinder.model.view.RouteDetailsViewModel;
import bg.softuni.pathfinder.model.view.RouteViewModel;
import bg.softuni.pathfinder.repository.RouteRepository;
import bg.softuni.pathfinder.service.CategoryService;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;


    @Transactional
    @Override
    public List<RouteViewModel> findAllRoutesView() {
        return routeRepository
                .findAll()
                .stream()
                .map(route -> {
                    RouteViewModel routeViewModel = modelMapper.map(route, RouteViewModel.class);

                    if (route.getPictures().isEmpty()) {
                        routeViewModel.setPictureURL("/images/pic4.jpg");
                    } else {
                        routeViewModel.setPictureURL(route
                                .getPictures()
                                .stream()
                                .findFirst()
                                .get()
                                .getUrl());
                    }

                    return routeViewModel;
                })
                .collect(Collectors.toList());
    }


    // SECOND WAY for findAllRoutesView
//    public List<RouteViewModel> findAllRoutes() {
//        return routeRepository
//                .findAll()
//                .stream()
//                .map(route -> new RouteViewModel(
//                        route.getId(),
//                        route.getName(),
//                        route.getDescription(),
//                        route.getPictures().stream().findFirst().get().getUrl()
//                )).collect(Collectors.toList());
//    }


    @Override
    public void addNewRoute(RouteServiceModel routeServiceModel) {
        Route route = modelMapper.map(routeServiceModel, Route.class);
        //TODO : current suer
        // route.setAuthor(userService.findCurrentLoginUserEntity());

        route.setCategories(routeServiceModel
                .getCategories()
                .stream()
                .map(categoryNameEnum -> categoryService.findCategoryByName(categoryNameEnum))
                .collect(Collectors.toList()));

        routeRepository.save(route);
    }



    // SECOND WAY to show route with ID
//    public RouteDetailsViewModel getRouteById(Long id) {
//        return routeRepository
//                .findById(id)
//                .map(route -> new RouteDetailsViewModel(
//                        route.getId(),
//                        route.getGpxCoordinates(),
//                        route.getLevel().name(),
//                        route.getName(),
//                        route.getDescription(),
//                        route.getAuthor().getFullName(),
//                        route.getVideoURL(),
//                        route.getPictures()
//                                .stream().map(Picture::getUrl).collect(Collectors.toList())
//                )).orElseThrow(ObjectNotFoundException::new);
//    }



    @Transactional
    @Override
    public RouteDetailsViewModel findRouteById(Long id) {
        return routeRepository
                .findById(id)
                .map(route -> modelMapper.map(route, RouteDetailsViewModel.class))
                .orElse(null);
    }
}
