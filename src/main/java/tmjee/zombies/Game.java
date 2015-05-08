package tmjee.zombies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * @author tmjee
 */
public class Game {

    public static void main(String[] args) throws Exception {

        List<GamePiece> moveables = new ArrayList();
        Area area = null;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("dimension of the area (default to 4):>");
            String line1 = scanner.nextLine().trim();
            System.out.print("initial position of the Zombie (default to 2 1):>");
            String[] line2 = scanner.nextLine().trim().split("\\s+");
            System.out.print("a list of positions of poor creature (default to 0 1,1 2,3 1):>");
            String[] line3 = scanner.nextLine().trim().split(",");
            System.out.print("and a list of moves zombies will make (default to DLUURR):>");
            String line4 = scanner.nextLine();


            line1 = line1.isEmpty() ? "4" : line1;
            line2 = line2.length == 1 ? new String[]{"2", "1"} : line2;
            line3 = line3.length == 1 ? new String[]{"0 1", "1 2", "3 1"} : line3;
            line4 = line4.isEmpty() ? "DLUURR" : line4;

            System.out.println("\n=== summary of input ===");
            System.out.println("dimension> "+line1);
            System.out.println("initial position of zombie> "+ Arrays.deepToString(line2));
            System.out.println("list of position of poor creature> "+ Arrays.deepToString(line3));
            System.out.println("and a list of moves zombies will make> "+line4);
            System.out.println("");

            // area
            int dimension = Integer.parseInt(line1);
            area = new Area(dimension);


            // zombie
            Position zombiePosition = new Position(
                    dimension, Integer.parseInt(line2[0]), Integer.parseInt(line2[1]));
            moveables.add(new GamePiece(zombiePosition, new Zombie(line4.toCharArray())));

            // creatures
            for (int a=0; a<line3.length; a++) {
                String[] coordinatePair = line3[a].split("\\s");
                Position creaturesPosition = new Position(
                        dimension, Integer.parseInt(coordinatePair[0]), Integer.parseInt(coordinatePair[1]));
                moveables.add(new GamePiece(creaturesPosition, new Creature()));
            }
        }

        Game game = new Game(moveables, area);
        game.start();
    }


    private final List<GamePiece> gamePieces;
    private final Area area;
    private final GameContext gameContext;

    public Game(List<GamePiece> gamePieces, Area area) {
        this.gamePieces = gamePieces;
        this.area = area;
        this.gameContext = new GameContext(area, gamePieces);
    }

    public void start() {

        System.out.println("\n==== Game started ===\n");

        // init
        gamePieces.stream().forEach((gc)->gc.init(gameContext));


        // put game pieces into game area
        gamePieces.stream().forEach((gp)->area.putGamePiece(gp));



        // game loop
        while(hasMovesLeft()) {
            for (GamePiece gamePiece : this.gamePieces) {
                gamePiece.action();
            }
        }

        // game end, print stats
        System.out.println("\n=== Game ended (statistics) ===\n");
        System.out.println(
                format("zombies score: %s", gameContext.zombieScore()));
        System.out.println(format("zombies position: %s",
            gameContext.allZombieGamePiece()
                .stream()
                .map((z) -> z.position().x() + " " + z.position().y())
                .collect(Collectors.joining(","))
        ));
    }

    public GameContext gameContext() {
        return gameContext;
    }

    private boolean hasMovesLeft() {
        for (GamePiece gamePiece : gamePieces) {
            if (gamePiece.canMove()) {
                return true;
            }
        }
        return false;
    }
}
