package MakoNetbackend.controllers;

import MakoNetbackend.models.AuthenticationResponse;
import MakoNetbackend.models.DTO.LoginDTO;
import MakoNetbackend.models.DTO.RegisterDTO;
import MakoNetbackend.models.DTO.UserDTO;
import MakoNetbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "controller for user")
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @Operation(summary = "Get logged in user info")
    @GetMapping("/info")
    public ResponseEntity<UserDTO> getInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(service.getUserInfo(userDetails.getUsername()));
    }
}
