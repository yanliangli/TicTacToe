package com.example.yanli.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SpinnerAdapter;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity implements View.OnClickListener, ActionBar.OnNavigationListener {

    private Button[] btns;
    private String current;
    private String xSym;
    private String oSym;
    private int xColor;
    private int oColor;
    private int count;
    private boolean win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        win = false;
        count = 0;
        btns = new Button[9];

        int id = R.id.button1;
        for (int i=0; i<btns.length; i++)
        {
            btns[i] = (Button)findViewById(id);
            btns[i].setOnClickListener(this);
            id++;
        }
        xSym = "X";
        oSym = "O";
        xColor = Color.RED;
        oColor = Color.BLUE;
        current = xSym;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SpinnerAdapter pColorsSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.player_colors_array,
                android.R.layout.simple_spinner_dropdown_item);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(pColorsSpinnerAdapter, this);
        actionBar.setTitle("Tic Tac Toe");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.new_game){
            newGame();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        Button b = (Button) v;
        b.setEnabled(false); //disable the button

        if (current.equals(xSym))
        {
            b.setText(xSym);
            b.setTextColor(xColor);
        }
        else
        {
           b.setText(oSym);
           b.setTextColor(oColor);
        }
        count++;
        checkWinner();
        next();
    }

    public void next()
    {
        if (current.equals(xSym)) {
            current = oSym;
        }
        else {
            current = xSym;
        }
    }

    public void checkWinner()
    {
        // check rows
        for (int i = 0; i < 7; i += 3)
        {
            if (!btns[i].getText().equals("") &&
                    btns[i].getText().equals(btns[i + 1].getText()) &&
                    btns[i + 1].getText().equals(btns[i + 2].getText()))
            {
                win = true;
            }

        }

        // check columns
        for (int i = 0; i < 3; i++)
        {
            if (!btns[i].getText().equals("") &&
                    btns[i].getText().equals(btns[i + 3].getText()) &&
                    btns[i].getText().equals(btns[i + 6].getText()))
            {
                win = true;
            }
        }

        // check diagonals
        if ((!btns[4].getText().equals("")) &&
                ((btns[4].getText().equals(btns[0].getText()) &&
                        btns[4].getText().equals(btns[8].getText())) ||
                        (btns[4].getText().equals(btns[2].getText()) &&
                                btns[4].getText().equals(btns[6].getText()))))
        {
            win = true;
        }

        if (win)
        {
            Toast.makeText(getApplicationContext(), "Player " + current + " win!", Toast.LENGTH_SHORT).show();
            enableAllButton(false); // disable remaining buttons
        }

        if (count > 9 && !win)
        {
            Toast.makeText(getApplicationContext(), "Draw!", Toast.LENGTH_SHORT).show();
        }
    }

    public void enableAllButton(boolean status)

    {
        for (int i = 0; i < 9; i++)
        {
            btns[i].setEnabled(status);
        }
    }

    public void newGame()
    {
        for (int i = 0; i < 9; i++)
        {
            btns[i].setText("");
        }
        enableAllButton(true); // re-enable buttons
        current = xSym;
        count = 0;
        win = false;
        Toast.makeText(getApplicationContext(), "New Game", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        switch(itemPosition)
        {
            case 0:
                xColor = Color.RED;
                oColor = Color.BLUE;
                Toast.makeText(getApplicationContext(), xSym + " is red. " + oSym + " is blue.", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                xColor = Color.YELLOW;
                oColor = Color.GREEN;
                Toast.makeText(getApplicationContext(), xSym + " is yellow. " + oSym + " is green.", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                xColor = Color.CYAN;
                oColor = Color.MAGENTA;
                Toast.makeText(getApplicationContext(), xSym + " is cyan. " + oSym + " is magenta.", Toast.LENGTH_SHORT).show();
                break;
        }
        for (int i = 0; i < 9; i++)
        {
            if (btns[i].getText().equals(xSym))
            {
                btns[i].setTextColor(xColor);
            }
            else
            {
                btns[i].setTextColor(oColor);
            }
        }

        return true;
    }

}
