package organic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import organic.model.User;
import organic.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    
    public User register(User user) {
        // In production: encode password with BCrypt
        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    // ── LOGIN ─────────────────────────────────────────────────────────────────
    public User login(String identifier, String password) {
        // Find user by email or phone depending on what was entered
        User user;
        if (identifier.contains("@")) {
            user = repository.findByEmail(identifier).orElse(null);
        } else {
            user = repository.findByPhone(identifier).orElse(null);
        }

        if (user == null) {
            return null; // Not found
        }

        // Check password matches
        // NOTE: passwords are stored as plain text here.
        // If you use BCrypt later, replace with:
        // if (!passwordEncoder.matches(password, user.getPassword())) return null;
        if (!user.getPassword().equals(password)) {
            return null; // Wrong password
        }

        return user; // Login success
    }

    // ── CHECK EMAIL EXISTS ────────────────────────────────────────────────────
    public boolean emailExists(String email) {
        return repository.findByEmail(email).isPresent();
    }
}