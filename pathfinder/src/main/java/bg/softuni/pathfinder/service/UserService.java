package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.service.UserServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void registerUser(UserServiceModel userServiceModel);
}
