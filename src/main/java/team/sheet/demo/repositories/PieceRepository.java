package team.sheet.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import team.sheet.demo.models.Piece;

public interface PieceRepository extends JpaRepository<Piece,Long> {
    List<Piece> findByTeamId(Long Id);
    void deleteByTeamId(Long Id);
}
