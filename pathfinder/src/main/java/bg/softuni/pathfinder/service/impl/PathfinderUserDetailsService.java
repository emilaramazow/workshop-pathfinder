package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PathfinderUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public PathfinderUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userEntity = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email" + email + "was not found!"));

        return map(userEntity);
    }

    private UserDetails map(User user) {
        // map user entity to user details

        Set<GrantedAuthority> grantedAuthorities =
                user
                        .getRoles()
                        .stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name()))
                        .collect(Collectors.toUnmodifiableSet());


        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                grantedAuthorities
        );
    }
}
