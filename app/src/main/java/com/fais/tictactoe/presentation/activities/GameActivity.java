package com.fais.tictactoe.presentation.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.widget.GridView;


import com.fais.tictactoe.R;
import com.fais.tictactoe.presentation.AndroidOutputProvider;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * This activity holds main game view
 */
public class GameActivity extends BaseActivity {

    @Bind(R.id.activity_game_coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.activity_game_grid_view)
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.activity_game);
        // bind this view
        ButterKnife.bind(this);

    }





}
