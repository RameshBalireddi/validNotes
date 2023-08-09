package validNotes.DTO;

import java.time.LocalDate;

public class NoteDTO {

    private int noteId;

    private int teamId;

    private String note;

    private LocalDate createdAt;

//    private LocalDate updatedAt;
//
//    private LocalDate deletedAt;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getCreatedAt(LocalDate createdAt) {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }


}
