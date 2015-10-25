package com.fais.tictactoe.domain;

import com.fais.tictactoe.Data.Player;
import com.fais.tictactoe.interfaces.PlayerManager;

/**
 * Created by paweldylag on 20/10/15.
 */
public class TicTacToePlayerManager implements PlayerManager{

    private Player firstPlayer;
    private Player secondPlayer;

    public TicTacToePlayerManager(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    @Override
    public Player getPlayer(int playerType) {
        return null;
    }
}
