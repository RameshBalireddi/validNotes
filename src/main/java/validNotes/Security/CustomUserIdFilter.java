//package validNotes.Security;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//import validNotes.Entities.User;
//import validNotes.Repository.UserRepository;
//
//import java.io.IOException;
//
//public class CustomUserIdFilter extends OncePerRequestFilter {
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//            if (authentication != null && authentication.getPrincipal() instanceof ApplicationUser) {
//
//                String userEmail = ((ApplicationUser) authentication.getPrincipal()).getEmail();
//
//                if (userEmail != null) {
//                    User userObj = userRepository.findByEmail(userEmail);
//                    if (userObj != null) {
//                        int userId = userObj.getId();
//                        request.setAttribute("userId", userId);
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            throw new ServletException("An error occurred during authentication: " + ex.getMessage(), ex);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//
//}