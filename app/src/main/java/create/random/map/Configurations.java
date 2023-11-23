package create.random.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Configurations {
    public static final int minNInFile = 0;
    public static final int maxNInFile = 5;
    public static final int idexOfEarthImage = 0;
    public static final int idexOfGrassImage = 1;
    public static final int idexOfTreeImage = 3;
    public static final int idexOfWaterImage = 4;
    public static final int idexOfWallImage = 5;
    public static final String mapFileName = "map.txt";
    public static final int pixelForImage = 25;
    public static final List<Integer> notCrossable = new ArrayList<>(Arrays.asList(idexOfWaterImage, idexOfTreeImage, idexOfWallImage));
}
