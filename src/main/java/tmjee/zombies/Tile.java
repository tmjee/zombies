package tmjee.zombies;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tmjee
 */
public class Tile {

    private final List<GamePiece> gamePieces;

    public Tile(List<GamePiece> gamePieces) {
        this.gamePieces = new ArrayList(gamePieces);
    }

    public List<GamePiece> gamePieces() {
        return gamePieces;
    }
}
