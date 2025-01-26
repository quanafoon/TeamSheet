package team.sheet.demo.DTO;

import java.util.List;
import java.util.Map;

import team.sheet.demo.models.Piece;

public class Update {

    private List<Piece> pieces;
    private Map<String, String> details;
    public List<Piece> getPieces() {
        return pieces;
    }
    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }
    public Map<String, String> getDetails() {
        return details;
    }
    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
    
}
