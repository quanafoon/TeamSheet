package team.sheet.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import team.sheet.demo.Utility;
import team.sheet.demo.models.Piece;
import team.sheet.demo.models.Team;
import team.sheet.demo.models.User;
import team.sheet.demo.repositories.PieceRepository;
import team.sheet.demo.repositories.TeamRepository;
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
    TeamRepository teamRepository;

    @Autowired
    PieceRepository pieceRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/init")
    public ResponseEntity<Map<String, String>> init() {
        teamRepository.deleteAll();
        userRepository.deleteAll();
        pieceRepository.deleteAll();
        jdbcTemplate.execute("ALTER TABLE users ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE teams ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE pieces ALTER COLUMN id RESTART WITH 1");
        
        Utility.dummyData(userRepository);
        Map<String, String> response = new HashMap<>();
        response.put("message", "db initialized");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/teams/all")
    public ResponseEntity<List<Team>> teams() {
        List<Team> teams = teamRepository.findAll();
        return ResponseEntity.ok(teams);
    }
    
    @GetMapping("/pieces")
    public ResponseEntity<List<Piece>> pieces(){
        List<Piece> pieces = pieceRepository.findAll();
        return ResponseEntity.ok(pieces);
    }
    
}
