package bg.softuni.pathfinder.model.validation;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ApiError {

    @NonNull
    private final HttpStatus status;
    private List<String> fieldWithErrors = new ArrayList<>();

    public void addFieldWithError(String error) {
        fieldWithErrors.add(error);
    }
}
