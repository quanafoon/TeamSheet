package team.sheet.demo.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import team.sheet.demo.DTO.Update;
import team.sheet.demo.models.Piece;
import team.sheet.demo.models.Team;
import team.sheet.demo.models.User;
import team.sheet.demo.repositories.PieceRepository;
import team.sheet.demo.repositories.TeamRepository;
import team.sheet.demo.repositories.UserRepository;

@Controller
public class Builder {
    

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PieceRepository pieceRepository;


    @GetMapping("/builder")
    public String builder(@RequestParam Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if(user == null){
            redirectAttributes.addFlashAttribute("message", "Please Login");
            return "redirect:/";
        }
        if(id==0){
            Team team = new Team("New Team", "this is a new team");
            model.addAttribute("team", team);
            return "builder";
        }
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if(optionalTeam.isPresent()){
            Team team = optionalTeam.get();
            List<Team> teams = teamRepository.findByUserId(user.getId());
            if(!teams.contains(team)){
                redirectAttributes.addFlashAttribute("message", "Select a team");
                return "redirect:/teams";
            }
            model.addAttribute("team", team);
            return "builder";
        }
        redirectAttributes.addFlashAttribute("message", "Select a team");
        return "redirect:/teams";
    }

    @Transactional
    @PostMapping("/saveNew")
    @ResponseBody
    public String saveNew(@RequestBody Update data, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if(user == null){
            return "teams";
        }
        String name = data.getDetails().get("name");
        String description = data.getDetails().get("description");
        List<Piece> pieces = data.getPieces();
        Team team = new Team(name, description);
        team.setPieces(pieces);
        for(Piece piece : pieces){
            piece.setTeam(team);
        }
        team.setUser(user);
        List<Team> teams = teamRepository.findByUserId(user.getId());
        teams.add(team);
        user.setTeams(teams);
        teamRepository.save(team);
        return "teams";
    }

    @Transactional
    @PostMapping("/save")
    @ResponseBody
    public String updateTeam(@RequestBody Update data, @RequestParam Long id, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if(user == null){
            return "teams";
        }
        List<Team> teams = teamRepository.findByUserId(user.getId());
        for(Team team : teams){
            if(team.getId() == id){
                List<Piece> pieces = data.getPieces();
                pieceRepository.deleteByTeamId(id);
                team.setPieces(pieces);
                for(Piece piece : pieces){
                    piece.setTeam(team);
                }
                team.setName(data.getDetails().get("name"));
                team.setDescription(data.getDetails().get("description"));
                teamRepository.save(team);
            }
        }
        return "teams";
    }
    
    @GetMapping("/teamPieces")
    @ResponseBody
    public ResponseEntity<List<Piece>> team_pieces(@RequestParam Long id) {
        Optional<Team> team = teamRepository.findById(id);
        if(team.isPresent()){
            List<Piece> pieces = pieceRepository.findByTeamId(id);
            return ResponseEntity.ok(pieces);
        }
        return ResponseEntity.badRequest().build();
    }
    
    
    
}
