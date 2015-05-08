package tmjee.zombies;

import java.util.List;

/**
 * @author tmjee
 */
public class Zombie extends Type {

    public static Zombie toZombie(Zombie parentZombie, Creature creature) {
        Zombie zombie = new Zombie(parentZombie.directions());
        zombie.position(creature.position());
        zombie.init(creature.gameContext());
        return zombie;
    }


    private boolean started;
    private char[] directions;
    private int currentDirectionIndex;

    public Zombie(char[] directions) {
        this.directions = directions;
        currentDirectionIndex = 0;
    }

    @Override
    public boolean canMove() {
        return (currentDirectionIndex < this.directions.length);
    }

    public char[] directions() {
        return directions;
    }

    @Override
    public void action() {
        if (!started) {
            started = true;
            bite();
        }

        // move
        if (canMove()) {
            position().move(directions[currentDirectionIndex++]);
            bite();
        }
    }

    private void bite() {
        List<GamePiece> gamePieces = gameContext().area().gamePieceAt(position());
        gamePieces.stream()
                .filter(GamePiece::isCreature)
                .forEach((gp) -> {
                    gp.toZombie(this);
                    gameContext().incZombieScore();
                });
    }


}
