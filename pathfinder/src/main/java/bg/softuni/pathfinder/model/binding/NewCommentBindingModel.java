package bg.softuni.pathfinder.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class NewCommentBindingModel {

    @NotBlank
    @Size(min = 10)
    private String message;

}
