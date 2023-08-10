package validNotes.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import validNotes.DTO.NoteDTO;
import validNotes.Entities.Note;
import validNotes.Repository.NoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    NoteRepository noteRepository;

    public List<NoteDTO> getNotesByStatus(String status, int teamId, int userId) {
        List<NoteDTO> dtoList = new ArrayList<>();
        String ACTIVE="active",INACTIVE="inActive";

        if (INACTIVE.equalsIgnoreCase(status)) {
            List<Note> notes = noteRepository.findByStatusAndTeamIdAndUserId(status, teamId, userId);
            if (notes != null) {
                for (Note inActiveNotes : notes) {
                    if (inActiveNotes != null && inActiveNotes.getTeam() != null) {
                        NoteDTO dto = new NoteDTO();
                        dto.setNoteId(inActiveNotes.getId());
                        dto.setTeamId(inActiveNotes.getTeam().getId());
                        dto.setNote(inActiveNotes.getNote());
                        dto.setCreatedAt(inActiveNotes.getCreatedAt());
                        dtoList.add(dto);
                    }
                }
            }
        } else if (ACTIVE.equalsIgnoreCase(status)) {
            List<Note> allNotesActive = noteRepository.findByStatusAndTeamId(status, teamId);
            if (allNotesActive != null) {
                for (Note activeNote : allNotesActive) {
                    if (activeNote != null && activeNote.getTeam() != null && activeNote.getDeletedAt() == null) {
                        NoteDTO dto = new NoteDTO();
                        dto.setNoteId(activeNote.getId());
                        dto.setTeamId(activeNote.getTeam().getId());
                        dto.setNote(activeNote.getNote());
                        dto.setCreatedAt(activeNote.getCreatedAt());
                        dtoList.add(dto);
                    }
                }
            }
        }
        return dtoList;
    }

    public ResponseEntity<String> editNoteById(int noteId, int userId, String updateNote) {
        try {
            Optional<Note> optionalNote = noteRepository.findById(noteId);
            if (optionalNote.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
            }
            if (optionalNote.get().getUser().getId() != userId) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user unauthorised");
            }
            optionalNote.get().setNote(updateNote);
            noteRepository.save(optionalNote.get());

            return ResponseEntity.ok("Note updated successfully");
        } catch (Exception ex) {

            throw new RuntimeException("Error while updating note: " + ex.getMessage(), ex);
        }

    }

    public ResponseEntity<String> updateNoteStatus(int userId, int noteId, String status) {
        try {
            Optional<Note> note = noteRepository.findById(noteId);
            if (note.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
            }
            if (note.get().getUser().getId() != userId) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User unauthorized to update note status");
            }
            note.get().setStatus(status);
            noteRepository.save(note.get());
            return ResponseEntity.ok("Note status updated successfully");
        } catch (Exception ex) {

            throw new RuntimeException("Error while updating note status: " + ex.getMessage(), ex);
        }
    }
    public ResponseEntity<String> deleteNoteById(int notesId, int userId) {
        try {
            Optional<Note> optionalNote = noteRepository.findById(notesId);
            if (optionalNote.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
            if (optionalNote.get().getUser().getId() != userId)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User unauthorized to update note status");
            Note note = optionalNote.get();
            if ((note.getStatus()) == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note already deleted ...!");
            note.setStatus(null);
            note.setDeletedAt(LocalDate.from(LocalDateTime.now()));
            noteRepository.save(note);
            return ResponseEntity.ok("note deleted successfully");
        } catch (Exception ex) {
            throw new RuntimeException("Error while deleting note: " + ex.getMessage(), ex);
        }

    }
}
