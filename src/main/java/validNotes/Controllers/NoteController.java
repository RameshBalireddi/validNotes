package validNotes.Controllers;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import validNotes.DTO.NoteDTO;
import validNotes.Entities.Note;
import validNotes.Repository.NoteRepository;
import validNotes.Repository.UserRepository;
import validNotes.Service.NoteService;
import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    NoteService noteService;

    @PostMapping("/add")
    public String addNote(@RequestBody Note note) {
               noteRepository.save(note);
        return "Note added successfully";
    }

    @GetMapping("/list/{teamId}")
    public List<NoteDTO> getNoteByStatus(@PathVariable int teamId,
            @RequestParam(value = "status", defaultValue = "active", required = false) String status,
           HttpServletRequest request
    ) {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            String userEmail = ((UserDetails) authentication.getPrincipal()).getUsername();
//            User userObj = userRepository.findByEmail(userEmail);
//            int userId=    userObj.getId();

            int userId = (int)  request.getAttribute("userId");
            System.out.println(userId + "---id controller");
            List<NoteDTO> dtoList = noteService.getNotesByStatus(status, teamId, userId);
            return dtoList;
    }
}
