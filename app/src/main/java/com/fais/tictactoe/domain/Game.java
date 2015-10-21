package com.fais.tictactoe.domain;

import com.fais.tictactoe.data.Player;
import com.fais.tictactoe.presentation.InputProvider;
import com.fais.tictactoe.presentation.OutputProvider;


/**
 * Created by paweldylag on 20/10/15.
 */
public class Game  {

    private GameEngine gameEngine;
    private BoardManager boardManager;
    private PlayerManager playerManager;

    private Player firstPlayer;
    private Player secondPlayer;

    private int boardSize;

    private InputProvider inputProvider;
    private OutputProvider outputProvider;

    public Game(Player firstPlayer, Player secondPlayer, int boardSize, InputProvider inputProvider, OutputProvider outputProvider) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.boardSize = boardSize;
        this.inputProvider = inputProvider;
        this.outputProvider = outputProvider;
    }

    public void start() {
        //gameEngine.startGame()
    }
}
