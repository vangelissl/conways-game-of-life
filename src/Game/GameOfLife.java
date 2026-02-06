package Game;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.stream.IntStream;

public class GameOfLife {
	private GameGrid _grid;
	private Dimension _gridSize;
	private int _generation;

	public GameOfLife(Dimension gridSize) {
		_gridSize = gridSize;

		reset();
	}

	public void reset() {
		_grid = new GameGrid(_gridSize);
		_generation = 1;
	}

	public void addCell(int row, int col) {
		_grid.setCell(row, col, 1);
	}
	
	public void removeCell(int row, int col) {
		_grid.setCell(row, col, 0);
	}

	public GameGrid nextGen() {
		GameGrid nextGenGrid = new GameGrid(_gridSize);

		for (int i = 0; i < _gridSize.height; i++) {
			for (int j = 0; j < _gridSize.width; j++) {
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

	public int[][] getGridView() {
		return _grid.getCopy();
	}

	public Dimension getGridSize() {
		return _gridSize;
	}

	public int getCellCount() {
		IntStream flatStream = Arrays.stream(_grid.getGrid()).flatMapToInt(Arrays::stream);
		return flatStream.sum();
	}

	private void updateCell(GameGrid grid, int row, int col) {
		int neighborsCount = getNeighborsCount(row, col);
		grid.setCell(row, col, _grid.getCell(row, col));

		if (neighborsCount < 2 || neighborsCount > 3) {
			grid.setCell(row, col, 0);
		}
		else if (neighborsCount == 3) {
			grid.setCell(row, col, 1);
		}
	}
	
	private int getNeighborsCount(int row, int col) {
		int neighborsCount = 0;

		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				if (isSafe(i, j) && !(i == row && j == col)) {
					neighborsCount += _grid.getCell(i, j);
				}
			}
		}

		return neighborsCount;
	}

	private boolean isSafe(int row, int col) {
		if (row < 0 || row == _gridSize.height)
			return false;
		else if (col < 0 || col == _gridSize.width)
			return false;

		return true;
	}
}