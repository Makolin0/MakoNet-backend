package MakoNetbackend.services;

import MakoNetbackend.models.DTO.LootboxDTO;
import MakoNetbackend.models.DTO.LootboxDrawDTO;
import MakoNetbackend.models.DTO.LootboxesDTO;
import MakoNetbackend.models.DTO.UserDTO;
import MakoNetbackend.models.database.*;
import MakoNetbackend.repositories.LootboxesRepository;
import MakoNetbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final LootboxesRepository lootboxesRepository;
    private final LootboxService lootboxService;

    public UserDTO getUserInfo(String username) {
        UserDB userDB = userRepository.findByUsernameIgnoreCase(username).get();
        return new UserDTO(userDB);
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
        LootboxDTO reward = lootboxService.draw();
        List<LootboxDTO> filler = lootboxService.drawFiller();

        var lootbox = LootboxesDB.builder()
                .user(userDB)
                .rarity(reward.getRarity())
                .reward(reward.getReward())
                .received(false)
                .build();
        lootboxesRepository.save(lootbox);
        userDB.setLootboxes(userDB.getLootboxes() - 1);

        return new LootboxDrawDTO(reward, filler);
    }
}
