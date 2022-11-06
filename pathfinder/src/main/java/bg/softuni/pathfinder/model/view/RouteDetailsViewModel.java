package bg.softuni.pathfinder.model.view;

import bg.softuni.pathfinder.enums.LevelEnum;
import bg.softuni.pathfinder.model.entity.Picture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class RouteDetailsViewModel {

    private String gpxCoordinates;
    private String description;
    private LevelEnum level;
    private String name;
    private String videoURL;
    private Set<Picture> pictures;

}
