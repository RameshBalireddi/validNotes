package validNotes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import validNotes.Entities.User;
import validNotes.Repository.UserRepository;
    @RestController
    @RequestMapping("user")
    public class UserController {

        @Autowired
        UserRepository userRepository;
        @PostMapping("/signup")
        public String addUser(@RequestBody User user) {
            try {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encryptedPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(encryptedPassword);

                userRepository.save(user);
            }catch(Exception e){
                return  "error" +  e.getMessage();
            }
            return "user added";
        }
    }




