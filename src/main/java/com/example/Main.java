package com.example;

import java.awt.Dimension;

import GUI.Grid;
import Game.GameOfLife;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private static final int ROWS = 30;
    private static final int COLS = 30;
    private static final int CELL_SIZE = 20;
    private static final int STEP_DELAY_MS = 100;

    private GameOfLife game;
    private Grid grid;
    private Timeline timeline;

    @Override
    public void start(Stage stage) {

        // Initialize model
        game = new GameOfLife(new Dimension(COLS, ROWS));

        // Initialize UI grid
        grid = new Grid(game, new Dimension(COLS, ROWS), CELL_SIZE);
        grid.update(game.getGridView()); // initial render

        // Initialize Timeline
        timeline = new Timeline(new KeyFrame(Duration.millis(STEP_DELAY_MS), e -> step()));
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Buttons
        Button startBtn = new Button("Start");
        Button stopBtn = new Button("Stop");
        Button resetBtn = new Button("Reset");

        startBtn.setOnAction(e -> timeline.play());
        stopBtn.setOnAction(e -> timeline.pause());
        resetBtn.setOnAction(e -> {
            game.reset();
            grid.update(game.getGridView());
        });

        HBox controls = new HBox(10, startBtn, stopBtn, resetBtn);

        BorderPane root = new BorderPane();
        root.setCenter(grid);
        root.setBottom(controls);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Game of Life");
        stage.show();
    }

    private void step() {
        game.update(); // compute next generation
        grid.update(game.getGridView()); // refresh UI
    }

    public static void main(String[] args) {
        launch(args);
    }
}
