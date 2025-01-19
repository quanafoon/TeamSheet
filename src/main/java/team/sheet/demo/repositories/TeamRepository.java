package team.sheet.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import team.sheet.demo.models.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{
    List<Team> findByUserId(Long userId);
}
