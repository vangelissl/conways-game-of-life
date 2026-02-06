import java.awt.Dimension;
import java.util.Arrays;
import java.util.stream.IntStream;

public class GameGrid {
	public int[][] _grid;
	public Dimension _size;

	public GameGrid(Dimension gridSize) {
		_size = gridSize;
		_grid = new int[gridSize.height][gridSize.width];
	}

	public GameGrid(GameGrid grid) {
		Dimension size = grid.getSize();

		// copy the grid
		for (int i = 0; i < size.height; i++) {
			for (int j = 0; j < size.width; j++) {
				setCell(i, j, grid.getCell(i, j));
			}
		}
	}

	public void setCell(int row, int col, int val) {
		_grid[row][col] = val;
	}

	public int[][] getGrid() {
		return _grid;
	}

	public Dimension getSize() {
		return _size;
	}

	public int getCellCount() {
		IntStream flatStream = Arrays.stream(_grid).flatMapToInt(Arrays::stream);
		return flatStream.sum();
	}

	public int getCell(int row, int col) {
		return _grid[row][col];
	}
	
	public int[][] getCopy() {
		int[][] copy = new int[_size.height][_size.width];

		for (int i = 0; i < _size.height; i++) {
			for (int j = 0; j < _size.width; j++) {
				copy[i][j] = getCell(i, j);
			}
		}

		return copy;
	}
}
