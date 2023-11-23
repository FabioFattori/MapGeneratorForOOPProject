package create.random.map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public int currentForest = 0;
    public int currentRiver = 0;
    public int nCol = Configurations.maxCols;
    public int nRow = Configurations.maxRows;
    private boolean riverCreated = false;
    private boolean forestCreated = false;
    public int[][] map;
    private final int minNInFile = Configurations.minNInFile;
    private final int maxNInFile = Configurations.maxNInFile;
    List<Integer> numsToGenerate = new ArrayList<>();
    List<Double> discreteProbabilities = new ArrayList<>(); // { 0.1, 0.25, 0.3, 0.25, 0.1 };

    public Generator() {
        for (int i = 0; i < Configurations.maxNInFile; i++) {
            numsToGenerate.add(i);
            discreteProbabilities.add(0.1);
        }
        generateMap();

    }

    public void generateMap() {
        currentForest = 0;
        currentRiver = 0;
        riverCreated = false;
        forestCreated = false;
        int range = maxNInFile - minNInFile + 1;
        nCol = generateRandomNumber((Configurations.maxCols - Configurations.minCols + 1), -Configurations.minCols);
        nRow = generateRandomNumber((Configurations.maxRows - Configurations.minRows + 1), -Configurations.minRows);
        map = new int[nRow][nCol];

        createBorderAround();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                extractNumber(i, j, range);
            }
        }
        fillEmptySpaces();

        BFSVisit bfs = new BFSVisit();
        if (!bfs.breadthFirstSearch(1,1, map)) {
            generateMap();
        } else {
            writeOnFile();
        }

    }

    private void fillEmptySpaces() {
        int count=0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == Configurations.idexOfEarthImage) {
                    int prob = generateRandomNumber(6);
                    if(prob > 0 ){
                        map[i][j] = Configurations.idexOfGrassImage;
                    }else {
                        map[i][j] = Configurations.idexOfSandImage;
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

    private void extractNumber(int x, int y, int range) {
        if (!riverCreated && x > 2 && y > 2 && x < map.length - 3 && y < map[0].length - 3) {
            Pair pair = generateAndValidate();
            createRiver(pair.x, pair.y);
            currentRiver++;
            if(currentRiver == Configurations.nLaghi){
                riverCreated = true;
            }
        }

        if (!forestCreated && x > 2 && y > 2 && x < map.length - 3 && y < map[0].length - 3) {
            Pair pair = generateAndValidate();
            createForest(pair.x, pair.y);
            currentForest++;
            if(currentForest == Configurations.nForeste){
                forestCreated = true;
            }
            forestCreated = true;
        }
    }

    

    private void createForest(int x, int y) {
        int length = (int) Math.floor(Math.random() * (nRow / 2) + 1);
        int width = (int) Math.floor(Math.random() * (nCol / 2) + 1);

        for (int i = x; i < x + length; i++) {
            for (int j = y; j < y + width; j++) {
                createBallOfIndex(Configurations.idexOfTreeImage, i, j);
            }
        }
        createGrassAroundForest();
    }

    // ciao pianini <3
    private void createGrassAroundForest() {
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

    private boolean checkIfCoordinatesAreValid(int x, int y) {
        if (x <= 1 || y <= 1 || x > map.length - 2 || y > map[0].length - 2
                || map[x][y] != Configurations.idexOfEarthImage) {
            return false;
        }

        return true;
    }

    private Pair generateAndValidate() {
        Pair pair = new Pair();
        do {
            pair.x = generateRandomNumber(nRow - 2);
            pair.y = generateRandomNumber(nCol - 2);
        } while (!checkIfCoordinatesAreValid(pair.x, pair.y));
        return pair;
    }

    private int generateRandomNumber(int range, int toSubtract) {
        int randomNum = (int) Math.floor(Math.random() * range - toSubtract);

        return randomNum;
    }

    private int generateRandomNumber(int range) {
        int randomNum = (int) Math.floor(Math.random() * range);
        return randomNum;
    }

    private void createRiver(int x, int y) {
        int length = (int) Math.floor(Math.random() * (nRow / 2) + 1);
        int width = (int) Math.floor(Math.random() * (nCol / 2) + 1);

        for (int i = x; i < x + length; i++) {
            for (int j = y; j < y + width; j++) {
                createBallOfIndex(Configurations.idexOfWaterImage, i, j);
            }
        }

        createSandAroundRiver();

    }

    private void createSandAroundRiver() {
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

    private void createBallOfIndex(int index, int x, int y) {
        if (!checkIfCoordinatesAreValid(x, y))
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

    private void createSpawnZone(int x, int y) {
        map[x][y] = Configurations.idexOfGrassImage;
        map[x][y - 1] = Configurations.idexOfGrassImage;
        map[x][y + 1] = Configurations.idexOfGrassImage;

        map[x - 1][y - 1] = Configurations.idexOfGrassImage;
        map[x - 1][y + 1] = Configurations.idexOfGrassImage;

        map[x - 1][y] = Configurations.idexOfGrassImage;
        map[x][y + 2] = Configurations.idexOfGrassImage;
        map[x][y - 2] = Configurations.idexOfGrassImage;
        map[x - 2][y] = Configurations.idexOfGrassImage;

    }

    private void createBorderAround() {
        for (int i = 0; i < map.length; i++) {
            map[i][0] = Configurations.idexOfWallImage;
            map[i][map[0].length - 1] = Configurations.idexOfWallImage;
        }
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = Configurations.idexOfWallImage;
            map[map.length - 1][i] = Configurations.idexOfWallImage;
        }
    }

    private void writeOnFile() {
        try {
            // Creates a FileWriter
            FileWriter file = new FileWriter(Configurations.mapFileName);

            // Creates a BufferedWriter
            BufferedWriter buffer = new BufferedWriter(file);
            String toPrint = "";
            for (int[] row : map) {
                for (int cella : row) {
                    toPrint += Integer.toString(cella) + " ";
                }
                buffer.write(toPrint);
                toPrint = "";
                buffer.newLine();
            }

            buffer.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
