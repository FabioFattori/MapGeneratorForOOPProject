package create.random.map;

public class Pair {
    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public static Pair of(int x, int y) {
        return new Pair(x, y);
    }

    public Pair() {
        this(0, 0);
    }
}
