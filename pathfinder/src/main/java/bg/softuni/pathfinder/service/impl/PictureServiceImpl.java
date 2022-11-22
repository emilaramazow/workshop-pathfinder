package bg.softuni.pathfinder.service.impl;

import bg.softuni.pathfinder.repository.PictureRepository;
import bg.softuni.pathfinder.service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;


    @Override
    public List<String> findAllUrls() {
        return pictureRepository.findAllUrls();
    }
}
