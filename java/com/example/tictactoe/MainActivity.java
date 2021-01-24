package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    /* player representation
        x - 0
        o - 1
        null - 2
        */
    int activePlayer = 0;
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    char winner = 'O';
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int win;

    @SuppressLint("ResourceAsColor")
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImg = Integer.parseInt(img.getTag().toString());
        if(gameState[tappedImg] == 2 && gameActive) {
            gameState[tappedImg] = activePlayer;
            if(activePlayer == 0) {
                img.setImageResource(R.drawable.o);
                activePlayer = 1;
                TextView status = (TextView) findViewById(R.id.status);
                status.setText("X's turn");
            } else {
                img.setImageResource(R.drawable.x);
                activePlayer = 0;
                TextView status = (TextView) findViewById(R.id.status);
                status.setText("O's turn");
            }
        }
        // Check if any player has won
        for(int[] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2) {
                // Somebody has won! - Find out who!
                gameActive = false;
                String winnerStr;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "O has won";
                    winner = 'O';
                    win = 0;
                } else {
                    winnerStr = "X has won";
                    winner = 'X';
                    win = 1;
                }
                // Update the status bar for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
                gameActive = false;
            }
        }
    }

    public void reset (View view) {
        gameActive = true;
        activePlayer = win;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText(winner+ "'s Turn - Tap to play");
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
