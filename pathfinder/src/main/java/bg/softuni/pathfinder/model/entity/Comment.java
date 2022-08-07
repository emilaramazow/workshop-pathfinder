package bg.softuni.pathfinder.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@Getter
@Setter
public class Comment extends BaseEntity{

    @Column(nullable = false)
    private Boolean approved;
    @Column(columnDefinition = "TEXT")
    private String textContent;
    @Column(name = "created", nullable = false)
    private LocalDate created;

    @ManyToOne
    private Route route;
    @ManyToOne
    private User author;

}
