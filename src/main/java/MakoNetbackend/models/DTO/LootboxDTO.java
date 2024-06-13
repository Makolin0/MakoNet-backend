package MakoNetbackend.models.DTO;

import MakoNetbackend.models.database.LootboxesDB;
import MakoNetbackend.models.database.Rarity;
import MakoNetbackend.models.database.Rewards;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LootboxDTO {
    private Rewards reward;
    private Rarity rarity;
    private boolean received;

    public LootboxDTO(LootboxesDB lootboxesDB){
        this.reward = lootboxesDB.getReward();
        this.rarity = lootboxesDB.getRarity();
        this.received = lootboxesDB.getReceived();
    }
}
