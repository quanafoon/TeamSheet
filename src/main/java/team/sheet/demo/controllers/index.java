package team.sheet.demo.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import team.sheet.demo.models.User;
import team.sheet.demo.repositories.UserRepository;







@Controller
public class Index {
    
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute(new User());
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        //redirectAttributes.addFlashAttribute("message", "An error occured");
        return "error";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes){
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if(optionalUser.isPresent()){
            User u = optionalUser.get();
            if (User.checkPassword(user.getPassword(), u.getPassword())) {
                System.out.println(u.getFirstname());
                redirectAttributes.addFlashAttribute("message", "Welcome " + u.getFirstname());
                redirectAttributes.addFlashAttribute("user", u);
                return "redirect:/teams";
            }
        }
        redirectAttributes.addFlashAttribute("message", "Invalid credentials");
        return "redirect:/";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if(optionalUser.isPresent()){
            redirectAttributes.addFlashAttribute("message", "Username already exists");
            return "redirect:/";
        }
        User newUser = new User(user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname());
        userRepository.save(newUser);
        redirectAttributes.addFlashAttribute("message", "Welcome " + newUser.getFirstname());
        return "redirect:/teams";
    }

    @GetMapping("/builder")
    public String builder() {
        return "builder";
    }
    
    @GetMapping("/teams")
    public String teams() {
        return "teams";
    }
    
    
}
