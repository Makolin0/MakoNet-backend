package MakoNetbackend.services;

import MakoNetbackend.models.DTO.*;
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

    public UserDTO getUserInfo(String username) {
        UserDB userDB = userRepository.findByUsernameIgnoreCase(username).get();
        return new UserDTO(userDB);
    }

    public List<UserAdminDTO> getAll() {
        return userRepository.findAll().stream().map(UserAdminDTO::new).toList();
    }
}
