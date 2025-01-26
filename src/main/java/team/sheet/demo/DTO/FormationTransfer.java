package team.sheet.demo.DTO;

import java.util.List;

import team.sheet.demo.models.Piece;

public class FormationTransfer {
    List<Piece> pieces;
    String formation;
    
    public List<Piece> getPieces() {
        return pieces;
    }
    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }
    public String getFormation() {
        return formation;
    }
    public void setFormation(String formation) {
        this.formation = formation;
    }
        
}
