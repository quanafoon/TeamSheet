package team.sheet.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

import team.sheet.demo.models.Team;
import team.sheet.demo.models.User;
import team.sheet.demo.repositories.TeamRepository;
import team.sheet.demo.repositories.UserRepository;








@Controller
public class Index {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(new User());
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, RedirectAttributes redirectAttributes, HttpSession session){
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if(optionalUser.isPresent()){
            User u = optionalUser.get();
            if (User.checkPassword(user.getPassword(), u.getPassword())) {
                System.out.println(u.getFirstname());
                redirectAttributes.addFlashAttribute("message", "Welcome " + u.getFirstname());
                session.setAttribute("loggedInUser", u);
                return "redirect:/teams";
            }
        }
        redirectAttributes.addFlashAttribute("message", "Invalid credentials");
        return "redirect:/";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if(optionalUser.isPresent()){
            redirectAttributes.addFlashAttribute("message", "Username already exists");
            return "redirect:/";
        }
        User newUser = new User(user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname());
        userRepository.save(newUser);
        redirectAttributes.addFlashAttribute("message", newUser.getFirstname() + " created");
        return "redirect:/";
    }

    @GetMapping("/builder")
    public String builder() {
        return "builder";
    }


    @GetMapping("/teams")
    public String teams(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if(user == null){
            redirectAttributes.addFlashAttribute("message", "Please Login");
            return "redirect:/";
        }
        List<Team> teams = teamRepository.findByUserId(user.getId());
        model.addAttribute("teams", teams);
        model.addAttribute("user", user);
        model.addAttribute("newTeam", new Team("first"));
        return "teams";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("loggedInUser");
        if(user != null){
            session.removeAttribute("loggedInUser");
            redirectAttributes.addFlashAttribute("message", "Logged out");
        }
        return "redirect:/";
    }
    
    
}
