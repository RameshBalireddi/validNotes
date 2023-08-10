package validNotes.Controllers;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import validNotes.DTO.NoteDTO;
import validNotes.Entities.Note;
import validNotes.Entities.User;
import validNotes.Repository.NoteRepository;
import validNotes.Repository.UserRepository;
import validNotes.Security.ApplicationUser;
import validNotes.Security.UserIdContextHolder;
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

    public int getUserId(){
        int userId=0;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if(authentication!=null &&   authentication.getPrincipal() instanceof ApplicationUser) {
         userId=  ((ApplicationUser) authentication.getPrincipal()).getUserId();
      }
          return userId;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNote(@RequestBody Note note) {
        try {
                       noteRepository.save(note);
            return ResponseEntity.ok()
                    .body("Note added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error while adding note: " + e.getMessage());
        }
    }
     @GetMapping("/list/{teamId}")
    public List<NoteDTO> getNoteByStatus(@PathVariable int teamId,
                                         @RequestParam(value = "status", defaultValue = "active", required = false) String status
                                        ){
                 int userId= getUserId();
        List<NoteDTO> dtoList = noteService.getNotesByStatus(status, teamId, userId);
            return dtoList;
    }

    @PutMapping("/update/{noteId}")
    public ResponseEntity<?> editNote(@PathVariable int noteId, @RequestBody String updateNote,
                                           HttpServletRequest request) {
                  int userId = getUserId();
           return noteService.editNoteById(noteId, userId, updateNote);
                       }


    @PutMapping("/changeStatus/{noteId}/{status}")
    public ResponseEntity<?> changeNoteStatus(
            @PathVariable int noteId, @PathVariable String status,
            HttpServletRequest request) {

        int userId = getUserId();
      return noteService.updateNoteStatus(userId, noteId, status);
              }

    @DeleteMapping("/delete/{noteId}")
    public ResponseEntity<?> deleteNoteById(@PathVariable int noteId, HttpServletRequest request) {
        int userId = getUserId();
                   return noteService.deleteNoteById(noteId, userId);

    }

}
