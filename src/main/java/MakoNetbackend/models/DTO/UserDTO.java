package MakoNetbackend.models.DTO;


import MakoNetbackend.models.database.UserDB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String nickname;
    private String role;

    public UserDTO(UserDB userDB){
        this.username = userDB.getUsername();
        this.nickname = userDB.getNickname();
        this.role = String.valueOf(userDB.getRole());
    }
}
