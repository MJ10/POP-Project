package com.it.jchess;


import com.it.jchess.pieces.*;
import com.it.jchess.ui.Tile;
import com.it.jchess.ui.Time;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * @author Moksh Jain
 * <p>
 * This class contains the main working of the game.
 */
public class Game extends JFrame implements MouseListener {

    private static final int HEIGHT = 700, WIDTH = 1100;

    private static King wKing, bKing;
    private static Queen wQueen, bQueen;
    private static Rook bRook1, bRook2, wRook1, wRook2;
    private static Bishop bBishop1, bBishop2, wBishop1, wBishop2;
    private static Knight bKnight1, bKnight2, wKnight1, wKnight2;
    private static Pawn wPawns[], bPawns[];

    private Tile tile, previousTile;
    private int turn;

    private Tile boardState[][];
    private ArrayList<Tile> destinations = new ArrayList<>();

    private Player white, black;
    private Player tempPlayer;

    private JPanel board = new JPanel(new GridLayout(8, 8));
    private JPanel wDetails = new JPanel(new GridLayout(3, 3));
    private JPanel bDetails = new JPanel(new GridLayout(3, 3));
    private JPanel wComboPanel = new JPanel();
    private JPanel bComboPanel = new JPanel();
    private JPanel controlPanel, wPlayer, bPlayer, temp, displayTime, showPlayer, time;
    private JSplitPane split;
    private JLabel label, mov;
    private static JLabel CHNC;
    private Time timer;
    private JSlider timeSlider;
    private Button start, wSelect, bSelect, wNewPlayer, bNewPlayer;
    private JScrollPane wScroll, bScroll;

    public static Game gameBoard;

    private boolean selected = false, end = false;
    private Container content;

    private ArrayList<Player> wPlayers, bPlayers;
    private ArrayList<String> wNames = new ArrayList<>();
    private ArrayList<String> bNames = new ArrayList<>();
    private JComboBox<String> wCombo, bCombo;
    private String wName = null, bName = null, winner = null;
    static String move;
    private String[] wNamesList = {}, bNamesList = {};
    public static int timeRemaining = 60;

    private Game() {
        defineLayouts();

        //Time Slider Details
        setupTimer();

        //Fetching Details of all Players
        fetchPlayers();

        //Defining the Player Box in Control Panel
        setupPlayerBox();

        setupBoard();

        setupSidePanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setupSidePanel() {
        showPlayer = new JPanel(new FlowLayout());
        showPlayer.add(timeSlider);

        JLabel setTime = new JLabel("Set Timer(in mins):");
        start = new Button("Start");
        start.setBackground(Color.black);
        start.setForeground(Color.white);
        start.addActionListener(new StartListener());
        start.setPreferredSize(new Dimension(120, 40));
        setTime.setFont(new Font("Arial", Font.BOLD, 16));

        label = new JLabel("Time Starts now", JLabel.CENTER);
        label.setFont(new Font("SERIF", Font.BOLD, 30));
        displayTime = new JPanel(new FlowLayout());
        time = new JPanel(new GridLayout(3, 3));
        time.add(setTime);
        time.add(showPlayer);
        displayTime.add(start);
        time.add(displayTime);
        controlPanel.add(time);
        board.setMinimumSize(new Dimension(800, 700));

        controlPanel.setMinimumSize(new Dimension(285, 700));
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, temp, controlPanel);

        content.add(split);
    }

    private void fetchPlayers() {
        wPlayers = Player.fetchPlayerDetails();
        for (Player wPlayer1 : wPlayers) wNames.add(wPlayer1.getName());

        bPlayers = Player.fetchPlayerDetails();
        for (Player bPlayer1 : bPlayers) bNames.add(bPlayer1.getName());
        wNamesList = wNames.toArray(wNamesList);
        bNamesList = bNames.toArray(bNamesList);
    }

    private void setupTimer() {
        timeRemaining = 60;
        timeSlider.setMinimum(1);
        timeSlider.setMaximum(15);
        timeSlider.setValue(1);
        timeSlider.setMajorTickSpacing(2);
        timeSlider.setPaintLabels(true);
        timeSlider.setPaintTicks(true);
        timeSlider.addChangeListener(new TimeChangeListener());
    }

