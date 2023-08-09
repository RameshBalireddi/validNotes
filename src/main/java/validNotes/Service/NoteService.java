package validNotes.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validNotes.DTO.NoteDTO;
import validNotes.Entities.Note;
import validNotes.Repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    @Autowired
    NoteRepository noteRepository;
    public List<NoteDTO> getNotesByStatus(String status, int teamId, int userId) {
        List<NoteDTO> dtoList = new ArrayList<>();

        if ("inActive".equalsIgnoreCase(status)) {
            List<Note> notes = noteRepository.findByStatusAndTeamIdAndUserId(status, teamId, userId);
            if (notes != null) {
                for (Note inActiveNotes : notes) {
                    if (inActiveNotes != null && inActiveNotes.getTeam() != null) {
                        NoteDTO dto = new NoteDTO();
                        dto.setNoteId(inActiveNotes.getId());
                        dto.setTeamId(inActiveNotes.getTeam().getId());
                        dto.setNote(inActiveNotes.getNote());
                        dto.setCreatedAt(inActiveNotes.getCreatedAt()); // Corrected method invocation
                        dtoList.add(dto);
                    }
                }
            }
        } else if ("active".equalsIgnoreCase(status)) {
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
}
