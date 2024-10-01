package optas.lt.battleship.controllers;

import optas.lt.battleship.domain.dtos.MoveRequest;
import optas.lt.battleship.domain.dtos.MoveResult;
import optas.lt.battleship.domain.dtos.StartGameResponse;
import optas.lt.battleship.services.Game;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BattleshipController {
    private final Map<String, Game> games = new HashMap<>();

    @GetMapping("/")
    public String redirectToGame() {
        return "Redirecting to game API...";
    }

    @GetMapping("/startGame")
    public ResponseEntity<StartGameResponse> handleStartGame() {
        String gameId = UUID.randomUUID().toString();
        Game game = new Game(gameId);
        games.put(gameId, game);

        return new ResponseEntity<>(new StartGameResponse(gameId, game.getRemainingShots()), HttpStatus.OK);
    }

    @PostMapping("/{gameId}/move")
    public ResponseEntity<MoveResult> handlePlayerMove(@PathVariable String gameId, @RequestBody MoveRequest move) {
        Game game = games.get(gameId);

        if (game == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        MoveResult moveResult = game.processMove(move.getX(), move.getY());

        return new ResponseEntity<>(moveResult, HttpStatus.OK);
    }
}