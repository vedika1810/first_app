package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn=true;

    private int Rcount;

    private int player1count;
    private int player2count;

    private TextView tvp1;
    private TextView tvp2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvp1=findViewById(R.id.t1);
        tvp2=findViewById(R.id.t2);

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String buttonID="b_"+i+j;
                int resID=getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j]=findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.b_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                resetgame();

            }
        });

    }
            @Override
            public void onClick(View v) {
                if (!((Button) v).getText().toString().equals("")) {    //no space in equals""
                    return;
                }
                if (player1Turn) {
                    ((Button) v).setText("x");
                } else {
                    ((Button) v).setText("O");
                }
                Rcount++;

                if (CheckForWin()) {
                    if (player1Turn) {
                        player1wins();
                    } else {
                        player2wins();
                    }
                } else if (Rcount == 9) {
                    draw();
                } else {
                    player1Turn = !player1Turn;
                }
            }

            private boolean CheckForWin() {
                String[][] field = new String[3][3];

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        field[i][j] = buttons[i][j].getText().toString();
                    }
                }
                for (int i = 0; i < 3; i++) {
                    if (field[0][i].equals(field[1][i])
                            && field[0][i].equals(field[2][i])
                            && !field[0][i].equals("")) {
                        return true;
                    }
                }
                if (field[0][0].equals(field[1][1])
                        && field[0][0].equals(field[2][2])
                        && !field[0][0].equals("")) {
                    return true;
                }
                if (field[0][2].equals(field[1][1])
                        && field[0][2].equals(field[2][2])
                        && !field[0][2].equals("")) {
                    return true;
                }
                return false;
            }

            private void player1wins() {
        player1count++;
                Toast.makeText(this,"player1 wins!",Toast.LENGTH_LONG).show();
                updatepointstext();
                resetboard();
            }
            private void player2wins() {
                player2count++;
                Toast.makeText(this,"player2 wins!",Toast.LENGTH_LONG).show();
                updatepointstext();
                resetboard();
            }
            private void draw() {
           Toast.makeText(this,"Draw!",Toast.LENGTH_LONG).show();
           resetboard();
            }
            private void updatepointstext(){
        tvp1.setText("Player1: " + player1count);
        tvp2.setText("player2: " + player2count);
            }
            private void resetboard() {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");
                    }
                }
                Rcount=0;
                player1Turn=true;
            }
            private void resetgame(){
        player1count=0;
        player2count=0;
        updatepointstext();
        resetboard();
            }
}
