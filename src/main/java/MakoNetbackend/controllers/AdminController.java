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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "controller for admins")
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final LootboxService lootboxService;

    @Operation(summary = "Get all users")
    @GetMapping("/users")
    public ResponseEntity<List<UserAdminDTO>> getUsers(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userService.getAll());
    }
    @Operation(summary = "Get all lootboxes")
    @GetMapping("/lootbox")
    public ResponseEntity<List<LootboxAdminDTO>> getLootboxes(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(lootboxService.getAll());
    }
    @Operation(summary = "marks a given lootbox as received")
    @PostMapping("/lootbox/receive")
    public ResponseEntity<String> getLootboxes(@AuthenticationPrincipal UserDetails userDetails, @RequestBody long id) {
        return ResponseEntity.ok(lootboxService.markAsReceived(id));
    }
    @Operation(summary = "add a given amount to user's lootbox count")
    @PostMapping("/lootbox/add")
    public ResponseEntity<String> addLootboxes(@AuthenticationPrincipal UserDetails userDetails, @RequestBody long id, int amount) {
        return ResponseEntity.ok(lootboxService.addLootboxesToUser(id, amount));
    }
}
