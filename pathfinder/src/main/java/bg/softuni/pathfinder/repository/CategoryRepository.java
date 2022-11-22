package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.enums.CategoryNameEnum;
import bg.softuni.pathfinder.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(CategoryNameEnum categoryNameEnum);

}
