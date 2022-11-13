package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.enums.RoleNameEnum;
import bg.softuni.pathfinder.model.entity.Role;
import bg.softuni.pathfinder.model.entity.User;
import bg.softuni.pathfinder.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// Testing with JUnit5

@ExtendWith(MockitoExtension.class)
class PathfinderUserDetailsServiceTest {

    private User testUser;

    private PathfinderUserDetailsService serviceToTest;
    private Role adminRole, userRole;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init() {

        // 1. Arrange
        serviceToTest = new PathfinderUserDetailsService(mockUserRepository);

        adminRole = new Role();
        adminRole.setRole(RoleNameEnum.ADMIN);
        userRole = new Role();
        userRole.setRole(RoleNameEnum.USER);

        testUser = new User();
        testUser.setUsername("Emil");
        testUser.setEmail("emil@abv.bg");
        testUser.setPassword("topsecret");
        testUser.setRoles(Set.of(adminRole, userRole));
    }

    @Test
    void testUserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> {
                    serviceToTest.loadUserByUsername("invalid_user_email@not-exist.bg");
                }
        );
    }

    @Test
    void testUserFound() {
        // 1. Arrange
        // с mockito.when все едно обучавам кухия обект да не е кух, а да връща някаква информация
        // как да се държи, какво поведение да има
        Mockito.when(mockUserRepository.findByEmail(testUser.getEmail()))
                .thenReturn(Optional.of(testUser));

        // 2. Act
       var actual = serviceToTest.loadUserByUsername(testUser.getEmail());

        // 3. Assert
        Assertions.assertEquals(actual.getUsername(), testUser.getEmail());
        String actualRoles = actual
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        String expectedRoles = "ROLE_ADMIN, ROLE_USER";

        Assertions.assertEquals(expectedRoles, actualRoles);
    }

}
