package validNotes.Controllers;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import validNotes.Entities.TeamMember;
import validNotes.Repository.TeamMemberRepository;
@RestController
@RequestMapping("teamMember")
public class TeamMemberController {


    @Autowired
    TeamMemberRepository teamMemberRepository;


    @PostMapping("/add")
    public String addTeamMember(@RequestBody TeamMember teamMember) {
        try {
            teamMember.getTeam().getId();
            teamMemberRepository.save(teamMember);
        } catch (Exception e) {
            return "Error: " + e.getMessage(); // Return the error message
        }
        return "Member added to the team successfully";
    }




}
