package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.service.UserServiceModel;
import org.springframework.stereotype.Service;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logout();
}
