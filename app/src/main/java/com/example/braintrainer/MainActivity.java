package com.example.braintrainer;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView timerTextView,scoreTextView,questionTextView,resultTextView;
    CountDownTimer timesUp;
    int locationOfCorrectAnswer;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int score=0,count=0;
    Button b1,b2,b3,b4;
    Button playAgain;
    boolean gameIsActive=false;
    ConstraintLayout gameConstraintlayout;

    public void startGame(View v) {
        startButton.setVisibility(View.INVISIBLE);
        gameIsActive=true;
        timerTextView.setText("10s");
        timer();
        mainPlay();
        gameConstraintlayout.setVisibility(View.VISIBLE);

        //if u want to call a button function ;use this
       // playAgain(findViewById(R.id.button6));
    }



    public void timer() {
        if (gameIsActive) {
            timesUp = new CountDownTimer(10100, 1000) {
                @Override
                public void onTick(long l) {
                    timerTextView.setText(Long.toString(l / 1000) + "s");
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("0s");
                    resultTextView.setText("Your score : " + Integer.toString(score) + "/" + Integer.toString(count));
                    playAgain.setVisibility(View.VISIBLE);
                    gameIsActive = false;
                }
            }.start();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton=(Button)findViewById(R.id.button);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        scoreTextView=(TextView)findViewById(R.id.scoreTextView);
        questionTextView=(TextView)findViewById(R.id.textView3);
        resultTextView=(TextView)findViewById(R.id.textView4);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        gameConstraintlayout=(ConstraintLayout)findViewById(R.id.gameConstraintLayout);
        playAgain=(Button)findViewById(R.id.button6);
        playAgain.setVisibility(View.INVISIBLE);

        mainPlay();

    }

    public void mainPlay(){
        if (gameIsActive) {
            int a = 1 + (int) (Math.random() * 20);
            int b = 1 + (int) (Math.random() * 20);
            questionTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
            answers.clear();
            int correctAns = a + b;
            int incorrectAns;
            locationOfCorrectAnswer = 1 + (int) (Math.random() * 4);
            for (int i = 1; i <= 4; i++) {
                if (i == locationOfCorrectAnswer)
                    answers.add(correctAns);
                else {
                    incorrectAns = 1 + (int) (Math.random() * 40);
                    while (incorrectAns == correctAns) {
                        incorrectAns = 1 + (int) (Math.random() * 40);
                    }
                    answers.add(incorrectAns);
                }
            }
            b1.setText(Integer.toString(answers.get(0)));
            b2.setText(Integer.toString(answers.get(1)));
            b3.setText(Integer.toString(answers.get(2)));
            b4.setText(Integer.toString(answers.get(3)));
        }
    }

    public void buttontapped(View v){
        if (gameIsActive) {
            Log.i("Button Tappped", (String) (v.getTag()));
            Log.i("LocationOf correct answer", Integer.toString(locationOfCorrectAnswer));
            if (v.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

                resultTextView.setText("Correct!");
                score++;
            } else {
                resultTextView.setText("Incorrect!");
            }
            count++;
            scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(count));
            mainPlay();
        }
    }

    public void playAgain(View v){
        score=0;
        count=0;
        scoreTextView.setText("0/0");
        resultTextView.setText(" ");
        gameIsActive=true;
        timerTextView.setText("10s");
        timer();
        mainPlay();

    }


}