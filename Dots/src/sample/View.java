package sample;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;

import java.util.ArrayList;


public class View extends Group {

    private int DOT_RADIUS = 5;
    private Canvas canvas;
    private GraphicsContext gc;

    public View() {
        canvas = new Canvas(800, 800);
        gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);

        // ROUGH ADDING OF AN OBSTACLE RECTANGLE (HARDCODED IN BRAIN TOO)
        gc.fillRect(300, 200, 200, 50);

    }

    private void drawDot(Dot dot) {
        gc.fillOval(dot.getX(), dot.getY(), DOT_RADIUS, DOT_RADIUS);
    }

    public void drawDotList(ArrayList<Dot> dotList) {
        gc.clearRect(0,0,800,800);
        gc.fillRect(300, 200, 200, 50);
        for (Dot dot : dotList) {
            drawDot(dot);
        }
    }
}
