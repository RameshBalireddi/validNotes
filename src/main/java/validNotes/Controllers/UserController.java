package validNotes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import validNotes.Entities.User;
import validNotes.Repository.UserRepository;
@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    UserRepository userRepository;

       @PostMapping("/signup")
       public String addUser(@RequestBody User user){
           userRepository.save(user);
           return "user added";
       }
    }



