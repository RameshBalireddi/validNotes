package validNotes.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import validNotes.Entities.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember,Integer> {


}
