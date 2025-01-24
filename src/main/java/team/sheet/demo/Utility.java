package team.sheet.demo;

import java.util.ArrayList;
import java.util.List;
import team.sheet.demo.models.Piece;
import team.sheet.demo.models.Team;
import team.sheet.demo.models.User;
import team.sheet.demo.repositories.UserRepository;

public class Utility {
    

    public static void dummyData(UserRepository userRepository){
        
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
        Piece piece = new Piece(1, 2, 10, "blue", "white");
        Piece piece2 = new Piece(2, 3, 10, "red", "white");
        Piece piece3 = new Piece(3, 4, 10, "blue", "red");
        Piece piece4 = new Piece(4, 5, 10, "red", "blue");


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
        piece.setTeam(team);
        piece2.setTeam(team);
        piece3.setTeam(team2);
        piece4.setTeam(team3);

        List<Team> teams = new ArrayList<>();
        List<Piece> pieces = new ArrayList<>();
        List<Piece> pieces2 = new ArrayList<>();
        List<Piece> pieces3 = new ArrayList<>();
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
        pieces.add(piece);
        pieces.add(piece2);
        pieces2.add(piece3);
        pieces3.add(piece4);

        user.setTeams(teams);
        team.setPieces(pieces);
        team2.setPieces(pieces2);
        team3.setPieces(pieces3);
        userRepository.save(user);
   }
}
