package validNotes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import validNotes.Entities.Note;

import java.util.List;

public interface NoteRepository extends JpaRepository <Note,Integer>{
//    @Query("SELECT n FROM Note n WHERE n.status = :status AND n.team.id = :teamId")
//    List<Note> findByNoteStatusAndTeamId(@Param("status") String status, @Param("teamId") int teamId);

    @Query(value = "SELECT * FROM notes WHERE status = :status AND team_id = :teamId", nativeQuery = true)
    List<Note> findByStatusAndTeamId(@Param("status") String status, @Param("teamId") int teamId);


    @Query(value = "SELECT * FROM notes WHERE status = :status AND team_id = :teamId and member_id=:memberId ", nativeQuery = true)
    List<Note> findByStatusAndTeamIdAndUserId(@Param("status") String status, @Param("teamId") int teamId,@Param("memberId") int memberId);



    List<Note> findByTeamId(int id);
}
