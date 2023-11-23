package create.random.map.Factory;

import create.random.map.BFSVisit;
import create.random.map.Configurations;
import create.random.map.Pair;
import create.random.map.Utils.functions;

public class Forest implements Factory {

    @Override
    public void create(Pair pair, int[][] map, int rows, int cols) {
        int pathIsSpawning = functions.generateRandomNumber(4);
        /*
        if (pathIsSpawning == 0) {

            createScriptedForest(pair, map);
            return;
        }
        */

        int length = functions.generateRandomNumber((rows / 2), -1);
        int width = functions.generateRandomNumber((cols / 2), -1);
        for (int i = pair.x; i < pair.x + length; i++) {
            for (int j = pair.y; j < pair.y + width; j++) {

                functions.createBallOfIndex(Configurations.idexOfTreeImage, i, j, map);
            }
        }
        createGrassAroundForest(map);
    }

    private void createGrassAroundForest(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == Configurations.idexOfTreeImage) {
                    if (i + 1 < map.length - 1 && map[i + 1][j] != Configurations.idexOfTreeImage
                            && map[i + 1][j] != Configurations.idexOfWaterImage
                            && map[i + 1][j] != Configurations.idexOfSandImage) {
                        map[i + 1][j] = Configurations.idexOfGrassImage;
                    }
                    if (i - 1 >= 1 && map[i - 1][j] != Configurations.idexOfTreeImage
                            && map[i - 1][j] != Configurations.idexOfWaterImage
                            && map[i - 1][j] != Configurations.idexOfSandImage) {
                        map[i - 1][j] = Configurations.idexOfGrassImage;
                    }
                    if (j + 1 < map[0].length - 1 && map[i][j + 1] != Configurations.idexOfTreeImage
                            && map[i][j + 1] != Configurations.idexOfWaterImage
                            && map[i][j + 1] != Configurations.idexOfSandImage) {
                        map[i][j + 1] = Configurations.idexOfGrassImage;
                    }
                    if (j - 1 >= 1 && map[i][j - 1] != Configurations.idexOfTreeImage
                            && map[i][j - 1] != Configurations.idexOfWaterImage
                            && map[i][j - 1] != Configurations.idexOfSandImage) {
                        map[i][j - 1] = Configurations.idexOfGrassImage;
                    }
                }
            }
        }
    }

    private void createScriptedForest(Pair coordinates, int[][] map) {

    }
}