    private void defineLayouts() {
        timeSlider = new JSlider();
        move = "White";
        wName = null;
        bName = null;
        winner = null;
        board = new JPanel(new GridLayout(8, 8));
        wDetails = new JPanel(new GridLayout(3, 3));
        bDetails = new JPanel(new GridLayout(3, 3));
        bComboPanel = new JPanel();
        wComboPanel = new JPanel();
        wNames = new ArrayList<>();
        wNames = new ArrayList<>();
        board.setMinimumSize(new Dimension(800, 700));
        ImageIcon img = new ImageIcon(this.getClass().getResource("ui/images/icon.png"));
        this.setIconImage(img.getImage());
        board.setBorder(BorderFactory.createLoweredBevelBorder());
        content = getContentPane();
        setSize(WIDTH, HEIGHT);
        setTitle("JChess");
        content.setBackground(Color.black);
        controlPanel = new JPanel();
        content.setLayout(new BorderLayout());
        controlPanel.setLayout(new GridLayout(3, 3));
        controlPanel.setBorder(BorderFactory.createTitledBorder(null, "Statistics", TitledBorder.TOP, TitledBorder.CENTER, new Font("Ubuntu", Font.PLAIN, 20), Color.black));

    }

    private void setupPlayerBox() {
        wPlayer = new JPanel();
        wPlayer.setBorder(BorderFactory.createTitledBorder(null, "White Player", TitledBorder.TOP, TitledBorder.CENTER, new Font("Utopia", Font.BOLD, 18), Color.BLACK));
        wPlayer.setLayout(new BorderLayout());
        wPlayer.setBackground(new Color(255, 224, 130));

        bPlayer = new JPanel();
        bPlayer.setBackground(new Color(188, 170, 164));
        bPlayer.setBorder(BorderFactory.createTitledBorder(null, "Black Player", TitledBorder.TOP, TitledBorder.CENTER, new Font("Utopia", Font.BOLD, 18), Color.BLACK));
        bPlayer.setLayout(new BorderLayout());

        JPanel whiteStats = new JPanel(new GridLayout(3, 3));
        JPanel blackStats = new JPanel(new GridLayout(3, 3));

        wCombo = new JComboBox<>(wNamesList);
        bCombo = new JComboBox<>(wNamesList);

        wScroll = new JScrollPane(wCombo);
        bScroll = new JScrollPane(bCombo);

        wComboPanel.setLayout(new FlowLayout());
        wComboPanel.setBackground(new Color(255, 224, 130));
        bComboPanel.setLayout(new FlowLayout());
        bComboPanel.setBackground(new Color(188, 170, 164));

        wSelect = new Button("Select");
        bSelect = new Button("Select");
        wSelect.addActionListener(new SelectListener(0));
        bSelect.addActionListener(new SelectListener(1));

        wNewPlayer = new Button("New Player");
        bNewPlayer = new Button("New Player");
        wNewPlayer.addActionListener(new Handler(0));
        bNewPlayer.addActionListener(new Handler(1));

        wComboPanel.add(wScroll);
        wComboPanel.add(wSelect);
        wComboPanel.add(wNewPlayer);
        bComboPanel.add(bScroll);
        bComboPanel.add(bSelect);
        bComboPanel.add(bNewPlayer);
        wPlayer.add(wComboPanel, BorderLayout.NORTH);
        bPlayer.add(bComboPanel, BorderLayout.NORTH);
        whiteStats.setBackground(new Color(255, 224, 130));
        whiteStats.add(new JLabel("Name   :"));
        whiteStats.add(new JLabel("Played :"));
        whiteStats.add(new JLabel("Won    :"));
        blackStats.setBackground(new Color(188, 170, 164));
        blackStats.add(new JLabel("Name   :"));
        blackStats.add(new JLabel("Played :"));
        blackStats.add(new JLabel("Won    :"));

        wPlayer.add(whiteStats, BorderLayout.WEST);
        bPlayer.add(blackStats, BorderLayout.WEST);

        controlPanel.add(wPlayer);
        controlPanel.add(bPlayer);
    }

