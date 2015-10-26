package com.fais.tictactoe.interfaces;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by paweldylag on 20/10/15.
 */
public interface OutputProvider {



    void drawOnBoard(int x, int y, int resource);

    void displayMessage(String message);

    void displayDialog(String title, String message);

    void displayWinnerCells(ArrayList<Point> winnerPoints);

}
