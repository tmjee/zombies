package tmjee.zombies;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tmjee
 */
public class Area {

    private final Tile[][] tiles;

    public Area(int dimension) {
        this.tiles = new Tile[dimension][dimension];
        for (int y=0; y<tiles.length; y++) {
           for (int x=0; x<tiles[y].length; x++) {
               tiles[y][x] = new Tile(new ArrayList());
           }
        }
    }


    public List<GamePiece> gamePieceAt(Position position) {
        return tiles[position.y()][position.x()].gamePieces();
    }

    public void putGamePiece(GamePiece gamePiece) {
        tiles[gamePiece.position().y()][gamePiece.position().x()].gamePieces().add(gamePiece);
    }
}
