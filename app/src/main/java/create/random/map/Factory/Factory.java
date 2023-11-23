package create.random.map.Factory;

import create.random.map.Pair;

public interface Factory {
    public void create(Pair pair, int[][] map , int rows, int cols);
}
