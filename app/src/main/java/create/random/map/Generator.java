package create.random.map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public int nCol = 35;
    public int nRow = 35;
    private boolean riverCreated = false;
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
        riverCreated = false;
        int range = maxNInFile - minNInFile + 1;
        int max = 35;
        int min = 10;
        nCol = (int) Math.floor(Math.random() * (max - min + 1) + min);
        nRow = (int) Math.floor(Math.random() * (max - min + 1) + min);
        map = new int[nRow][nCol];


        

        createBorderAround();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                extractNumber(i, j, range);
            }
        }
        createSpawnZone(nRow-2, nCol/2);

        BFSVisit bfs = new BFSVisit();
        if (!bfs.breadthFirstSearch(nRow-2, nCol/2, map)) {
            //generateMap();
        } else {
            writeOnFile();
        }

    }

    private void extractNumber(int x, int y, int range) {
        if(!riverCreated&&x>2&&y>2&&x<map.length-3&&y<map[0].length-3){
            createRiver(x,y);
            riverCreated = true;
        }
        
    }

    private void createRiver(int x, int y){
        createBallOfIndex(Configurations.idexOfWaterImage, x, y);
        
    }

    private void createBallOfIndex(int index,int x, int y){
        map[x][y] = index;
        map[x][y-1] = index;
        map[x][y + 1] = index;
        map[x-1][y] = index;
        map[x+1][y] = index;
    }

    private void createSpawnZone(int x, int y) {
        map[x][y] = Configurations.idexOfGrassImage;
        map[x][y-1] = Configurations.idexOfGrassImage;
        map[x][y + 1] = Configurations.idexOfGrassImage;

        map[x-1][y-1] = Configurations.idexOfGrassImage;
        map[x-1][y + 1] = Configurations.idexOfGrassImage;

        map[x-1][y] = Configurations.idexOfGrassImage;
        map[x][y+2] = Configurations.idexOfGrassImage;
        map[x][y-2] = Configurations.idexOfGrassImage;
        map[x-2][y] = Configurations.idexOfGrassImage;
        

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
