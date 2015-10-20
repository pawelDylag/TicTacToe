package com.fais.tictactoe.presentation.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fais.tictactoe.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

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
