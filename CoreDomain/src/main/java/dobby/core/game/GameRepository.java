package dobby.core.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gautierc on 10/12/15.
 */
public class GameRepository {

    private List<Game> games;

    public GameRepository(List<Game> availablesGames) {
        games = new ArrayList<Game>();
        for (Game game : availablesGames)
            addGame(game);
    }

    public boolean exists(Game game) {
        return games.stream()
                .filter(g -> game.equals(g))
                .findFirst()
                .isPresent();
    }

    public void addGame(Game game) {
        if (!exists(game))
            games.add(game);
    }

    public void removeGame(Game game) {
        if (exists(game))
            games.remove(game);
    }
}
