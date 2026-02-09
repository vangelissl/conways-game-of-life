package GUI;

import java.awt.Dimension;

import Game.GameOfLife;
import javafx.scene.layout.GridPane;

public class Grid extends GridPane {
	private Cell[][] cells;
	private Dimension size;
	private GameOfLife game;

	public Grid(GameOfLife game, Dimension size, int cellSize) {
		setHgap(0);
		setVgap(0);

		this.game = game;
		this.size = size;
		this.cells = new Cell[size.height][size.width];

		populate(cellSize);
	}
	
	private void populate(int cellSize) {
		for (int i = 0; i < size.height; i++) {
			for (int j = 0; j < size.width; j++) {
				Cell cell = new Cell(cellSize, i, j, game);
				cells[i][j] = cell;
				add(cell, j, i);
			}
		}
	}

	public void update(int[][] grid) {
		for (int row = 0; row < size.height; row++) {
			for (int col = 0; col < size.width; col++) {
				cells[row][col].render(grid[row][col]);
			}
		}
	}

	public void clear() {
		for (int row = 0; row < size.height; row++) {
			for (int col = 0; col < size.width; col++) {
				cells[row][col].render(0);
			}
		}
	}
}
