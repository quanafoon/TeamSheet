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
        
        Piece piece = new Piece(100, 100, 10, "blue", "white");
        Piece piece2 = new Piece(175, 100, 10, "red", "white");
        Piece piece3 = new Piece(100, 100, 10, "blue", "white");
        Piece piece4 = new Piece(100, 100, 10, "red", "white");
        Piece piece5 = new Piece(125, 200, 5, "white", "black");
        Piece piece6 = new Piece(125, 200, 5, "white", "black");
        Piece piece7 = new Piece(125, 200, 5, "white", "black");
        Piece piece8 = new Piece(125, 200, 5, "white", "black");

        team.setUser(user);
        team2.setUser(user);
        team3.setUser(user);
        team4.setUser(user);
        
        piece.setTeam(team);
        piece2.setTeam(team);
        piece3.setTeam(team2);
        piece4.setTeam(team3);
        piece5.setTeam(team);
        piece6.setTeam(team2);
        piece7.setTeam(team3);
        piece8.setTeam(team4);

        List<Team> teams = new ArrayList<>();
        List<Piece> pieces = new ArrayList<>();
        List<Piece> pieces2 = new ArrayList<>();
        List<Piece> pieces3 = new ArrayList<>();
        List<Piece> pieces4 = new ArrayList<>();

        teams.add(team);
        teams.add(team2);
        teams.add(team3);
        teams.add(team4);

        pieces.add(piece);
        pieces.add(piece2);
        pieces.add(piece5);
        pieces2.add(piece3);
        pieces2.add(piece6);
        pieces3.add(piece4);
        pieces3.add(piece7);
        pieces4.add(piece8);

        user.setTeams(teams);
        team.setPieces(pieces);
        team2.setPieces(pieces2);
        team3.setPieces(pieces3);
        team4.setPieces(pieces4);
        userRepository.save(user);
   }
}
