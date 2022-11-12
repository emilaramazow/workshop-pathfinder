package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.enums.CategoryNameEnum;
import bg.softuni.pathfinder.model.entity.Category;
import bg.softuni.pathfinder.repository.CategoryRepository;
import bg.softuni.pathfinder.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category findCategoryByName(CategoryNameEnum categoryNameEnum) {
        return categoryRepository
                .findByName(categoryNameEnum).
                orElse(null);
    }
}
