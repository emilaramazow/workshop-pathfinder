package bg.softuni.pathfinder.model.binding;

import bg.softuni.pathfinder.enums.CategoryNameEnum;
import bg.softuni.pathfinder.enums.LevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class RouteAddBindingModel {

    @Size(min = 3, max = 30, message = "Route name must be between 3 and 30 symbols.")
    private String name;
    @Size(min = 3)
    private String description;

    // временно качване на файл в паметта
    private MultipartFile gpxCoordinates;
    @NotNull
    private LevelEnum level;
    private String videoURL;
    private Set<CategoryNameEnum> categories;

}
