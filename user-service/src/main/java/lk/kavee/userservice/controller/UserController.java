package lk.kavee.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lk.kavee.userservice.dto.AuthRequest;
import lk.kavee.userservice.entity.User;
import lk.kavee.userservice.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        System.out.println("AWaaaaaaaaaa" + user);
        try {
            return ResponseEntity.ok(service.saveUser(user));
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            return ResponseEntity.status(409).body("Username already exists!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody AuthRequest authRequest) {
        User user = service.validateAndGetUser(authRequest.getUsername(), authRequest.getPassword());
        if (user != null) {
            String token = service.generateToken(user.getUsername());
            return ResponseEntity.ok(new lk.kavee.userservice.dto.AuthResponse(token, user.getUsername(), user.getRole()));
        } else {
            return ResponseEntity.status(401).body("Invalid User Credentials");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return ResponseEntity.ok("Token is valid");
    }
}
