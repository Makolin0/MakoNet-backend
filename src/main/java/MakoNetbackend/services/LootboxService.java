package MakoNetbackend.services;

import MakoNetbackend.models.DTO.LootboxAdminDTO;
import MakoNetbackend.models.DTO.LootboxDTO;
import MakoNetbackend.models.DTO.LootboxDrawDTO;
import MakoNetbackend.models.DTO.LootboxesDTO;
import MakoNetbackend.models.database.LootboxesDB;
import MakoNetbackend.models.database.Rarity;
import MakoNetbackend.models.database.Rewards;
import MakoNetbackend.models.database.UserDB;
import MakoNetbackend.repositories.LootboxesRepository;
import MakoNetbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LootboxService {
    private final UserRepository userRepository;
    private final LootboxesRepository lootboxesRepository;


    public LootboxDTO draw() {
        List<LootboxDTO> rewardList = new ArrayList<>();
        rewardList.add(new LootboxDTO("OP", 5, Rarity.LEGENDARY));
        rewardList.add(new LootboxDTO("Jajo smoka", 20, Rarity.LEGENDARY));
        rewardList.add(new LootboxDTO("bedrock", 30, Rarity.LEGENDARY));
        rewardList.add(new LootboxDTO("2x netherite", 50, Rarity.RARE));
        rewardList.add(new LootboxDTO("knockback 10 patyk", 50, Rarity.RARE));
        rewardList.add(new LootboxDTO("netherite upgrade", 65, Rarity.RARE));
        rewardList.add(new LootboxDTO("Glowa smoka", 70, Rarity.UNCOMMON));
        rewardList.add(new LootboxDTO("shulker", 70, Rarity.UNCOMMON));
        rewardList.add(new LootboxDTO("armor trim", 70, Rarity.UNCOMMON));
        rewardList.add(new LootboxDTO("music disk", 70, Rarity.UNCOMMON));
        rewardList.add(new LootboxDTO("enchant", 100, Rarity.COMMON));
        rewardList.add(new LootboxDTO("zelazo", 100, Rarity.COMMON));
        rewardList.add(new LootboxDTO("diament", 100, Rarity.COMMON));
        rewardList.add(new LootboxDTO("emerald", 100, Rarity.COMMON));
        rewardList.add(new LootboxDTO("blok wegla", 100, Rarity.COMMON));

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
    public List<LootboxDTO> drawFiller(){
        List<LootboxDTO> filler = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            filler.add(draw());
        }
        return filler;
    }


    public LootboxesDTO getLootboxesInfo(String username){
        UserDB userDB = userRepository.findByUsernameIgnoreCase(username).get();
        return new LootboxesDTO(userDB);
    }

    public LootboxDrawDTO drawLootbox(String username) throws IllegalArgumentException{
        UserDB userDB = userRepository.findByUsernameIgnoreCase(username).get();
        if(userDB.getLootboxes() < 1){
            throw new IllegalArgumentException();
        }
        LootboxDTO reward = draw();
        List<LootboxDTO> filler = drawFiller();

        var lootbox = LootboxesDB.builder()
                .user(userDB)
                .rarity(reward.getRarity())
                .reward(reward.getReward())
                .received(false)
                .drawTime(LocalDateTime.now())
                .build();
        lootboxesRepository.save(lootbox);
        userDB.setLootboxes(userDB.getLootboxes() - 1);
        userRepository.save(userDB);

        return new LootboxDrawDTO(reward, filler);
    }

    public List<LootboxAdminDTO> getAll(){
        return lootboxesRepository.findAll().stream().map(LootboxAdminDTO::new).sorted(Comparator.comparing(LootboxAdminDTO::getDrawTime).reversed()).toList();
    }

    public String markAsReceived(long id){
        LootboxesDB lootbox = lootboxesRepository.getReferenceById(id);
        lootbox.setReceived(true);
        lootboxesRepository.save(lootbox);
        return "OK";
    }

    public String addLootboxesToUser(long id, int amount) {
        UserDB userDB = userRepository.getReferenceById(id);
        userDB.setLootboxes(userDB.getLootboxes() + amount);
        userRepository.save(userDB);
        return "OK";
    }
}
