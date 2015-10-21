package com.fais.tictactoe.presentation.activities;

import android.os.Bundle;


import com.fais.tictactoe.R;

import butterknife.ButterKnife;

/**
 * This activity holds main game view
 */
public class GameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.activity_game);
        // bind this view
        ButterKnife.bind(this);


    }

}
