package MakoNetbackend.services;

import MakoNetbackend.models.DTO.UserDTO;
import MakoNetbackend.models.database.UserDB;
import MakoNetbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO getUserInfo(String username) {
        UserDB userDB = userRepository.findByUsername(username).get();
        return new UserDTO(userDB);
    }
}
