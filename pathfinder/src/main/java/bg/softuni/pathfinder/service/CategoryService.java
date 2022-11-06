package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.enums.CategoryNameEnum;
import bg.softuni.pathfinder.model.entity.Category;

import java.util.Optional;

public interface CategoryService {
    Category findCategoryByName(CategoryNameEnum categoryNameEnum);
}
