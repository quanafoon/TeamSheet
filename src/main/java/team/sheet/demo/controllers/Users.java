package team.sheet.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import team.sheet.demo.models.Team;
import team.sheet.demo.models.User;
import team.sheet.demo.repositories.TeamRepository;
import team.sheet.demo.repositories.UserRepository;

import java.util.ArrayList;
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
        jdbcTemplate.execute("ALTER TABLE users ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE teams ALTER COLUMN id RESTART WITH 1");
        User user = new User("quan", "pass", "quan", "afoon");
        Team team = new Team("first", "something");
        Team team2 = new Team("second", "something");
        Team team3 = new Team("third", "something");
        Team team4 = new Team("fourth", "something");
        Team team5 = new Team("fifth", "something");
        Team team6 = new Team("sixth", "something");
        Team team7 = new Team("seventh", "something");
        Team team8 = new Team("eigth", "something");
        Team team9 = new Team("ninth", "something");
        Team team10 = new Team("tenth", "something");
        Team team11 = new Team("eleventh", "something");

        team.setUser(user);
        team2.setUser(user);
        team3.setUser(user);
        team4.setUser(user);
        team5.setUser(user);
        team6.setUser(user);
        team7.setUser(user);
        team8.setUser(user);
        team9.setUser(user);
        team10.setUser(user);
        team11.setUser(user);

        List<Team> teams = new ArrayList<>();
        teams.add(team);
        teams.add(team2);
        teams.add(team3);
        teams.add(team4);
        teams.add(team5);
        teams.add(team6);
        teams.add(team7);
        teams.add(team8);
        teams.add(team9);
        teams.add(team10);
        teams.add(team11);


        user.setTeams(teams);
        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "db initialized");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/teams/all")
    public ResponseEntity<List<Team>> teams() {
        List<Team> teams = teamRepository.findAll();
        return ResponseEntity.ok(teams);
    }
    
    
}
