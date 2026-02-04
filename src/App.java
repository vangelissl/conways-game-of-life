import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        GameOfLife game = new GameOfLife(new Dimension(5, 5));
        int[][] grid = {
                { 0, 1, 0, 0, 0},
                { 1, 1, 1, 0, 0},
                { 0, 1, 0, 1, 0},
                { 0, 0, 1, 0, 0},
                { 0, 0, 0, 0, 0},
        };
        game.setGrid(grid);

        boolean run = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (run) {
            System.out.println("=================GEN " + game.getGeneration() + "================");
            printGrid(game.getGrid());
            System.out.println("============================================================");
            System.out.print("Next(y)/Quit(n): ");
            char c = br.readLine().charAt(0);

            if (c == 'n') {
                run = false;
            }
            else {
                game.update();
            }
        }

    }
    
    public static void printGrid(int[][] grid) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.print('\n');
        }
    }
}
