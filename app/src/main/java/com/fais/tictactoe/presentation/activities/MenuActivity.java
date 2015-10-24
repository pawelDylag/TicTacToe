package com.fais.tictactoe.presentation.activities;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.fais.tictactoe.R;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemSelected;


public class MenuActivity extends BaseActivity {

    @Bind(R.id.table_size_picker)
    NumberPicker sizePicker;
    @Bind(R.id.opponent_checkbox_human)
    CheckBox humanCheckbox;
    @Bind(R.id.opponent_checkbox_ai)
    CheckBox aiCheckbox;
    @Bind(R.id.difficulty_spinner)
    Spinner difficultySpinner;
    @Bind(R.id.start_game_button)
    Button startButton;
    @Bind(R.id.difficulty_layout)
    RelativeLayout difficultyLayout;

    private int difficultyPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.activity_menu);
        // bind this view
        ButterKnife.bind(this);

//      setToolbarTitle(R.string.app_name);

        humanCheckbox.setChecked(true);
        aiCheckbox.setChecked(false);

        sizePicker.setValue(3);
        sizePicker.setMinValue(2);
        sizePicker.setMaxValue(100);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // All clicks on menu items needs to be here
        return super.onOptionsItemSelected(item);
    }

    @OnCheckedChanged(R.id.opponent_checkbox_human)
    public void onHumanChecked(boolean checked){
        if(checked){
            aiCheckbox.setChecked(false);
            difficultyLayout.setVisibility(View.INVISIBLE);
            startButton.setClickable(true);
        }else {
            startButton.setClickable(false);
            aiCheckbox.setChecked(true);
            difficultyLayout.setVisibility(View.VISIBLE);
        }
    }
    @OnCheckedChanged(R.id.opponent_checkbox_ai)
    public void onAIChecked(boolean checked){
        if(checked){
            startButton.setClickable(false);
            humanCheckbox.setChecked(false);
            difficultyLayout.setVisibility(View.VISIBLE);
        }else {
            humanCheckbox.setChecked(true);
            startButton.setClickable(true);
            difficultyLayout.setVisibility(View.INVISIBLE);
        }
    }

    @OnItemSelected(R.id.difficulty_spinner)
    void onItemSelected(int position){
        if (aiCheckbox.isChecked()) {
            startButton.setClickable(true);
            difficultyPosition = position;
        }
    }

    @OnClick(R.id.start_game_button)
    public void onClick(View view){
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        Bundle bundle = new Bundle();
        if(aiCheckbox.isChecked())
            bundle.putInt("Difficulty", difficultyPosition);

        bundle.putInt("Size", sizePicker.getValue());
        startActivity(intent);
    }


}
