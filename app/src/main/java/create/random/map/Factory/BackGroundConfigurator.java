package create.random.map.Factory;

import create.random.map.Configurations;
import create.random.map.Pair;
import create.random.map.Utils.functions;

public abstract class BackGroundConfigurator implements Factory{

    @Override
    public void create(Pair pair, int[][] map, int rows, int cols) {
        createBorderAround(map);
    }

    public static void createBorderAround(int [][] map) {
        for (int i = 0; i < map.length; i++) {
            map[i][0] = Configurations.idexOfWallImage;
            map[i][map[0].length - 1] = Configurations.idexOfWallImage;
        }
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = Configurations.idexOfWallImage;
            map[map.length - 1][i] = Configurations.idexOfWallImage;
        }
    }

    public static void fillEmptySpaces(int[][] map) {
        int count=0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == Configurations.idexOfEarthImage) {
                    int prob = functions.generateRandomNumber(Configurations.probabilityOfRandomTree);
                    if(prob > 0 ){
                        map[i][j] = Configurations.idexOfGrassImage;
                    }else {
                        map[i][j] = Configurations.idexOfTreeImage;
                    }
                }
                if (map[i][j] == Configurations.idexOfSandImage) {
                    if (i + 1 < map.length - 1 && map[i + 1][j] == Configurations.idexOfWaterImage) {
                        count++;
                    }
                    if (i - 1 >= 1 && map[i - 1][j] == Configurations.idexOfWaterImage) {
                        count++;
                    }
                    if (j + 1 < map[0].length - 1 && map[i][j + 1] == Configurations.idexOfWaterImage) {
                        count++;
                    }
                    if (j - 1 >= 1 && map[i][j - 1] == Configurations.idexOfWaterImage) {
                        count++;
                    }
                    if(j + 1 < map[0].length - 1 && i + 1 < map.length - 1 && map[i + 1][j + 1] == Configurations.idexOfWaterImage) {
                        count++;
                    }
                    if(j - 1 >= 1 && i - 1 >= 1 && map[i - 1][j - 1] == Configurations.idexOfWaterImage) {
                        count++;
                    }
                    if(j + 1 < map[0].length - 1 && i - 1 >= 1 && map[i - 1][j + 1] == Configurations.idexOfWaterImage) {
                        count++;
                    }
                    if(j - 1 >= 1 && i + 1 < map.length - 1 && map[i + 1][j - 1] == Configurations.idexOfWaterImage) {
                        count++;
                    }


                    if (count >= 5) {
                        map[i][j] = Configurations.idexOfWaterImage;
                    }
                }

                count=0;
            }
        }
    }
    
}
