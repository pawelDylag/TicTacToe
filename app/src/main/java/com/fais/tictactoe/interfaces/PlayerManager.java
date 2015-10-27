package com.fais.tictactoe.interfaces;

import com.fais.tictactoe.Data.Player;

/**
 * Created by paweldylag on 20/10/15.
 */
public interface PlayerManager
{
    // zwraca obiekt Player dla podanego typu
    Player getPlayer(int playerType);

    Player getFirstPlayer();

    Player getSecondPlayer();

    // pobiera kolejnego graca w turze
    void nextTurn();

    // pobiera aktualnego graca
    Player currentPlayer();
}
