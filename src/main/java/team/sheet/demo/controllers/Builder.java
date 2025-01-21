package team.sheet.demo.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import team.sheet.demo.models.Piece;
import team.sheet.demo.models.Team;
import team.sheet.demo.models.User;
import team.sheet.demo.repositories.TeamRepository;
import team.sheet.demo.repositories.UserRepository;

@Controller
public class Builder {
    

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;


    @GetMapping("/builder")
    public String builder(@RequestParam Long id, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if(user == null){
            redirectAttributes.addFlashAttribute("message", "Please Login");
            return "redirect:/";
        }
        if(id==0){
            Team team = new Team("newTeam", "this is a new team");
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

    @PostMapping("/save")
    @ResponseBody
    public String save(@RequestBody List<Piece> pieces) {
        for (Piece piece : pieces){
            System.out.println(piece.getX() + " " + piece.getY());
        }
        return "teams";
    }
    
}
