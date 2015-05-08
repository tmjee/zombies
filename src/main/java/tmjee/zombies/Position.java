package tmjee.zombies;

import static java.lang.String.format;

/**
 * @author tmjee
 */
public class Position {

    private final int dimension;
    private int x, y;

    public Position(int dimension, int x, int y) {
        this.dimension = dimension;
        this.x = x;
        this.y = y;
    }

    public int x() {return x;}
    public int y() {return y;}

    public void move(char direction) {
        switch(direction) {
            case 'D':
                y = ((y + 1) % dimension);
                break;

            case 'L':
                x = (x - 1);
                if (x < 0)
                    x = dimension + x;
                x = x % dimension;
                break;

            case 'U':
                y = ((y - 1) % dimension);
                if (y < 0)
                    y = dimension + y;
                y = y % dimension;
                break;

            case 'R':
                x = ((x + 1) % dimension);
                break;

            default:
                throw new IllegalArgumentException(format("bad direction %s", direction));
        }
    }
}
