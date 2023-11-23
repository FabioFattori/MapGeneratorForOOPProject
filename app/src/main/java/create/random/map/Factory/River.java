package create.random.map.Factory;

import create.random.map.Configurations;
import create.random.map.Pair;
import create.random.map.Utils.functions;

public class River implements Factory{

    @Override
    public void create(Pair pair, int[][] map, int rows, int cols) {
        int length = (int) Math.floor(Math.random() * (rows / 2) + 1);
        int width = (int) Math.floor(Math.random() * (cols / 2) + 1);

        for (int i = pair.x; i < pair.x + length; i++) {
            for (int j = pair.y; j < pair.y + width; j++) {
                functions.createBallOfIndex(Configurations.idexOfWaterImage, i, j,map);
            }
        }

        createSandAroundRiver(map);
    }

    private void createSandAroundRiver(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == Configurations.idexOfWaterImage) {
                    if (i + 1 < map.length - 1 && map[i + 1][j] != Configurations.idexOfWaterImage) {
                        map[i + 1][j] = Configurations.idexOfSandImage;
                    }
                    if (i - 1 >= 1 && map[i - 1][j] != Configurations.idexOfWaterImage) {
                        map[i - 1][j] = Configurations.idexOfSandImage;
                    }
                    if (j + 1 < map[0].length - 1 && map[i][j + 1] != Configurations.idexOfWaterImage) {
                        map[i][j + 1] = Configurations.idexOfSandImage;
                    }
                    if (j - 1 >= 1 && map[i][j - 1] != Configurations.idexOfWaterImage) {
                        map[i][j - 1] = Configurations.idexOfSandImage;
                    }
                }
            }
        }
    }
    
}
