package com.fais.tictactoe.interfaces;

/**
 * Created by paweldylag on 20/10/15.
 */
public interface OutputManager {

    void drawOnBoard(int x, int y, int resource);

    void displayMessage(String message);

    void displayDialog(String title, String message);

}
