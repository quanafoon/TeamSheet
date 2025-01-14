package team.sheet.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import team.sheet.demo.models.User;
import team.sheet.demo.repositories.UserRepository;


@Controller
public class Index {
    
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String getMethodName(Model model) {
        model.addAttribute(new User());
        return "index";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model){
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if(optionalUser.isPresent()){
            User u = optionalUser.get();
            if (User.checkPassword(user.getPassword(), u.getPassword())) {
                return "builder";
            }
        }
        return "redirect:/";
    }
}
