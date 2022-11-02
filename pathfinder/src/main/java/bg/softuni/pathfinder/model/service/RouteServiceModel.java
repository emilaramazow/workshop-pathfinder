package bg.softuni.pathfinder.model.service;

import bg.softuni.pathfinder.enums.LevelEnum;
import bg.softuni.pathfinder.model.entity.Category;
import bg.softuni.pathfinder.model.entity.Picture;
import bg.softuni.pathfinder.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class RouteServiceModel {

    private Long id;
    private String name;
    private String description;
    private String gpxCoordinates;
    private LevelEnum level;
    private String videoURL;
    private User author;
    private Set<Picture> pictures;
    private Set<Category> categories;

}
