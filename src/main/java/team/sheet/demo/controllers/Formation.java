package team.sheet.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import team.sheet.demo.Utility;
import team.sheet.demo.DTO.FormationTransfer;
import team.sheet.demo.models.Piece;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class Formation {
    
    @PostMapping("/selectFormation")
    public ResponseEntity<List<Piece>> select_formation(@RequestParam String color, @RequestBody FormationTransfer formationInfo) {
        System.out.println("Function reached");

        List<Piece> pieces = formationInfo.getPieces();
        List<Piece> newPieces = new ArrayList<>();
        String formation = formationInfo.getFormation();
        newPieces = Utility.getFormationPieces(formation, color);
        System.out.println("Before color check " + formation + " " + color);
        if(newPieces == null){
            System.out.println("New pieces was null");
            return ResponseEntity.badRequest().build();
        }
        if(color == "red"){
            for(Piece piece : pieces){
                if(piece.getColor() == "red"){
                    newPieces.add(piece);
                }
            }
        }
        else if(color == "blue"){
            System.out.println("Blue accepted");
            for(Piece piece : pieces){
                if(piece.getColor() == "red"){
                    newPieces.add(piece);
                }
            }
        }
        System.out.println("Here" + newPieces);
        return ResponseEntity.ok(newPieces);
    }
    
}
