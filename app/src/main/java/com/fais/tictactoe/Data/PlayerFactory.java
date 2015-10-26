package com.fais.tictactoe.Data;

import android.support.annotation.Nullable;

import com.fais.tictactoe.domain.TicTacToeAIPlayerEasy;
import com.fais.tictactoe.domain.TicTacToeAIPlayerHard;
import com.fais.tictactoe.domain.TicTacToeHumanPlayer;

/**
 * Created by paweldylag on 25/10/15.
 */
public class PlayerFactory
{
    private Player player;

    public PlayerFactory()
    {
    }

    @Nullable
    public Player getPlayer(int playerType)
    {
        switch(playerType)
        {
            case Parameters.PLAYER_HUMAN:
                player = new TicTacToeHumanPlayer();
                player.setPlayerType(playerType);
                break;
            case Parameters.PLAYER_HUMAN_TWO:
                player = new TicTacToeHumanPlayer();
                player.setPlayerType(playerType);
                break;
            case Parameters.PLAYER_AI_EASY:
                player = new TicTacToeAIPlayerEasy();
                player.setPlayerType(playerType);
                break;
            case Parameters.PLAYER_AI_HARD:
                player = new TicTacToeAIPlayerHard();
                player.setPlayerType(playerType);
                break;
            default:
                return null;
        }
        return player;
    }
}
