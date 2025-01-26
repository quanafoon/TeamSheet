package team.sheet.demo.models;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<Team> teams;
    

    public User(){

    }
    public User(String username, String password, String firstname, String lastname){
        this.username = username;
        this.password = hashPassword(password);
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
       this.password = password;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public List<Team> getTeams() {
        return teams;
    }
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public static boolean checkPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }
    
}
