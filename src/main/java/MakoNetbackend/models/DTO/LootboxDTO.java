package MakoNetbackend.models.DTO;

import MakoNetbackend.models.database.LootboxesDB;
import MakoNetbackend.models.database.Rarity;
import MakoNetbackend.models.database.Rewards;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LootboxDTO {
    private String reward;
    private int chance;
    private Rarity rarity;
    private boolean received;
    private LocalDateTime drawTime;

    public LootboxDTO(LootboxesDB lootboxesDB){
        this.reward = lootboxesDB.getReward();
        this.rarity = lootboxesDB.getRarity();
        this.received = lootboxesDB.getReceived();
        this.drawTime = lootboxesDB.getDrawTime();
    }

    public LootboxDTO(String reward, int chance, Rarity rarity) {
        this.reward = reward;
        this.chance = chance;
        this.rarity = rarity;
        this.received = false;
        this.drawTime = LocalDateTime.now();
    }
}
