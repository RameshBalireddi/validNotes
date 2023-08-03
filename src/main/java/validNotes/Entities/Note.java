package validNotes.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noteId;

    @Positive(message = "Team ID must be a positive integer.")
    private int teamId;
    @Positive(message = "Team ID must be a positive integer.")
    private int memberId;

    @NotBlank(message = "Notes field must not be blank.")
    private String note;
    @Pattern(regexp = "^(Active|Inactive)$", message = "Note status must be 'Active' or 'Inactive'")
    private String status;

    private LocalDate createdAt;
    private LocalDate updatedAt;
}
