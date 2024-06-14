package MakoNetbackend.models.DTO;

import MakoNetbackend.models.database.LootboxesDB;
import MakoNetbackend.models.database.Rarity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LootboxAdminDTO {
    private long id;
    private String reward;
    private Rarity rarity;
    private boolean received;
    private String user;
    private LocalDateTime drawTime;


    public LootboxAdminDTO(LootboxesDB lootboxesDB){
        this.id = lootboxesDB.getId();
        this.reward = lootboxesDB.getReward();
        this.rarity = lootboxesDB.getRarity();
        this.received = lootboxesDB.getReceived();
        this.user = lootboxesDB.getUser().getNickname();
        this.drawTime = lootboxesDB.getDrawTime();
    }
}
