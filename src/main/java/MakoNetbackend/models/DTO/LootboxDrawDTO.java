package MakoNetbackend.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LootboxDrawDTO {
    private LootboxDTO reward;
    private List<LootboxDTO> fillerList;
}
