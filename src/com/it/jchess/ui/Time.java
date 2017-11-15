package com.it.jchess.ui;

import com.it.jchess.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class defines the Timer for the game.
 */
public class Time {

    private JLabel label;
    public Timer timer;
    int timeRemaining;

    public Time(JLabel label) {
        timer = new Timer(1000, new TimerListener());
        this.label = label;
        this.timeRemaining = Game.timeRemaining;
    }

    public void start() {
        timer.start();
    }

    public void reset() {
        timeRemaining = Game.timeRemaining;
    }

    class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int minutes, seconds;
            if (timeRemaining > 0) {
                minutes = timeRemaining / 60;
                seconds = timeRemaining % 60;
                label.setText(String.valueOf(minutes) + ":" + (seconds >= 10 ? String.valueOf(seconds) : "0" + String.valueOf(seconds)));
                timeRemaining--;
            } else {
                label.setText("Time's Up!");
                reset();
                start();
//                Game.gameBoard.switchTurns();
                Game.gameBoard.onGameEnd();
            }
        }
    }
}