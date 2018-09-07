package sample;

import java.util.ArrayList;
import java.util.Random;

public class Brain {
    ArrayList<Dot> dotList;
    Random rand;
    Double moveDistance;

    public Brain(double moveDistance) {
        this.moveDistance = moveDistance;
        rand = new Random();
        dotList = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            dotList.add(new Dot(400,0));
        }
    }

    public ArrayList<Dot> getDotList() {
        return this.dotList;
    }

    public void moveDotsOneStep() {
        for (int i = 0; i < dotList.size(); i++) {
            Double angle = rand.nextDouble() * 2 * Math.PI;
            Dot dot = dotList.get(i);
            double newX = dot.getX() + moveDistance * Math.cos(angle);
            double newY = dot.getY() + moveDistance * Math.sin(angle);

            if (!dot.isStuck()) {
                if (newX < 0) {
                    dot.setY(dot.getY() + (dot.getX()) * Math.tan(angle));
                    dot.setX(0);
                    dot.hasGottenStuck();
                }
                else if (newX > 800) {
                    dot.setY(dot.getY() + (800 - dot.getX()) * Math.tan(angle));
                    dot.setX(796);
                    dot.hasGottenStuck();
                }
                else if (newY < 0) {
                    dot.setX(dot.getX() + (dot.getY()) / Math.tan(angle));
                    dot.setY(0);
                    dot.hasGottenStuck();
                }
                else if (newY > 800) {
                    dot.setX(dot.getX() + (800 - dot.getY()) / Math.tan(angle));
                    dot.setY(796);
                    dot.hasGottenStuck();
                }
                else if (newX > 300 && newX < 500 && newY > 200 && newY < 250) { //HARDCODED OBSTACLE ~~~~~~~~~~~ WORK ON MODULARIZING LATER
                    dot.setX(newX);
                    dot.setY(newY);
                    dot.hasGottenStuck();
                }
                else {
                    dot.setX(newX);
                    dot.setY(newY);
                }
            }

        }



    }
}
