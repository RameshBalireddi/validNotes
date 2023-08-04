package validNotes.Entities;

import jakarta.persistence.*;
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
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Positive(message = "Team ID must be a positive integer.")
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Positive(message = "Team ID must be a positive integer.")
    @ManyToOne
    @JoinColumn(name = "member_id")
    private User user;

    @NotBlank(message = "Notes field must not be blank.")
    private String note;
    @Pattern(regexp = "^(Active|Inactive)$", message = "Note status must be 'Active' or 'Inactive'")
    private String status;

    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate deletedAt;


}
