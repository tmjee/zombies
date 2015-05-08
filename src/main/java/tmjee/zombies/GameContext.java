package tmjee.zombies;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tmjee
 */
public class GameContext {

    private final Area area;
    private final List<GamePiece> gamePieces;

    private int zombieScore;

    public GameContext(Area area, List<GamePiece> gamePieces) {
        this.area = area;
        this.gamePieces = gamePieces;
    }

    public Area area() {
        return area;
    }

    public void incZombieScore() {
        zombieScore++;
    }

    public int zombieScore() {
        return zombieScore;
    }

    public List<GamePiece> allZombieGamePiece() {
        return gamePieces.stream()
                .filter(GamePiece::isZombie)
                .collect(Collectors.toList());
    }

}
