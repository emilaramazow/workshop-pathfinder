package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.enums.LevelEnum;
import bg.softuni.pathfinder.enums.RoleNameEnum;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.model.service.UserServiceModel;
import bg.softuni.pathfinder.repository.UserRepository;
import bg.softuni.pathfinder.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);

        String username = userServiceModel.getUsername();
        String email = userServiceModel.getEmail();
        String fullName = userServiceModel.getFullName();
        String encodePassword = passwordEncoder.encode(userServiceModel.getPassword());
        Integer age = userServiceModel.getAge();


        user.setUsername(username);
        user.setEmail(email);
        user.setFullName(fullName);
        user.setPassword(encodePassword);
        user.setAge(age);
        user.setLevel(LevelEnum.BEGINNER);

        userRepository.save(user);
    }



    @Override
    public UserServiceModel findById(Long id) {
        return userRepository
                .findById(id)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }


}
