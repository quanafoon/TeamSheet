package team.sheet.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import team.sheet.demo.models.User;
import team.sheet.demo.repositories.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class Users {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/init")
    public ResponseEntity<Map<String, String>> init() {
        userRepository.deleteAll();
        jdbcTemplate.execute("ALTER TABLE users ALTER COLUMN id RESTART WITH 1");
        User user = new User("quan", "pass", "quan", "afoon");
        userRepository.save(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "db initialized");
        return ResponseEntity.ok(response);
    }
    
    
}
