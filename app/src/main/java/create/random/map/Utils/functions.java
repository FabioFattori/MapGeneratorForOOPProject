package create.random.map.Utils;

import create.random.map.Configurations;
import create.random.map.Pair;

public class functions {
    public static int generateRandomNumber(int range, int toSubtract) {
        int randomNum = (int) Math.floor(Math.random() * range - toSubtract);

        return randomNum;
    }

    public static int generateRandomNumber(int range) {
        int randomNum = (int) Math.floor(Math.random() * range);
        return randomNum;
    }

    public static void createBallOfIndex(int index, int x, int y,int[][] map) {
        if (!checkIfCoordinatesAreValid(Pair.of(x, y), map))
            return;
        map[x][y] = index;
        if (y + 1 < map[0].length - 1 && map[x][y + 1] == Configurations.idexOfEarthImage) {
            map[x][y + 1] = index;
        }

        if (y - 1 >= 1 && map[x][y - 1] == Configurations.idexOfEarthImage) {
            map[x][y - 1] = index;
        }

        if (x + 1 < map.length - 1 && map[x + 1][y] == Configurations.idexOfEarthImage) {

            map[x + 1][y] = index;
        }

        if (x - 1 >= 1 && map[x - 1][y] == Configurations.idexOfEarthImage) {
            map[x - 1][y] = index;
        }

        if (x + 1 < map.length - 1 && y + 1 < map[0].length - 1
                && map[x + 1][y + 1] == Configurations.idexOfEarthImage) {
            map[x + 1][y + 1] = index;
        }

        if (x + 1 < map.length - 1 && y - 1 >= 1 && map[x + 1][y - 1] == Configurations.idexOfEarthImage) {
            map[x + 1][y - 1] = index;
        }

        if (x - 1 >= 1 && y + 1 < map[0].length - 1 && map[x - 1][y + 1] == Configurations.idexOfEarthImage) {
            map[x - 1][y + 1] = index;
        }

        if (x - 1 >= 1 && y - 1 >= 1 && map[x - 1][y - 1] == Configurations.idexOfEarthImage) {
            map[x - 1][y - 1] = index;
        }

        if (x + 2 < map.length - 1 && map[x + 2][y] == Configurations.idexOfEarthImage) {
            map[x + 2][y] = index;
        }

        if (x - 2 >= 1 && map[x - 2][y] == Configurations.idexOfEarthImage) {
            map[x - 2][y] = index;
        }

        if (y + 2 < map[0].length - 1 && map[x][y + 2] == Configurations.idexOfEarthImage) {
            map[x][y + 2] = index;
        }

        if (y - 2 >= 1 && map[x][y - 2] == Configurations.idexOfEarthImage) {
            map[x][y - 2] = index;
        }
    }

    public static boolean checkIfCoordinatesAreValid(Pair coordinates, int[][] map) {
        if (coordinates.x <= 1 || coordinates.y <= 1 || coordinates.x > map.length - 2 || coordinates.y > map[0].length - 2
                || map[coordinates.x][coordinates.y] != Configurations.idexOfEarthImage) {
            return false;
        }

        return true;
    }

    public static boolean checkCoordinatesForForest(Pair coordinates, int[][] map) {
        if (coordinates.x <= 1 || coordinates.y <= 1 || coordinates.x > map.length - 2 || coordinates.y > map[0].length - 2
                ) {
            return false;
        }

        return true;
    }
}
