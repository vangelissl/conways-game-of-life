import java.awt.Dimension;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Life {
	private int[][] _grid;
	private Dimension _size;
	private int _generation;

	public Life(Dimension gridSize) {
		_grid = new int[gridSize.height][gridSize.width];
		_size = gridSize;
	}

	public void setCell(int row, int col) {
		_grid[row][col] = 1;
	}

	public void removeCell(int row, int col) {
		_grid[row][col] = 0;
	}

	public int[][] nextGen(){
		int[][] nextGenGrid = new int[_size.height][_size.width];

		for (int i = 0; i < _size.height; i++) {
			for (int j = 0; j < _size.width; j++) {
				updateCell(nextGenGrid, i, j);
			}
		}
		
		return nextGenGrid;
	}

	public void update() {
		_grid = nextGen();
		_generation++;
	}

	public int getGeneration() {
		return _generation;
	}

	public int getCellCount() {
		IntStream flatStream = Arrays.stream(_grid).flatMapToInt(Arrays::stream);
		return flatStream.sum();
	}

	private void updateCell(int[][] grid, int row, int col) {
		int neighborsCount = getNeighborsCount(row, col);

		if (neighborsCount < 2 || neighborsCount > 3) {
			grid[row][col] = 0;
		}
		else if (neighborsCount == 3) {
			grid[row][col] = 1;
		}
	}
	
	private int getNeighborsCount(int row, int col) {
		int neighborsCount = 0;

		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; i++) {
				if (isSafe(i, j)) {
					neighborsCount += _grid[i][j];
				}
			}
		}

		return neighborsCount;
	}

	private boolean isSafe(int row, int col) {
		if(row < 0 || row > _size.height)
			return false;
		else if(col < 0 || col > _size.width)
			return false;

		return true;
	}
}