    private void setupBoard() {
        Piece P;
        boardState = new Tile[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                P = null;
                if (i == 0 && j == 0)
                    P = bRook1;
                else if (i == 0 && j == 7)
                    P = bRook2;
                else if (i == 7 && j == 0)
                    P = wRook1;
                else if (i == 7 && j == 7)
                    P = wRook2;
                else if (i == 0 && j == 1)
                    P = bKnight1;
                else if (i == 0 && j == 6)
                    P = bKnight2;
                else if (i == 7 && j == 1)
                    P = wKnight1;
                else if (i == 7 && j == 6)
                    P = wKnight2;
                else if (i == 0 && j == 2)
                    P = bBishop1;
                else if (i == 0 && j == 5)
                    P = bBishop2;
                else if (i == 7 && j == 2)
                    P = wBishop1;
                else if (i == 7 && j == 5)
                    P = wBishop2;
                else if (i == 0 && j == 4)
                    P = bKing;
                else if (i == 0 && j == 3)
                    P = bQueen;
                else if (i == 7 && j == 4)
                    P = wKing;
                else if (i == 7 && j == 3)
                    P = wQueen;
                else if (i == 1)
                    P = bPawns[j];
                else if (i == 6)
                    P = wPawns[j];
                tile = new Tile(j, i, P);
                tile.addMouseListener(this);
                board.add(tile);
                boardState[i][j] = tile;
            }
    }

    public void switchTurns() {
        if (boardState[getKing(turn).getLocX()][getKing(turn).getLocY()].isCheck()) {
            turn ^= 1;
            onGameEnd();
        }
        if (destinations.isEmpty())
            clearDestinations(destinations);
        if (previousTile != null)
            previousTile.deselect();
        previousTile = null;
        turn ^= 1;
        if (!end && timer != null) {
            timer.reset();
            timer.start();
            showPlayer.remove(CHNC);
            if (Game.move.equals("White"))
                Game.move = "Black";
            else
                Game.move = "White";
            CHNC.setText(Game.move);
            showPlayer.add(CHNC);
        }
    }

    private King getKing(int color) {
        if (color == 0)
            return wKing;
        else
            return bKing;
    }

    private void clearDestinations(ArrayList<Tile> destinations) {
        for (Tile destination : destinations) destination.removePossibleDestination();
    }

    private void highlightPossibleDestinations(ArrayList<Tile> destinations) {
        for (Tile destination : destinations) destination.setPossibleDestination();
    }

    private boolean isKingInDanger(Tile fromTile, Tile toTile) {
        Tile newBoardState[][] = new Tile[8][8];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                try {
                    newBoardState[i][j] = new Tile(boardState[i][j]);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                    System.out.println("There is a problem with cloning !!");
                }
            }

        if (newBoardState[toTile.locX][toTile.locY].getPiece() != null)
            newBoardState[toTile.locX][toTile.locY].removePiece();

        newBoardState[toTile.locX][toTile.locY].setPiece(newBoardState[fromTile.locX][fromTile.locY].getPiece());
        if (newBoardState[toTile.locX][toTile.locY].getPiece() instanceof King) {
            ((King) (newBoardState[toTile.locX][toTile.locY].getPiece())).setLocX(toTile.locX);
            ((King) (newBoardState[toTile.locX][toTile.locY].getPiece())).setLocY(toTile.locY);
        }

