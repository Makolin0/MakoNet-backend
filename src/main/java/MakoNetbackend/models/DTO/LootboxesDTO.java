package MakoNetbackend.models.DTO;

import MakoNetbackend.models.database.UserDB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LootboxesDTO {
    private int available;
    private List<LootboxDTO> openedList;

    public LootboxesDTO(UserDB user){
        this.available = user.getLootboxes();
        this.openedList = user.getLootboxesList().stream().map(LootboxDTO::new)
                .sorted(Comparator.comparing(LootboxDTO::getDrawTime).reversed()).collect(Collectors.toList());
    }
}
