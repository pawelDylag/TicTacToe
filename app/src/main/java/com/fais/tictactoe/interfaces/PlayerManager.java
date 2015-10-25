package com.fais.tictactoe.interfaces;

import com.fais.tictactoe.Data.Player;

/**
 * Created by paweldylag on 20/10/15.
 */
public interface PlayerManager
{
    // zwraca obiekt Player dla podanego typu
    Player getPlayer(int playerType);

    // przekazuje referencje do BM jesli player jest typu AI
    void passBoardManager (BoardManager boardManager);

    // przekazuje referencje do siebie dla playerow
    void passPlayerManager ();
}
