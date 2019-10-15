package leet.problems.leet200;

public class Leet200NumberOfIslands {
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int num = 0;
        int nrow = grid.length;
        int ncol = grid[0].length;
        for (int r = 0; r < nrow; r++) {
            for (int c = 0; c < ncol; c++) {
                if (grid[r][c] == '1') {
                    flood(r, c, grid, nrow, ncol);
                    num++;
                }
            }
        }
        return num;
    }

    private static void flood(int r, int c, char[][] grid, int nrow, int ncol) {
        if (r < 0 || c < 0 || r > nrow - 1 || c > ncol - 1 || grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        flood(r - 1, c, grid, nrow, ncol);
        flood(r + 1, c, grid, nrow, ncol);
        flood(r, c - 1, grid, nrow, ncol);
        flood(r, c + 1, grid, nrow, ncol);
    }
}
