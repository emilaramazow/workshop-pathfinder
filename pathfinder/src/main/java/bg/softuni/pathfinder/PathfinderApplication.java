package bg.softuni.pathfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PathfinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PathfinderApplication.class, args);
    }

}
