package com.example.indra.connect3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int player = 0; // 0 = yellow  1 = red  2 = empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {3, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void playAgain(View view) {
        Button playAgainBtn = findViewById(R.id.playAgainButton);
        TextView winnerText =  findViewById(R.id.winnerTextView);
        playAgainBtn.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);

        android.support.v7.widget.GridLayout gridLayout =  findViewById(R.id.mainGrid);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            // do stuff with child view
            child.setImageDrawable(null);
        }

         player = 0; // 0 = yellow  1 = red  2 = empty


         for(int i=0;i< gameState.length;i++){
             gameState[i] = 2;
         }

         gameActive = true;
    }

    public void click(View view) {
        ImageView counter = (ImageView) view;


        if (gameState[Integer.parseInt(counter.getTag().toString())] == 2 && gameActive) {

            counter.setY(-1000);

            gameState[Integer.parseInt(counter.getTag().toString())] = player;

            if (player == 0) {
                counter.setImageResource(R.drawable.red);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);
                player = 0;
            }

            counter.animate().translationY(0).rotation(500).setDuration(500);


            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    Toast.makeText(this, "Someone win", Toast.LENGTH_SHORT).show();
                    gameActive = false;

                    Button playAgainBtn = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
                    playAgainBtn.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);


                } else {
                    Log.i("Info", "not match");
                }
            }
        }

    }
}
