package MakoNetbackend.services;

import MakoNetbackend.models.AuthenticationResponse;
import MakoNetbackend.models.DTO.LoginDTO;
import MakoNetbackend.models.DTO.RegisterDTO;
import MakoNetbackend.models.database.Role;
import MakoNetbackend.models.database.UserDB;
import MakoNetbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;


    public String register(RegisterDTO request) {
        var user = UserDB.builder()
                .nickname(request.getNickname())
                .username(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .isActive(true)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return "ok";
    }

    public AuthenticationResponse login(LoginDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = repository.findByUsername(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
