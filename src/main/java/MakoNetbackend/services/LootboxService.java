package MakoNetbackend.services;

import MakoNetbackend.models.DTO.LootboxDTO;
import MakoNetbackend.models.database.Rarity;
import MakoNetbackend.models.database.Rewards;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LootboxService {

    public LootboxDTO draw() {
        List<LootboxDTO> rewardList = new ArrayList<>();
        rewardList.add(new LootboxDTO("OP", 5, Rarity.LEGENDARY, false));
        rewardList.add(new LootboxDTO("Jajo smoka", 20, Rarity.LEGENDARY, false));
        rewardList.add(new LootboxDTO("bedrock", 30, Rarity.LEGENDARY, false));
        rewardList.add(new LootboxDTO("2x netherite", 50, Rarity.LEGENDARY, false));
        rewardList.add(new LootboxDTO("knockback 10 patyk", 50, Rarity.LEGENDARY, false));
        rewardList.add(new LootboxDTO("netherite upgrade", 65, Rarity.LEGENDARY, false));
        rewardList.add(new LootboxDTO("Glowa smoka", 70, Rarity.LEGENDARY, false));
        rewardList.add(new LootboxDTO("shulker", 70, Rarity.LEGENDARY, false));
        rewardList.add(new LootboxDTO("armor trim", 70, Rarity.LEGENDARY, false));
        rewardList.add(new LootboxDTO("music disk", 70, Rarity.LEGENDARY, false));
        rewardList.add(new LootboxDTO("enchant", 100, Rarity.LEGENDARY, false));
        rewardList.add(new LootboxDTO("zelazo", 100, Rarity.LEGENDARY, false));
        rewardList.add(new LootboxDTO("diament", 100, Rarity.LEGENDARY, false));
        rewardList.add(new LootboxDTO("emerald", 100, Rarity.LEGENDARY, false));
        rewardList.add(new LootboxDTO("blok wegla", 100, Rarity.LEGENDARY, false));

        int chanceSum = rewardList.stream().mapToInt(LootboxDTO::getChance).sum();
        int rand = (int)(Math.random() * chanceSum);

        int check = 0;
        for(int i = 0; i < rewardList.size(); i++){
            check += rewardList.get(i).getChance();
            if(rand <= check){
                return rewardList.get(i);
            }
        }
        return rewardList.get(rewardList.size()-1);
    }
}
