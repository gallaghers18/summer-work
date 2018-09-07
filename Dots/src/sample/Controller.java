package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Controller {

    @FXML private View view;
    ArrayList<Dot> dotList;
    Brain brain;

    int UPDATES_PER_SECOND = 100;
    double MOVE_DISTANCE = 5.0;

    public Controller() {
    }

    public void initialize() {
        brain = new Brain(MOVE_DISTANCE);
        dotList = brain.getDotList();
        view.drawDotList(dotList);
        setUpSimulationTimer();

    }

    public void updateSimulation() {
        brain.moveDotsOneStep();
        dotList = brain.getDotList();
        view.drawDotList(dotList);
    }

    /**
     * Starts timer for simulation, running update on
     * a timer.
     * */
    private void setUpSimulationTimer() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        updateSimulation();
                    }
                });
            }
        };

        long frameTimeInMilliseconds = (long)(1000.0 / UPDATES_PER_SECOND);
        Timer timer = new java.util.Timer();
        timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }



}
