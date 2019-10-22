package leet.problems.leet417;

import java.util.ArrayList;
import java.util.List;

public class Leet417PacificAtlanticWaterFlow {
    private static final byte BEING_EXPLORED = 1;
    private static final byte PACIFIC = 2;
    private static final byte ATLANTIC = 4;

    public static List<List<Integer>> pacificAtlantic(int[][] matrix) {
        // sanity-checking
        if (matrix == null) {
            return null;
        }
        if (matrix.length <= 1 || matrix[0].length <= 1) {
            List<List<Integer>> ret = new ArrayList<>();
            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix[0].length; j++) {
                    List<Integer> k = new ArrayList<>();
                    k.add(i);
                    k.add(j);
                    ret.add(k);
                }
            return ret;
        }

        // Initialize the state-tracking pieces.
        int nrow = matrix.length;
        int ncol = matrix[0].length;
        byte[][] visited = new byte[nrow][ncol];
        // fake queue!
        int[][] queue = new int[ncol * nrow][2];
        int cursor = 0;

        // Flood from the Atlantic side. Seed the queue from the two Atlantic edges.
        for (int c = 0; c < ncol - 1; c++) {
            visited[nrow - 1][c] |= BEING_EXPLORED;
            cursor = addToFakeQueue(queue, nrow - 1, c, cursor);
        }
        for (int r = 0; r < nrow; r++) {
            cursor = addToFakeQueue(queue, r, ncol - 1, cursor);
            visited[r][ncol - 1] |= BEING_EXPLORED;
        }

        // BFS from the Atlantic side.
        while (cursor > 0) {
            int r = queue[cursor - 1][0];
            int c = queue[cursor - 1][1];
            cursor--;
            visited[r][c] |= ATLANTIC;
            visited[r][c] >>= 1;
            visited[r][c] <<= 1;
            cursor = possiblyAddNeighborsToQueue(
                queue, matrix, visited, r, c, nrow, ncol, cursor, ATLANTIC);
        }

        // We have drained the fake Atlantic queue. We can now flood from the Pacific side.
        // Initialize the collection of the points that drain into both oceans.
        // Seed the queue with the Pacific edges.
        List<List<Integer>> cells = new ArrayList<>();
        for (int c = 0; c < ncol; c++) {
            cursor = addToFakeQueue(queue, 0, c, cursor);
            visited[0][c] |= BEING_EXPLORED;
        }
        for (int r = 1; r < nrow; r++) {
            cursor = addToFakeQueue(queue, r, 0, cursor);
            visited[r][0] |= BEING_EXPLORED;
        }

        // BFS from the Pacific side.
        while (cursor > 0) {
            int r = queue[cursor - 1][0];
            int c = queue[cursor - 1][1];
            cursor--;
            visited[r][c] |= PACIFIC;
            visited[r][c] >>= 1;
            visited[r][c] <<= 1;
            // Collect cells that drian into both oceans as we find them.
            if ((visited[r][c] & ATLANTIC) != 0) {
                List<Integer> cell = new ArrayList<>();
                cell.add(r);
                cell.add(c);
                cells.add(cell);
            }
            cursor = possiblyAddNeighborsToQueue(
                queue, matrix, visited, r, c, nrow, ncol, cursor, PACIFIC);
        }

        return cells;
    }

    private static int addToFakeQueue(int[][] queue, int r, int c, int cursor) {
        queue[cursor][0] = r;
        queue[cursor][1] = c;
        return cursor + 1;
    }

    /* For each of the N/S/E/W neighbors, check the following:
         - Row/column within range?
         - Have we already added this tuple to the queue?
         - Have we already visited this tuple?
       If the tuple passes the checks, mark it as being in the queue and add it to the queue.
     */
    private static int possiblyAddNeighborsToQueue(int[][] queue,
                                                   int[][] matrix,
                                                   byte[][] visited,
                                                   int r,
                                                   int c,
                                                   int nrow,
                                                   int ncol,
                                                   int cursor,
                                                   byte ocean) {
        int heightHere = matrix[r][c];
        if (c + 1 < ncol
                && (visited[r][c + 1] & ocean) == 0
                && (visited[r][c + 1] & BEING_EXPLORED) == 0
                && heightHere <= matrix[r][c + 1]) {
            visited[r][c + 1] |= BEING_EXPLORED;
            cursor = addToFakeQueue(queue, r, c + 1, cursor);
        }
        if (r + 1 < nrow
                && (visited[r + 1][c] & ocean) == 0
                && (visited[r + 1][c] & BEING_EXPLORED) == 0
                && heightHere <= matrix[r + 1][c]) {
            visited[r + 1][c] |= BEING_EXPLORED;
            cursor = addToFakeQueue(queue, r + 1, c, cursor);
        }
        if (c - 1 >= 0
                && (visited[r][c - 1] & ocean) == 0
                && (visited[r][c - 1] & BEING_EXPLORED) == 0
                && heightHere <= matrix[r][c - 1]) {
            visited[r][c - 1] |= BEING_EXPLORED;
            cursor = addToFakeQueue(queue, r, c - 1, cursor);
        }
        if (r - 1 >= 0
                && (visited[r - 1][c] & ocean) == 0
                && (visited[r - 1][c] & BEING_EXPLORED) == 0
                && heightHere <= matrix[r - 1][c]) {
            visited[r - 1][c] |= BEING_EXPLORED;
            cursor = addToFakeQueue(queue, r - 1, c, cursor);
        }
        return cursor;
    }
}
