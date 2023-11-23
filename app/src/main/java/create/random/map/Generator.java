package create.random.map;

import java.io.BufferedWriter;
import java.io.FileWriter;

import create.random.map.Factory.BackGroundConfigurator;
import create.random.map.Factory.Forest;
import create.random.map.Factory.River;
import create.random.map.Utils.functions;

public class Generator {
    private int firstForestX = 0;
    private int firstForestY = 0;
    private int lastForestX = 0;
    private int lastForestY = 0;
    public int currentForest = 0;
    public int currentRiver = 0;
    public int nCol = Configurations.maxCols;
    public int nRow = Configurations.maxRows;
    private boolean riverCreated = false;
    private boolean forestCreated = false;
    public int[][] map;
    private final int minNInFile = Configurations.minNInFile;
    private final int maxNInFile = Configurations.maxNInFile;

    public Generator() {
        
        generateMap();

    }

    public void generateMap() {
        
        currentForest = 0;
        currentRiver = 0;
        riverCreated = false;
        forestCreated = false;
        int range = maxNInFile - minNInFile + 1;
        nCol = functions.generateRandomNumber((Configurations.maxCols - Configurations.minCols + 1), -Configurations.minCols);
        nRow = functions.generateRandomNumber((Configurations.maxRows - Configurations.minRows + 1), -Configurations.minRows);
        map = new int[nRow][nCol];

        BackGroundConfigurator.createBorderAround(map);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                extractNumber(i, j, range);
            }
        }
        BackGroundConfigurator.fillEmptySpaces(map);

        BFSVisit bfs = new BFSVisit();
        if (!bfs.breadthFirstSearch(1,1, map)) {
            generateMap();
        } else {
            writeOnFile();
        }

    }

    

    private void extractNumber(int x, int y, int range) {
        if (!riverCreated && x > 2 && y > 2 && x < map.length - 3 && y < map[0].length - 3) {
            Pair pair = generateAndValidate();
            new River().create(pair, map, nRow,nCol);
            currentRiver++;
            if(currentRiver == Configurations.nLaghi){
                riverCreated = true;
            }
        }

        if (!forestCreated && x > 2 && y > 2 && x < map.length - 3 && y < map[0].length - 3) {
            Pair pair = generateAndValidate();
            new Forest().create(pair, map,nRow,nCol);
            currentForest++;
            if(currentForest == Configurations.nForeste){
                forestCreated = true;
            }
            forestCreated = true;
        }
    }


    private Pair generateAndValidate() {
        Pair pair = new Pair();
        do {
            pair.x = functions.generateRandomNumber(nRow - 2);
            pair.y = functions.generateRandomNumber(nCol - 2);
        } while (!functions.checkIfCoordinatesAreValid(pair, map));
        return pair;
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