        newBoardState[fromTile.locX][fromTile.locY].removePiece();
        return ((King) (newBoardState[getKing(turn).getLocX()][getKing(turn).getLocY()]
                .getPiece())).isInDanger(newBoardState);
    }

    private ArrayList<Tile> filterDestinations(ArrayList<Tile> destinations, Tile fromTile) {
        ArrayList<Tile> newList = new ArrayList<>();
        Tile newBoardState[][] = new Tile[8][8];
        int x, y;
        for (Tile destination : destinations) {
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++) {
                    try {
                        newBoardState[i][j] = new Tile(boardState[i][j]);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }

            Tile tempC = destination;
            if (newBoardState[tempC.locX][tempC.locY].getPiece() != null)
                newBoardState[tempC.locX][tempC.locY].removePiece();
            newBoardState[tempC.locX][tempC.locY].setPiece(newBoardState[fromTile.locX][fromTile.locY].getPiece());
            x = getKing(turn).getLocX();
            y = getKing(turn).getLocY();
            if (newBoardState[fromTile.locX][fromTile.locY].getPiece() instanceof King) {
                ((King) (newBoardState[tempC.locX][tempC.locY].getPiece())).setLocX(tempC.locX);
                ((King) (newBoardState[tempC.locX][tempC.locY].getPiece())).setLocY(tempC.locY);
                x = tempC.locX;
                y = tempC.locY;
            }
            newBoardState[fromTile.locX][fromTile.locY].removePiece();
            if (!((King) (newBoardState[x][y].getPiece())).isInDanger(newBoardState))
                newList.add(tempC);
        }
        return newList;
    }

    //A Function to filter the possible moves when the king of the current player is under Check
    private ArrayList<Tile> isCheckFilter(ArrayList<Tile> destinations, Tile fromTile, int color) {
        ArrayList<Tile> newList = new ArrayList<>();
        Tile newBoardState[][] = new Tile[8][8];
        int x, y;
        for (Tile destionation : destinations) {
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++) {
                    try {
                        newBoardState[i][j] = new Tile(boardState[i][j]);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            Tile tempC = destionation;
            if (newBoardState[tempC.locX][tempC.locY].getPiece() != null)
                newBoardState[tempC.locX][tempC.locY].removePiece();
            newBoardState[tempC.locX][tempC.locY]
                    .setPiece(newBoardState[fromTile.locX][fromTile.locY].getPiece());
            x = getKing(color).getLocX();
            y = getKing(color).getLocY();
            if (newBoardState[tempC.locX][tempC.locY].getPiece() instanceof King) {
                ((King) (newBoardState[tempC.locX][tempC.locY].getPiece())).setLocX(tempC.locX);
                ((King) (newBoardState[tempC.locX][tempC.locY].getPiece())).setLocY(tempC.locY);
                x = tempC.locX;
                y = tempC.locY;
            }
            newBoardState[fromTile.locX][fromTile.locY].removePiece();
            if (!((King) (newBoardState[x][y].getPiece())).isInDanger(newBoardState))
                newList.add(tempC);
        }
        return newList;
    }

    //A function to check if the King is check-mate. The Game Ends if this function returns true.
    public boolean checkmate(int color) {
        ArrayList<Tile> destinationsList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardState[i][j].getPiece() != null &&
                        boardState[i][j].getPiece().getColor() == color) {
                    destinationsList.clear();
                    destinationsList = boardState[i][j].getPiece().movePiece(boardState, i, j);
                    destinationsList = isCheckFilter(destinationsList, boardState[i][j], color);
                    if (destinationsList.size() != 0)
                        return false;
                }
            }
        }
        return true;
    }


    @SuppressWarnings("deprecation")
    public void onGameEnd() {
        clearDestinations(destinations);
        displayTime.disable();
        timer.timer.stop();
        if (previousTile != null)
            previousTile.removePiece();
        if (turn == 0) {
            white.wonGame();
            white.savePlayerDetails();
            winner = white.getName();
        } else {
            black.wonGame();
            black.savePlayerDetails();
            winner = black.getName();
        }
        JOptionPane.showMessageDialog(board, "Checkmate!!!\n" + winner + " wins");
        wPlayer.remove(wDetails);
        wPlayer.remove(bDetails);
        displayTime.remove(label);

        displayTime.add(start);
        showPlayer.remove(mov);
        showPlayer.remove(CHNC);
        showPlayer.revalidate();
        showPlayer.add(timeSlider);

        split.remove(board);
        wNewPlayer.enable();
        bNewPlayer.enable();
        wSelect.enable();
        bSelect.enable();
        end = true;
        gameBoard.disable();
        gameBoard.dispose();
        gameBoard = new Game();
        gameBoard.setVisible(true);
        gameBoard.setResizable(false);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        tile = (Tile) mouseEvent.getSource();
        if (previousTile == null) {
            if (tile.getPiece() != null) {
                if (tile.getPiece().getColor() != turn)
                    return;
                tile.select();
                previousTile = tile;
                destinations.clear();
                destinations = tile.getPiece().movePiece(boardState, tile.locX, tile.locY);
                if (tile.getPiece() instanceof King)
                    destinations = filterDestinations(destinations, tile);
                else {
                    if (boardState[getKing(turn).getLocX()][getKing(turn).getLocY()].isCheck()) {
                        destinations = new ArrayList<>(filterDestinations(destinations, tile));
                    } else if (!destinations.isEmpty() && isKingInDanger(tile, destinations.get(0)))
                        destinations.clear();
                }
                highlightPossibleDestinations(destinations);
            }
        } else {
            if (tile.locX == previousTile.locX && tile.locY == previousTile.locY) {
                tile.deselect();
                clearDestinations(destinations);
                destinations.clear();
                previousTile = null;
            } else if (tile.getPiece() == null ||
                    previousTile.getPiece().getColor() != tile.getPiece().getColor()) {
                if (tile.getPossibleDestination()) {
                    if (tile.getPiece() != null)
                        tile.removePiece();
                    tile.setPiece(previousTile.getPiece());
                    if (previousTile.isCheck())
                        previousTile.removeCheck();
                    previousTile.removePiece();
                    if (getKing(turn ^ 1).isInDanger(boardState)) {
                        boardState[getKing(turn ^ 1).getLocX()][getKing(turn ^ 1).getLocY()].setCheck();
                        if (checkmate(getKing(turn ^ 1).getColor())) {
                            previousTile.deselect();
                            if (previousTile.getPiece() != null)
                                previousTile.removePiece();
                            onGameEnd();
                        }
                    }
                    if (!getKing(turn).isInDanger(boardState))
                        boardState[getKing(turn).getLocX()][getKing(turn).getLocY()].removeCheck();
                    if (tile.getPiece() instanceof King) {
                        ((King) tile.getPiece()).setLocX(tile.locX);
                        ((King) tile.getPiece()).setLocY(tile.locY);
                    }
                    switchTurns();
                    if (!end) {
                        timer.reset();
                        timer.start();
                    }
                }
                if (previousTile != null) {
                    previousTile.deselect();
                    previousTile = null;
                }
                clearDestinations(destinations);
                destinations.clear();
            } else if (previousTile.getPiece().getColor() == tile.getPiece().getColor()) {
                previousTile.deselect();
                clearDestinations(destinations);
                destinations.clear();
                tile.select();
                previousTile = tile;
                destinations = tile.getPiece().movePiece(boardState, tile.locX, tile.locY);
                if (tile.getPiece() instanceof King)
                    destinations = filterDestinations(destinations, tile);
                else {
                    if (boardState[getKing(turn).getLocX()][getKing(turn).getLocY()].isCheck())
                        destinations = new ArrayList<>(filterDestinations(destinations, tile));
                    else if (!destinations.isEmpty() && isKingInDanger(tile, destinations.get(0)))
                        destinations.clear();
                }
                highlightPossibleDestinations(destinations);
            }
        }
        if (tile.getPiece() != null && tile.getPiece() instanceof King) {
            ((King) tile.getPiece()).setLocX(tile.locX);
            ((King) tile.getPiece()).setLocY(tile.locY);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    class StartListener implements ActionListener {

        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent arg0) {

            if (white == null || black == null) {
                JOptionPane.showMessageDialog(controlPanel, "Fill in the details");
                return;
            }
            white.playedGame();
            white.savePlayerDetails();
            black.playedGame();
            black.savePlayerDetails();
            wNewPlayer.disable();
            wNewPlayer.disable();
            wSelect.disable();
            wSelect.disable();
            split.add(board);
            showPlayer.remove(timeSlider);
            mov = new JLabel("Move:");
            mov.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            showPlayer.add(mov);
            CHNC = new JLabel(move);
            CHNC.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            showPlayer.add(CHNC);
            displayTime.remove(start);
            displayTime.add(label);
            timer = new Time(label);
            timer.start();
        }
    }

    class TimeChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent arg0) {
            timeRemaining = timeSlider.getValue() * 60;
        }
    }


    class SelectListener implements ActionListener {
        private int color;

        SelectListener(int i) {
            color = i;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            tempPlayer = null;
            String n = (color == 0) ? wName : bName;
            JComboBox<String> jComboBox = (color == 0) ? wCombo : bCombo;
            JComboBox<String> ojComboBox = (color == 0) ? wCombo : bCombo;
            ArrayList<Player> players = (color == 0) ? wPlayers : bPlayers;
            ArrayList<Player> oPlayer = Player.fetchPlayerDetails();
            if (oPlayer.isEmpty())
                return;
            JPanel det = (color == 0) ? wDetails : bDetails;
            JPanel PL = (color == 0) ? wPlayer : bPlayer;
            if (selected)
                det.removeAll();
            n = (String) jComboBox.getSelectedItem();
            for (Player player : players) {
                if (player.getName().equals(n)) {
                    tempPlayer = player;
                    break;
                }
            }
            for (Player player : players) {
                if (player.getName().equals(n)) {
                    oPlayer.remove(player);
                    break;
                }
            }

            if (tempPlayer == null)
                return;
            if (color == 0)
                white = tempPlayer;
            else
                black = tempPlayer;
            bPlayers = oPlayer;
            ojComboBox.removeAllItems();
            for (Player pl : oPlayer)
                ojComboBox.addItem(pl.getName());
            det.add(new JLabel(" " + tempPlayer.getName()));
            det.add(new JLabel(" " + tempPlayer.getPlayed()));
            det.add(new JLabel(" " + tempPlayer.getWon()));

            PL.revalidate();
            PL.repaint();
            PL.add(det);
            selected = true;
        }

    }


    class Handler implements ActionListener {

        private int color;

        Handler(int i) {
            color = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String n = (color == 0) ? wName : bName;
            JPanel j = (color == 0) ? wPlayer : bPlayer;
            ArrayList<Player> players = Player.fetchPlayerDetails();
            JPanel det = (color == 0) ? wDetails : bDetails;
            n = JOptionPane.showInputDialog(j, "Enter your name");

            if (n != null) {

                for (Player pl : players) {
                    if (pl.getName().equals(n)) {
                        JOptionPane.showMessageDialog(j, "Player exists");
                        return;
                    }
                }

                if (n.length() != 0) {
                    Player temp = new Player(n);
                    temp.savePlayerDetails();
                    if (color == 0)
                        white = temp;
                    else
                        black = temp;
                } else return;
            } else
                return;
            det.removeAll();
            det.add(new JLabel(" " + n));
            det.add(new JLabel(" 0"));
            det.add(new JLabel(" 0"));
            j.revalidate();
            j.repaint();
            j.add(det);
            selected = true;
        }
    }

    private static void setupPieces() {
        wRook1 = new Rook("WR01", "images/WhiteRook.png", 0);
        wRook2 = new Rook("WR02", "images/WhiteRook.png", 0);
        bRook1 = new Rook("BR01", "images/BlackRook.png", 1);
        bRook2 = new Rook("BR02", "images/BlackRook.png", 1);
        wKnight1 = new Knight("WK01", "images/WhiteKnight.png", 0);
        wKnight2 = new Knight("WK02", "images/WhiteKnight.png", 0);
        bKnight1 = new Knight("BK01", "images/BlackKnight.png", 1);
        bKnight2 = new Knight("BK02", "images/BlackKnight.png", 1);
        wBishop1 = new Bishop("WB01", "images/WhiteBishop.png", 0);
        wBishop2 = new Bishop("WB02", "images/WhiteBishop.png", 0);
        bBishop1 = new Bishop("BB01", "images/BlackBishop.png", 1);
        bBishop2 = new Bishop("BB02", "images/BlackBishop.png", 1);
        wQueen = new Queen("WQ", "images/WhiteQueen.png", 0);
        bQueen = new Queen("BQ", "images/BlackQueen.png", 1);
        wKing = new King("WK", "images/WhiteKing.png", 0, 7, 4);
        bKing = new King("BK", "images/BlackKing.png", 1, 0, 4);
        wPawns = new Pawn[8];
        bPawns = new Pawn[8];
        for (int i = 0; i < 8; i++) {
            wPawns[i] = new Pawn("WP0" + (i + 1), "images/WhitePawn.png", 0);
            bPawns[i] = new Pawn("BP0" + (i + 1), "images/BlackPawn.png", 1);
        }
    }

    public static void main(String[] args) {
        //Setting up the board
        setupPieces();
        gameBoard = new Game();
        gameBoard.setVisible(true);
        gameBoard.setResizable(false);
    }
}
