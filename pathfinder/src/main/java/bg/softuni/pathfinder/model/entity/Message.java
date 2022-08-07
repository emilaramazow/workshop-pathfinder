package bg.softuni.pathfinder.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@NoArgsConstructor
@Getter
@Setter
public class Message extends BaseEntity{

    @Column(nullable = false)
    private LocalDateTime dateTime;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String textContent;

    @ManyToOne
    private User author;
    @ManyToOne
    private User recipient;

}
