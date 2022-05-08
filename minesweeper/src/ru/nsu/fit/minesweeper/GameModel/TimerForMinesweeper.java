package ru.nsu.fit.minesweeper.GameModel;


import java.util.ArrayList;

public class TimerForMinesweeper implements GameTimer {
    private long timeLeftInSeconds;
    private boolean isWorking;
    private final ArrayList<TimerObserver> timerObservers = new ArrayList<>();

    @Override
    public void start() {
        timeLeftInSeconds = 0;
        Thread forTimer = new Thread() {
            @Override
            public void run() {
                work();
            }
        };
        forTimer.start();
    }

    @Override
    public void end(){
        isWorking = false;
    }

    @Override
    public long getTimeLeftInSeconds() {
        return timeLeftInSeconds;
    }

    @Override
    public void update(GameStates state) {
        switch (state) {
            case FAIL,VICTORY-> {end();}
            case IN_PROGRESS -> {
                if (!isWorking){
                    start();
                }

            }
            default -> {}
        }
    }


    @Override
    public void notifyAllObservers() {
        for (var observer : timerObservers) {
            observer.update(timeLeftInSeconds);
        }
    }

    @Override
    public void removeObserver(TimerObserver observer) {
        timerObservers.remove(observer);
    }

    @Override
    public void registerObserver(TimerObserver observer) {
        timerObservers.add(observer);
    }


    private void work() {
        long startTime = System.currentTimeMillis() / 1000;
        long currentTime;
        isWorking = true;
        while(isWorking) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            currentTime = System.currentTimeMillis() / 1000;
            timeLeftInSeconds = currentTime - startTime;
            notifyAllObservers();
        }
    }
}
