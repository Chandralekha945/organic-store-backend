package organic.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import organic.model.User;
import organic.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public List<User> getUsers() {
        List<User> users = repository.findAll();
        users.forEach(u -> u.setPassword(null));
        return users;
    }
}