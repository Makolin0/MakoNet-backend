package MakoNetbackend.controllers;

import MakoNetbackend.models.DTO.*;
import MakoNetbackend.services.LootboxService;
import MakoNetbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "controller for user")
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final LootboxService lootboxService;

    @Operation(summary = "Get logged in user basic info")
    @GetMapping("/info")
    public ResponseEntity<UserDTO> getInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.getUserInfo(userDetails.getUsername()));
    }

    @Operation(summary = "Get logged in user lootboxes info")
    @GetMapping("/lootbox")
    public ResponseEntity<LootboxesDTO> getLootboxesInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(lootboxService.getLootboxesInfo(userDetails.getUsername()));
    }

    @Operation(summary = "Draw a lootbox")
    @PostMapping("/lootbox")
    public ResponseEntity<LootboxDrawDTO> postLootbox(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            return ResponseEntity.ok(lootboxService.drawLootbox(userDetails.getUsername()));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
