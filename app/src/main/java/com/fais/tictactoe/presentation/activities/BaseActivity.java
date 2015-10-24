package com.fais.tictactoe.presentation.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.fais.tictactoe.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Base activity extended by all activities in this app
 * Created by paweldylag on 18/10/15.
 */
public class BaseActivity extends Activity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ButterKnife.bind(this);

        super.onCreate(savedInstanceState);
    }

    public void setToolbarTitle(int title){
        toolbar.setTitle(getString(title));
    }
}
