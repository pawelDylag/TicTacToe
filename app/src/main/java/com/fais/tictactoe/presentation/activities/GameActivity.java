package com.fais.tictactoe.presentation.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import com.fais.tictactoe.Data.Parameters;
import com.fais.tictactoe.R;
import com.fais.tictactoe.domain.Game;
import com.fais.tictactoe.presentation.AndroidOutputProvider;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * This activity holds main game view
 */
public class GameActivity extends Activity {

    @Bind(R.id.activity_game_coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.activity_game_grid_view)
    GridView gridView;

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.activity_game);
        // bind this view
        ButterKnife.bind(this);
        // init game with starting params
        initGame(getIntent());
        // set input listeners
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                game.onBoardClick(position);
            }
        });

    }

    public void initGame(Intent intent) {
        int boardSize = intent.getExtras().getInt(Parameters.INTENT_BOARD_SIZE);
        AndroidOutputProvider outputProvider = new AndroidOutputProvider(gridView, coordinatorLayout, boardSize, this);
        //TODO: Tutaj stworzyc playerow w zaleznosci od wyboru usera, i dodac ich do game. Aktualnie sa 'null'
        game = new Game(null, null, boardSize);
        game.setOutputProvider(outputProvider);
    }



}
