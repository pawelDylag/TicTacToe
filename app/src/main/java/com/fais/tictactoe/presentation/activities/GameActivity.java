package com.fais.tictactoe.presentation.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toolbar;


import com.fais.tictactoe.Data.Parameters;
import com.fais.tictactoe.R;
import com.fais.tictactoe.domain.TicTacToeGame;
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
    @Bind(R.id.activity_game_toolbar)
    Toolbar toolbar;

    /**
     * Main game object
     */
    private TicTacToeGame ticTacToeGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.activity_game);
        // bind this view
        ButterKnife.bind(this);
        // setup toolbar
        // TODO: dodac imiona graczy
        initToolbar();
        // init game with starting params
        initGame(getIntent());
        // set input listeners
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ticTacToeGame.onBoardClick(position);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ticTacToeGame == null) {
            throw new NullPointerException("Game object must be initialized first.");
        }
        ticTacToeGame.start();
    }

    public void initGame(Intent intent) {
        int boardSize = intent.getExtras().getInt(Parameters.INTENT_BOARD_SIZE);
        AndroidOutputProvider outputProvider = new AndroidOutputProvider(gridView, coordinatorLayout, boardSize, this);
        int secondPlayerType = intent.getIntExtra(Parameters.INTENT_PLAYER_TYPE, Parameters.PLAYER_HUMAN);
        ticTacToeGame = new TicTacToeGame(Parameters.PLAYER_HUMAN, secondPlayerType, boardSize);
        ticTacToeGame.setOutputProvider(outputProvider);
    }

    private void initToolbar() {
        setActionBar(toolbar);
        if (getActionBar() != null) {
            // add title
            getActionBar().setTitle(getResources().getString(R.string.title_activity_game));
            // add back button
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
