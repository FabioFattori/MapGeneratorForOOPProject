package create.random.map;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFSVisit {

    public boolean breadthFirstSearch(int startNodeX, int startNodeY, int[][] map) {
        // Creare una coda per i nodi da visitare
        Queue<int[]> queue = new LinkedList<>();

        // Creare una matrice per memorizzare i nodi visitati
        boolean[][] visited = new boolean[map.length][map[0].length];

        // Aggiungere il nodo di partenza alla coda e contrassegnarlo come visitato
        queue.add(new int[] { startNodeX, startNodeY });
        visited[startNodeX][startNodeY] = true;

        while (!queue.isEmpty()) {
            // Rimuovere il nodo in cima alla coda
            int[] node = queue.poll();

            // Stampa il nodo visitato
            System.out.println("Visited node: " + Arrays.toString(node));

            int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

            // Per ogni nodo adiacente al nodo rimosso
            for (int[] direction : directions) {
                int newX = node[0] + direction[0];
                int newY = node[1] + direction[1];
                if (newX >= 1 && newX < map.length && newY >= 1 && newY < map[0].length && !visited[newX][newY]
                        && !Configurations.notCrossable.contains(map[newX][newY])) {
                    // Se non è stato visitato, aggiungerlo alla coda e contrassegnarlo come
                    // visitato
                    queue.add(new int[] { newX, newY });
                    visited[newX][newY] = true;
                }
            }
        }

        if (visited[map.length - 2][map[0].length - 2] && visited[1][map[0].length - 2] && visited[map.length - 2][1]
                && visited[1][1]) {
            return true;
        } else {
            return false;
        }
    }

    public boolean BFSForForest(int startNodeX, int startNodeY, int[][] map) {
        // Creare una coda per i nodi da visitare
        Queue<int[]> queue = new LinkedList<>();

        // Creare una matrice per memorizzare i nodi visitati
        boolean[][] visited = new boolean[map.length][map[0].length];

        // Aggiungere il nodo di partenza alla coda e contrassegnarlo come visitato
        queue.add(new int[] { startNodeX, startNodeY });
        visited[startNodeX][startNodeY] = true;

        while (!queue.isEmpty()) {
            // Rimuovere il nodo in cima alla coda
            int[] node = queue.poll();

            // Stampa il nodo visitato
            System.out.println("Visited node: " + Arrays.toString(node));

            int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

            // Per ogni nodo adiacente al nodo rimosso
            for (int[] direction : directions) {
                int newX = node[0] + direction[0];
                int newY = node[1] + direction[1];
                if (newX >= 0 && newX < map.length && newY >= 0 && newY < map[0].length && !visited[newX][newY]
                        && map[newX][newY] == Configurations.idexOfTreeImage) {
                    // Se non è stato visitato, aggiungerlo alla coda e contrassegnarlo come
                    // visitato
                    queue.add(new int[] { newX, newY });
                    visited[newX][newY] = true;
                    map[newX][newY] = Configurations.idexOfWallImage;
                }
            }
        }

        if (visited[map.length - 1][map[0].length - 1] && visited[0][map[0].length - 1] && visited[map.length - 1][0]
                && visited[0][0]) {
            return true;
        } else {
            return false;
        }
    }
}
