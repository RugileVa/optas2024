package optas.lt.battleship.repositories;

import optas.lt.battleship.services.Game;

import java.util.HashMap;
import java.util.Map;

public class GameRepository {
    private final Map<String, Game> games = new HashMap<>();
}
