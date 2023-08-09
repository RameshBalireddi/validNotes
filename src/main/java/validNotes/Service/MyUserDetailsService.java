package validNotes.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import validNotes.Entities.User;
import validNotes.Repository.UserRepository;
import validNotes.Security.ApplicationUser;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user= Optional.ofNullable(userRepository.findByEmail(email));
        if(user==null){
            throw new UsernameNotFoundException("user notfound 404");
        }
        return  user.map(ApplicationUser::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found"+email));
    }
}
