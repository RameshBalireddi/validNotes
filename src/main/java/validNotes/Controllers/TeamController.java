package validNotes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import validNotes.Entities.Team;
import validNotes.Repository.TeamRepository;
@RestController
@RequestMapping("Team")
public class TeamController {


    @Autowired
    TeamRepository teamRepository;

            @PostMapping("/add")
               public String addTeam(@RequestBody Team team) {
                Team addTeam = teamRepository.save(team);
                if (addTeam != null) {
                    return "Team added successfully ";

                }
                return "something error occur while team is added";
            }
    }
