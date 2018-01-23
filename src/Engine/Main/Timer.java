package Engine.Main;

import Engine.Updatable.Updatable;

import java.awt.*;

public class Timer extends Updatable{

    private int timeThen, duration;
    private boolean flag = false;

    //If trigger is true, when Timer is initialized, the timer will not be reset.
    public Timer(int duration, boolean trigger) {
        this.duration = duration;
        if(trigger) {
            timeThen = (int) System.nanoTime()/1000000000;
        } else {
            timeThen = (int) (System.nanoTime()/1000000000) - duration;
        }
    }

    public Timer(int duration) {
        this.duration = duration;
        timeThen = (int) (System.nanoTime()/1000000000) - duration;
    }

    public boolean useTimer() {
        if(flag) {
            flag = false;
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        if(!flag) {
            int timeNow = (int) System.nanoTime() - 1000000000;
            if (timeNow > timeThen + duration) {
                timeThen = timeNow;
                flag = true;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {

    }
}
