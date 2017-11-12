package com.it.jchess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * This class defines a player behaviour.
 */
public class Player implements Serializable {
    public static final long serialVersionUID = -7121562930867472582L;
    private String name;
    private int played;
    private int won;

    public Player(String name) {
        this.name = name;
        this.played = 0;
        this.won = 0;
    }

    public String getName() {
        return name;
    }

    public int getPlayed() {
        return played;
    }

    public int getWon() {
        return won;
    }

    public void wonGame() {
        won++;
    }

    public void playedGame() {
        played++;
    }

    public static ArrayList<Player> fetchPlayerDetails() {
        Player tempPlayer;
        ObjectInputStream input = null;
        ArrayList<Player> players = new ArrayList<>();
        try {
            File infile = new File(System.getProperty("user.dir") + File.separator + "UserData.dat");
            input = new ObjectInputStream(new FileInputStream(infile));
            try {
                while (true) {
                    tempPlayer = (Player) input.readObject();
                    players.add(tempPlayer);
                }
            } catch (EOFException e) {
                input.close();
            }
        } catch (FileNotFoundException e) {
            players.clear();
            return players;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                input.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Cannot read game files");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Game file is damaged. Creating new Game File");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return players;
    }

    public void savePlayerDetails() {
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        Player tempPlayer;
        File inputFile = null;
        File outputFile = null;
        try {
            inputFile = new File(System.getProperty("user.dir") + File.separator + "UserData.dat");
            outputFile = new File(System.getProperty("user.dir") + File.separator + "TempFile.dat");
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(null, "R/W Permission Denied! Cannot start game!");
            System.exit(0);
        }
        boolean playerExists;
        try {
            if (!outputFile.exists())
                outputFile.createNewFile();
            if (!inputFile.exists()) {
                output = new ObjectOutputStream(new java.io.FileOutputStream(outputFile, true));
                output.writeObject(this);
            } else {
                input = new ObjectInputStream(new FileInputStream(inputFile));
                output = new ObjectOutputStream(new FileOutputStream(outputFile));
                playerExists = false;
                try {
                    while (true) {
                        tempPlayer = (Player) input.readObject();
                        if (tempPlayer.getName().equals(getName())) {
                            output.writeObject(this);
                            playerExists = true;
                        } else
                            output.writeObject(tempPlayer);
                    }
                } catch (EOFException e) {
                    input.close();
                }
                if (!playerExists)
                    output.writeObject(this);
            }
            inputFile.delete();
            output.close();
            File newf = new File(System.getProperty("user.dir") + File.separator + "UserData.dat");
            if (!outputFile.renameTo(newf))
                System.out.println("Cannot rename file!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot read game files");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Game file is damaged. Creating new Game File");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
