package bg.softuni.pathfinder.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
@NoArgsConstructor
@Getter
@Setter
public class Picture extends BaseEntity{

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String url;

    @ManyToOne
    private User author;
    @ManyToOne
    private Route route;

}
