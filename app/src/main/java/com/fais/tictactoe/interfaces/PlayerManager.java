package com.fais.tictactoe.interfaces;

import com.fais.tictactoe.Data.Player;

/**
 * Created by paweldylag on 20/10/15.
 */
public interface PlayerManager
{

    // pobiera kolejnego graca w turze
    void nextTurn();

    // pobiera aktualnego graca
    Player currentPlayer();
}
