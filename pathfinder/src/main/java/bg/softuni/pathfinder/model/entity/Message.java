package bg.softuni.pathfinder.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@NoArgsConstructor
@Getter
@Setter
public class Message extends BaseEntity{

    @Column(nullable = false)
    private LocalDateTime dateTime;
    @Lob
    @Column(nullable = false)
    private String textContent;

    @ManyToOne
    private User author;
    @ManyToOne
    private User recipient;

}
