package GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle {
	private boolean _alive;

	public Cell(int sideLength) {
		setWidth(sideLength);
		setHeight(sideLength);

		setFill(Color.BLACK);

		setStroke(Color.GRAY);
		setStrokeWidth(1);

		_alive = false;

		setOnMouseClicked(e -> {
			toggle();
		});
	}

	public boolean isAlive() {
		return _alive;
	}

	public void setAlive(boolean alive) {
		_alive = alive;
		updateView();
	}
	
	public void toggle() {
		_alive = !_alive;
		updateView();
	}

	private void updateView() {
		setFill(_alive ? Color.BLACK : Color.WHITE);
	}
}
