package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
