package su.lubieerror.jteatime.logic;

import su.lubieerror.jteatime.ui.mvController;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author lubieerror
 *         <p>
 *         Forked from my old project:
 *         > https://github.com/Lubieerror/RPGTool-JWB/blob/master/src/su/lubieerror/rpgtool/Tools/StopWatch.java
 */

public class CountDown {
    private int min;
    private int sec;
    private boolean running, firstTime;
    private Timer myTimer;
    private TimerTask secTask;
    private mvController srcXFML;

    public enum WaitType {
        COLD_FULL(270),
        FOR_WATER(330),
        FOR_TEA(210),
        FOR_ALL(COLD_FULL.toInt() + FOR_WATER.toInt() + FOR_TEA.toInt());

        private final int time;

        WaitType(int time) {
            this.time = time;
        }

        public int toInt() {
            return time;
        }
    }

    // Inicialization:

    public CountDown(mvController source) {
        srcXFML = source;
        firstTime = true;
        reset();

        myTimer = new Timer();
        secTask = new TimerTask() {
            public void run() {
                if (running) {
                    sec--;
                    if (sec < 0)
                        updateTimer();
                    updateCountDown();
                }
            }
        };
    }

    public void start(WaitType timeSec) {
        crankTimer(timeSec.toInt());
        running = true;
        myTimer.scheduleAtFixedRate(secTask, 1000, 1000);
        firstTime = false;
    }

    // Manage time:

    private void crankTimer(int timeSec) {
        sec = timeSec;
        while (sec >= 60) {
            min++;
            sec -= 60;
        }
        javafx.application.Platform.runLater(() -> srcXFML.setClock(getTime()));
    }

    private void updateTimer() {
        if (sec < 0) {
            if (min > 0) {
                min--;
                sec = 59;
            } else {
                reset();
            }
        }
    }

    // Getting time:

    private void updateCountDown() {
        if (running)
            javafx.application.Platform.runLater(() -> srcXFML.setClock(getTime()));
    }

    private String getTime() {
        String time = "";
        if (min < 10)
            time += "0";
        time += min + ":";
        if (sec < 10)
            time += "0";
        time += sec;
        return time;
    }

    // Reset:

    public void reset() {
        running = false;
        if (!firstTime) {
            myTimer.cancel();
            myTimer.purge();
            firstTime = false;
            srcXFML.stop();
        }
        javafx.application.Platform.runLater(() -> srcXFML.setClock("13:37"));
        min = 0;
        sec = 0;
    }
}