package MakoNetbackend.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Tag(name = "Tests", description = "Various tests and experiments")
public class TestController {

    @GetMapping
    public ResponseEntity<String> sendText () {
        return ResponseEntity.ok("Tekst pobrany z back-endu");
    }
}
