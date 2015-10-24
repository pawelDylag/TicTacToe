package com.fais.tictactoe.domain;

import com.fais.tictactoe.Data.Player;

/**
 * Created by paweldylag on 20/10/15.
 */
public class PlayerManager {

    private Player firstPlayer;
    private Player secondPlayer;

    public PlayerManager(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }
}
