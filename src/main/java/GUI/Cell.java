package GUI;

import Game.GameOfLife;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
	private final int row;
	private final int col;
	private final GameOfLife game;

	public Cell(int size, int row, int col, GameOfLife game) {
		this.row = row;
		this.col = col;
		this.game = game;

		setWidth(size);
		setHeight(size);
		setStroke(Color.GRAY);

		setOnMouseClicked(e -> toggle());
	}

	private void toggle() {
		int value = game.getGridView()[row][col];

		if (value == 1) {
			game.removeCell(row, col);
		} else {
			game.addCell(row, col);
		}

		// Force immediate update
		int newValue = game.getGridView()[row][col];
		render(newValue);
	}

	public void render(int value) {
		setFill(value == 1 ? Color.BLACK : Color.WHITE);
	}
}
