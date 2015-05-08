package tmjee.zombies;

/**
 * @author tmjee
 */
public class GamePiece {

    private Type type;

    public GamePiece(Position position, Type type) {
        this.type = type;
        this.type.position(position);
    }

    public void init(GameContext gameContext) {
        type.init(gameContext);
    }

    public boolean canMove() {
        return type.canMove();
    }

    public Position position() {
        return type.position();
    }

    public void action() {
        type.action();
    }


    public boolean isCreature() {
        return (type instanceof Creature);
    }

    public boolean isZombie() {
        return (type instanceof Zombie);
    }

    public void toZombie(Zombie parentZombie) {
        if (isCreature()) {
            Zombie zombie = Zombie.toZombie(parentZombie, (Creature)type);
            type = zombie;
        }
    }


}
