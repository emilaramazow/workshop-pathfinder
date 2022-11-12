package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findById(Long id);


}
