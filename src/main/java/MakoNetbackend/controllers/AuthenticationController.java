package MakoNetbackend.controllers;

import MakoNetbackend.models.AuthenticationResponse;
import MakoNetbackend.models.DTO.LoginDTO;
import MakoNetbackend.models.DTO.RegisterDTO;
import MakoNetbackend.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "controller for authentication")
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @Operation(summary = "registration endpoint")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDTO request) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (IllegalArgumentException e){
            // email already exists
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "login endpoint")
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDTO request) {
        System.out.println("loguje uzytkownika: " + request.getEmail() + " , " + request.getPassword());
        return ResponseEntity.ok(service.login(request));
    }

}
