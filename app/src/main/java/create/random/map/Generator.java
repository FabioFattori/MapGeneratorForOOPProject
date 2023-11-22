package create.random.map;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Generator {

    public int nCol = 35;
    public int nRow = 35;
    public int[][] map;
    private final int minNInFile = Configurations.minNInFile;
    private final int maxNInFile = Configurations.maxNInFile;

    public Generator() {
        generateMap();
    }

    public void generateMap() {
        int range = maxNInFile - minNInFile + 1;
        int max = 35;
        int min = 10;
        nCol = (int) Math.floor(Math.random() * (max - min + 1) + min);
        nRow = (int) Math.floor(Math.random() * (max - min + 1) + min);
        map = new int[nRow][nCol];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = (int) Math.floor(Math.random() * range);
            }
        }

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
