package organic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import organic.dto.LoginRequest;
import organic.model.User;
import organic.service.AuthService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService service;

    // ── REGISTER ─────────────────────────────────────────────────────────────
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            // Check if email already exists
            if (service.emailExists(user.getEmail())) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Email already registered");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            User saved = service.register(user);

            // Never send password back
            saved.setPassword(null);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Registration successful");
            response.put("user", saved);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Registration failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // ── LOGIN ─────────────────────────────────────────────────────────────────
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = service.login(request.getIdentifier(), request.getPassword());

            if (user == null) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Invalid email/phone or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
            }

            user.setPassword(null);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("token", "dummy-token-" + user.getId());
            response.put("user", user);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "Login failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }}