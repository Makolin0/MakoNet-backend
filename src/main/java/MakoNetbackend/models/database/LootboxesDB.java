package MakoNetbackend.models.database;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LootboxesDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Positive
    private long id;

    @NotNull
    private Boolean received;

//    @Enumerated(EnumType.STRING)
    @NotNull
    private String reward;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Rarity rarity;

    @NotNull
    private LocalDateTime drawTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    private UserDB user;
}
