package tmjee.zombies;

/**
 * @author tmjee
 */
public abstract class Type {

    private GameContext gameContext;
    private Position position;

    void init(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    public boolean canMove() { return false; };
    public void move() {};
    public void action() {};

    public void position(Position position) {
        this.position = position;
    }

    public Position position() {
        return position;
    }


    protected GameContext gameContext() {
        return gameContext;
    }

}
